package com.divya.inventorymanagement.Service.ServiceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divya.inventorymanagement.Model.Product;
import com.divya.inventorymanagement.Repository.ProductRepository;
import com.divya.inventorymanagement.Service.ProductService;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Product addProduct(Product product) {
        try {
            return productRepository.save(product);
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public List<Product> getProducts() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
    @Override
    public Product getProductById(Integer id) {
       try {
           return productRepository.findById(id).get();
       } catch (Exception e) {
           return null;
       } 
    
    }
    @Override
    public Product updateProduct(Integer id, Product product) {
        try{
            Product product1 = productRepository.findById(id).get();
            if(product1 == null)
                return null;
            else
                return productRepository.save(product);
        }catch(Exception e){
            return null;
        }
    }
    @Override
    public String deleteProduct(Integer id) {
        try{
            Product product = productRepository.findById(id).get();
            if(product == null)
                return "Product not found";
            else{
                productRepository.delete(product);
                return "Product deleted successfully";
            }
        }catch(Exception e){
            return "Product not found";
        }
    }

}
