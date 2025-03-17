package com.codegym.ss12_jdbc.service;

import com.codegym.ss12_jdbc.model.Product;
import com.codegym.ss12_jdbc.repository.IProductRepository;
import com.codegym.ss12_jdbc.repository.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {
    private final IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public void add(Product product) {
        productRepository.add(product);
    }

    @Override
    public void update(int id, Product product) {
        productRepository.update(id, product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public int generateId() {
        return productRepository.generateId();
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }
}