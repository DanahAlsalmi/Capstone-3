package com.example.capstone3.Repository;

import com.example.capstone3.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Stock findStockById(Integer id);

    Stock findByFabricId(Integer fabricId);

    Stock findByMerchantId(Integer merchantId);

    Stock findByQuantity(Integer quantity);

}
