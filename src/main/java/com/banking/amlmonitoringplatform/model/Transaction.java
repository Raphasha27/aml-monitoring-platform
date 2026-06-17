package com.banking.amlmonitoringplatform.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "transactions")
public class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String accountId;
    private String customerId;
    private BigDecimal amount;
    private String currency;
    private String type;
    private String sourceCountry;
    private String destinationCountry;
    private String status = "PENDING";
    private LocalDateTime timestamp;

    public Transaction() {}
    public Transaction(String accountId, String customerId, BigDecimal amount, String currency,
                       String type, String sourceCountry, String destinationCountry) {
        this.accountId = accountId; this.customerId = customerId;
        this.amount = amount; this.currency = currency; this.type = type;
        this.sourceCountry = sourceCountry; this.destinationCountry = destinationCountry;
        this.timestamp = LocalDateTime.now();
    }
    public String getId() { return id; }
    public String getAccountId() { return accountId; }
    public String getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getType() { return type; }
    public String getSourceCountry() { return sourceCountry; }
    public String getDestinationCountry() { return destinationCountry; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setStatus(String s) { this.status = s; }
}
