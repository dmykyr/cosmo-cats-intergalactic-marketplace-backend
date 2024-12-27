package com.example.cosmocatsintergalacticmarketplacebackend.service.interfaces;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(UUID id);
    Product createProduct(Product product);
    Optional<Product> updateProduct(UUID id, Product product);
    void deleteProduct (UUID id);
}
