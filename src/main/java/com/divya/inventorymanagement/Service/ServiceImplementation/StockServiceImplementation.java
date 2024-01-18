package com.divya.inventorymanagement.Service.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divya.inventorymanagement.Model.Stock;
import com.divya.inventorymanagement.Repository.StockRepository;
import com.divya.inventorymanagement.Service.StockService;

@Service
public class StockServiceImplementation implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public String deleteStockById(int id) {
        try{
            stockRepository.deleteById(id);
            return "Stock deleted successfully";
        }catch(Exception e){
            return "Stock not found";
        }
    }

    @Override
    public Stock updateStockById(int id, Stock stock) {
       try{
           Stock stockToUpdate = stockRepository.findById(id).get();
           stockRepository.save(stockToUpdate);
           return stockToUpdate;
       }
       catch(Exception e){
           return null;
       }
    }

    @Override
    public Stock getStockById(int id) {
        
        try{
            return stockRepository.findById(id).get();
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public Stock addStock(Stock stock) {
        try{
            return stockRepository.save(stock);
        }catch(Exception e){
            return null;
        }
    }

    @Override
    public List<Stock> getAllStocks() {
        try{
            return stockRepository.findAll();
        }catch(Exception e){
            return null;
        }
    }
    
}
