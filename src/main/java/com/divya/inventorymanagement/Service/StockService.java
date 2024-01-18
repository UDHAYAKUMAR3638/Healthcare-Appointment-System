package com.divya.inventorymanagement.Service;

import java.util.List;

import com.divya.inventorymanagement.Model.Stock;

public interface StockService {

    Stock addStock(Stock stock);

    List<Stock> getAllStocks();

    Stock getStockById(int id);

    Stock updateStockById(int id, Stock stock);

    String deleteStockById(int id);
    
}
