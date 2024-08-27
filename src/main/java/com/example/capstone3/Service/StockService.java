package com.example.capstone3.Service;

import com.example.capstone3.Api.ApiException;
import com.example.capstone3.Model.Stock;
import com.example.capstone3.Repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockService {

    private final StockRepository stockRepository;

    public List<Stock> getAllStock() {
        return stockRepository.findAll();
    }

    public void addStock(Stock stock){
        stockRepository.save(stock);
    }

    public void updateStock(Integer id, Stock stock) {
        Stock s = stockRepository.findStockById(id);
        if(s == null) {
            throw new ApiException("Stock with id " + id + " not found");
        }
        s.setQuantity(stock.getQuantity());
        s.setFabric(stock.getFabric());
        s.setMerchant(stock.getMerchant());
        stockRepository.save(s);
    }

    public void deleteStock(Integer id) {
        Stock s = stockRepository.findStockById(id);
        if(s == null) {
            throw new ApiException("Stock with id " + id + " not found");
        }
        stockRepository.delete(s);
    }
}
