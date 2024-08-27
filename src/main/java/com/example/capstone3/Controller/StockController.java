package com.example.capstone3.Controller;

import com.example.capstone3.Model.Stock;
import com.example.capstone3.Service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping("/get")
    public ResponseEntity getAllstock() {
        return ResponseEntity.ok(stockService.getAllStock());
    }

    @PostMapping("/add")
    public ResponseEntity addStock(@RequestBody @Valid Stock stock) {
        stockService.addStock(stock);
        return ResponseEntity.status(200).body("Stock added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStock(@PathVariable Integer id, @RequestBody @Valid Stock stock) {
        stockService.updateStock(id, stock);
        return ResponseEntity.status(200).body("Stock updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStock(@PathVariable Integer id) {
        stockService.deleteStock(id);
        return ResponseEntity.status(200).body("Stock deleted");
    }
}
