package com.banking.amlmonitoringplatform.repository;

import com.banking.amlmonitoringplatform.model.AmlRule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AmlRuleRepository extends JpaRepository<AmlRule, String> {
    List<AmlRule> findByActiveTrue();
    List<AmlRule> findByRuleType(String ruleType);
}
