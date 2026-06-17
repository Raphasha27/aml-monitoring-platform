package com.banking.amlmonitoringplatform.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "aml_alerts")
public class AmlAlert {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String transactionId;
    private String customerId;
    private String ruleTriggered;
    private String severity;
    private BigDecimal amount;
    private String description;
    private String status = "OPEN";
    private LocalDateTime createdAt;

    public AmlAlert() {}
    public AmlAlert(String transactionId, String customerId, String rule, String severity, BigDecimal amount, String desc) {
        this.transactionId = transactionId; this.customerId = customerId;
        this.ruleTriggered = rule; this.severity = severity;
        this.amount = amount; this.description = desc;
        this.createdAt = LocalDateTime.now();
    }
    public String getId() { return id; }
    public String getTransactionId() { return transactionId; }
    public String getCustomerId() { return customerId; }
    public String getRuleTriggered() { return ruleTriggered; }
    public String getSeverity() { return severity; }
    public BigDecimal getAmount() { return amount; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setStatus(String s) { this.status = s; }
}
