package com.banking.amlmonitoringplatform.service;

import com.banking.amlmonitoringplatform.model.*;
import com.banking.amlmonitoringplatform.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AmlMonitoringService {
    private final TransactionRepository txnRepo;
    private final AmlAlertRepository alertRepo;
    private final AmlRuleRepository ruleRepo;

    public AmlMonitoringService(TransactionRepository tr, AmlAlertRepository ar, AmlRuleRepository rr) {
        this.txnRepo = tr; this.alertRepo = ar; this.ruleRepo = rr;
    }

    public Transaction processTransaction(String accountId, String customerId, BigDecimal amount,
                                           String currency, String type, String srcCountry, String dstCountry) {
        Transaction txn = new Transaction(accountId, customerId, amount, currency, type, srcCountry, dstCountry);
        txn = txnRepo.save(txn);

        List<AmlRule> rules = ruleRepo.findByActiveTrue();
        for (AmlRule rule : rules) {
            if (rule.getThresholdAmount() != null && amount.compareTo(rule.getThresholdAmount()) > 0) {
                AmlAlert alert = new AmlAlert(txn.getId(), customerId, rule.getName(), rule.getSeverity(),
                    amount, "Amount " + amount + " exceeds threshold " + rule.getThresholdAmount());
                alertRepo.save(alert);
                txn.setStatus("FLAGGED");
                txnRepo.save(txn);
            }
        }

        LocalDateTime dayAgo = LocalDateTime.now().minusDays(1);
        List<Transaction> recent = txnRepo.findByCustomerIdAndTimestampAfter(customerId, dayAgo);
        BigDecimal totalRecent = recent.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (totalRecent.compareTo(new BigDecimal("100000")) > 0) {
            AmlAlert alert = new AmlAlert(txn.getId(), customerId, "HIGH_VOLUME", "HIGH",
                amount, "Customer " + customerId + " transacted " + totalRecent + " in 24h");
            alertRepo.save(alert);
            txn.setStatus("FLAGGED");
            txnRepo.save(txn);
        }

        if (!"FLAGGED".equals(txn.getStatus())) {
            txn.setStatus("CLEARED");
            txnRepo.save(txn);
        }
        return txn;
    }

    public List<Transaction> getTransactions(String status) {
        return status != null ? txnRepo.findByStatus(status) : txnRepo.findAll();
    }

    public List<AmlAlert> getAlerts(String status) {
        return status != null ? alertRepo.findByStatus(status) : alertRepo.findAll();
    }

    public AmlRule createRule(String name, String desc, String ruleType, BigDecimal threshold, String severity) {
        return ruleRepo.save(new AmlRule(name, desc, ruleType, threshold, severity));
    }

    public List<AmlRule> getActiveRules() { return ruleRepo.findByActiveTrue(); }
}
