package com.divya.inventorymanagement.Service;

import java.util.List;

import com.divya.inventorymanagement.Model.Product;

public interface ProductService {

    public Product addProduct(Product product);

    public List<Product> getProducts();

    public Product getProductById(Integer id);

    public Product updateProduct(Integer id, Product product);

    public String deleteProduct(Integer id);
    
}
