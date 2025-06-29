package com.billing.repository;

import com.billing.model.BillItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillItemRepository extends JpaRepository<BillItem, Long> {
    // Custom methods can be added if needed
}
