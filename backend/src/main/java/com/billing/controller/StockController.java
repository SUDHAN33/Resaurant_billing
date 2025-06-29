package com.billing.controller;

import com.billing.model.StockItem;
import com.billing.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public List<StockItem> getAllStock() {
        return stockService.getAllStock();
    }

    @GetMapping("/{id}")
    public StockItem getStockById(@PathVariable Long id) {
        return stockService.getStockById(id).orElse(null);
    }

    @PostMapping
    public StockItem addStock(@RequestBody StockItem item) {
        return stockService.addStock(item);
    }

    @PutMapping("/{id}")
    public StockItem updateStock(@PathVariable Long id, @RequestBody StockItem item) {
        return stockService.updateStock(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteStock(@PathVariable Long id) {
        stockService.deleteStock(id);
    }
}
