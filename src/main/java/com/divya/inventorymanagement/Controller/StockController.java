package com.divya.inventorymanagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divya.inventorymanagement.Model.Stock;
import com.divya.inventorymanagement.Service.StockService;

@RestController
@RequestMapping("api/stock")
public class StockController {
    @Autowired
    private StockService stockService;

    // Post request to add stock
    @PostMapping("/addstock")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock){
        try{
            return ResponseEntity.ok(stockService.addStock(stock));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get all stocks
    @GetMapping("/getallstocks")
    public ResponseEntity<List<Stock>> getAllStocks(){
        try{
            return ResponseEntity.ok(stockService.getAllStocks());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get stock by id
    @GetMapping("/getstockbyid/{id}")
    public ResponseEntity<Stock> getStockById(int id){
        try{
            return ResponseEntity.ok(stockService.getStockById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Put request to update stock by id
    @PutMapping("/updatestockbyid/{id}")
    public ResponseEntity<Stock> updateStockById(int id, Stock stock){
        try{
            return ResponseEntity.ok(stockService.updateStockById(id, stock));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Delete request to delete stock by id
    @DeleteMapping("/deletestockbyid/{id}")
    public ResponseEntity<String> deleteStockById(int id){
        try{
            return ResponseEntity.ok(stockService.deleteStockById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    

}
