package com.banking.amlmonitoringplatform.repository;

import com.banking.amlmonitoringplatform.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByCustomerId(String customerId);
    List<Transaction> findByStatus(String status);
    List<Transaction> findByCustomerIdAndTimestampAfter(String customerId, LocalDateTime after);
}
