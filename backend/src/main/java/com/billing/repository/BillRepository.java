package com.billing.repository;

import com.billing.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);
    // You can add custom queries here if needed
}
