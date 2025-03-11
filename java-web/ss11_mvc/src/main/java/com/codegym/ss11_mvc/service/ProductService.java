package com.codegym.ss11_mvc.service;

import com.codegym.ss11_mvc.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private static List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Laptop", 1500, "Laptop Gaming", "Razer"));
        products.add(new Product(2, "Smartphone", 800, "Điện thoại", "Apple"));
        products.add(new Product(3, "Tablet", 400, "Máy tính bảng", "Apple"));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void add(Product product) {
        products.add(product);
    }

    @Override
    public void update(int id, Product product) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(product.getName());
                p.setPrice(product.getPrice());
                p.setDescription(product.getDescription());
                p.setManufacturer(product.getManufacturer());
                return;
            }
        }
    }

    @Override
    public void delete(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    @Override
    public Product findById(int id) {
        return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public int generateId() {
        int maxId = 0;
        for (Product p : products) {
            if (p.getId() > maxId) {
                maxId = p.getId();
            }
        }
        return maxId + 1;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }
}
