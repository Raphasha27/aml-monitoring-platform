package com.banking.amlmonitoringplatform.controller;

import com.banking.amlmonitoringplatform.model.*;
import com.banking.amlmonitoringplatform.service.AmlMonitoringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AmlController {
    private final AmlMonitoringService service;

    public AmlController(AmlMonitoringService service) { this.service = service; }

    @PostMapping("/transactions")
    public ResponseEntity<Transaction> processTransaction(@RequestBody Map<String, Object> req) {
        return ResponseEntity.ok(service.processTransaction(
            (String)req.get("accountId"), (String)req.get("customerId"),
            new BigDecimal(req.get("amount").toString()), (String)req.get("currency"),
            (String)req.get("type"), (String)req.get("sourceCountry"),
            (String)req.get("destinationCountry")));
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.getTransactions(status));
    }

    @GetMapping("/alerts")
    public ResponseEntity<List<AmlAlert>> getAlerts(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.getAlerts(status));
    }

    @PostMapping("/rules")
    public ResponseEntity<AmlRule> createRule(@RequestBody Map<String, Object> req) {
        return ResponseEntity.ok(service.createRule(
            (String)req.get("name"), (String)req.get("description"),
            (String)req.get("ruleType"), new BigDecimal(req.get("thresholdAmount").toString()),
            (String)req.get("severity")));
    }

    @GetMapping("/rules")
    public ResponseEntity<List<AmlRule>> getRules() {
        return ResponseEntity.ok(service.getActiveRules());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "aml-monitoring-platform"));
    }
}
