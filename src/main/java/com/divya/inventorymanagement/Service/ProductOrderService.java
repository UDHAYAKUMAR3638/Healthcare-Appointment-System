package com.divya.inventorymanagement.Service;

import java.util.List;

import com.divya.inventorymanagement.Model.ProductOrder;

public interface ProductOrderService {

     String deleteProductOrderById(int id);

     ProductOrder updateProductOrderById(int id, ProductOrder productOrder);

     ProductOrder getProductOrderById(int id);

     List<ProductOrder> getAllProductOrders();

     ProductOrder addProductOrder(ProductOrder productOrder);

}
