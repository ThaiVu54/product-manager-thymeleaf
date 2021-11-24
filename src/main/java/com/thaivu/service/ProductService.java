package com.thaivu.service;

import com.thaivu.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService implements IProductService {
    private static final Map<Integer, Product> products;

    static {
        products = new HashMap<>();
        products.put(1, new Product(1, "T", 12, "des1", "nsx1"));
        products.put(2, new Product(2, "H", 12, "des2", "nsx2"));
        products.put(3, new Product(3, "A", 12, "des3", "nsx3"));
        products.put(4, new Product(4, "I", 12, "des4", "nsx4"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void save(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void update(int id, Product product) {
        products.put(id, product);
    }

    @Override
    public void remote(int id) {
        products.remove(id);
    }
}
