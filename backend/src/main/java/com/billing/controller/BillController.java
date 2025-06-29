package com.billing.controller;

import com.billing.model.Bill;
import com.billing.model.BillItem;
import com.billing.model.FoodItem;
import com.billing.repository.BillRepository;
import com.billing.repository.FoodItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    // ✅ Create a new bill
    @PostMapping
    @Transactional
    public ResponseEntity<?> generateBill(@RequestBody Bill bill) {
        bill.setCreatedAt(LocalDateTime.now());
        bill.setInvoiceNumber("INV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());

        for (BillItem item : bill.getItems()) {
            Optional<FoodItem> foodItem = foodItemRepository.findById(item.getFoodItem().getId());
            if (foodItem.isEmpty()) {
                return ResponseEntity.badRequest().body("Invalid food item ID: " + item.getFoodItem().getId());
            }
            item.setFoodItem(foodItem.get());
            item.setBill(bill);
            item.setPrice(item.getFoodItem().getPrice() * item.getQuantity());
        }

        double total = bill.getItems().stream().mapToDouble(BillItem::getPrice).sum();
        bill.setTotalAmount(total);
        bill.setPaymentStatus(bill.getPaymentStatus() != null ? bill.getPaymentStatus() : "Pending");

        Bill saved = billRepository.save(bill);
        return ResponseEntity.ok(saved);
    }


    // ✅ Get all bills
    // ✅ Filter bills by date range (used by Sales Dashboard)
    @GetMapping("/by-date")
    public ResponseEntity<List<Bill>> getBillsByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

        List<Bill> bills = billRepository.findByCreatedAtBetween(startDate, endDate);
        return ResponseEntity.ok(bills);
    }





}
