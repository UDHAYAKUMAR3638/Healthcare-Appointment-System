package com.divya.inventorymanagement.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.divya.inventorymanagement.Model.ProductOrder;
import com.divya.inventorymanagement.Service.ProductOrderService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/productorder")
public class ProductOrderController {

    @Autowired
    private ProductOrderService productOrderService;

    // Post request to add product order
    @PostMapping("/addproductorder")
    public ResponseEntity<ProductOrder> addProductOrder(@RequestBody ProductOrder productOrder){
        try{
            return ResponseEntity.ok(productOrderService.addProductOrder(productOrder));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get all product orders
    @GetMapping("/getallproductorders")
    public ResponseEntity<List<ProductOrder>> getAllProductOrders(){
        try{
            return ResponseEntity.ok(productOrderService.getAllProductOrders());
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Get request to get product order by id
    @GetMapping("/getproductorderbyid/{id}")
    public ResponseEntity<ProductOrder> getProductOrderById(int id){
        try{
            return ResponseEntity.ok(productOrderService.getProductOrderById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Put request to update product order by id
    @PutMapping("/updateproductorderbyid/{id}")
    public ResponseEntity<ProductOrder> updateProductOrderById(int id, ProductOrder productOrder){
        try{
            return ResponseEntity.ok(productOrderService.updateProductOrderById(id, productOrder));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    // Delete request to delete product order by id
    @DeleteMapping("/deleteproductorderbyid/{id}")
    public ResponseEntity<String> deleteProductOrderById(int id){
        try{
            return ResponseEntity.ok(productOrderService.deleteProductOrderById(id));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    
}
