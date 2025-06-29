package com.billing.service;

import com.billing.model.StockItem;
import com.billing.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public List<StockItem> getAllStock() {
        return stockRepository.findAll();
    }

    public Optional<StockItem> getStockById(Long id) {
        return stockRepository.findById(id);
    }

    public StockItem addStock(StockItem stockItem) {
        return stockRepository.save(stockItem);
    }

    public StockItem updateStock(Long id, StockItem updatedItem) {
        return stockRepository.findById(id).map(item -> {
            item.setName(updatedItem.getName());
            item.setCategory(updatedItem.getCategory());
            item.setQuantity(updatedItem.getQuantity());
            item.setPricePerUnit(updatedItem.getPricePerUnit());
            return stockRepository.save(item);
        }).orElse(null);
    }

    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
