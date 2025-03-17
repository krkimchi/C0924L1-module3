package com.codegym.ss12_jdbc.repository;

import com.codegym.ss12_jdbc.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    void add(Product product);
    void update(int id, Product product);
    void delete(int id);
    Product findById(int id);
    void save(Product product);
    int generateId();
    List<Product> findByName(String name);
}
