package com.codegym.ss11_mvc.service;

import com.codegym.ss11_mvc.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void add(Product product);
    void update(int id, Product product);
    void delete(int id);
    Product findById(int id);
    void save(Product product);
    int generateId();
    List<Product> findByName(String name);
}
