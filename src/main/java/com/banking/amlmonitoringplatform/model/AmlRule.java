package com.banking.amlmonitoringplatform.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity @Table(name = "aml_rules")
public class AmlRule {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private String ruleType;
    private BigDecimal thresholdAmount;
    private String severity;
    private boolean active = true;

    public AmlRule() {}
    public AmlRule(String name, String desc, String ruleType, BigDecimal threshold, String severity) {
        this.name = name; this.description = desc; this.ruleType = ruleType;
        this.thresholdAmount = threshold; this.severity = severity;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getRuleType() { return ruleType; }
    public BigDecimal getThresholdAmount() { return thresholdAmount; }
    public String getSeverity() { return severity; }
    public boolean isActive() { return active; }
    public void setActive(boolean a) { this.active = a; }
}
