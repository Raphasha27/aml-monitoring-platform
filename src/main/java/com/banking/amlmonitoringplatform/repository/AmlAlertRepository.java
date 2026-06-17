package com.banking.amlmonitoringplatform.repository;

import com.banking.amlmonitoringplatform.model.AmlAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AmlAlertRepository extends JpaRepository<AmlAlert, String> {
    List<AmlAlert> findByStatus(String status);
    List<AmlAlert> findBySeverity(String severity);
    List<AmlAlert> findByCustomerId(String customerId);
}
