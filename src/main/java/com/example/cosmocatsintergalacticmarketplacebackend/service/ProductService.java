package com.example.cosmocatsintergalacticmarketplacebackend.service;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Product;
import com.example.cosmocatsintergalacticmarketplacebackend.domain.enums.RarityLevel;
import com.example.cosmocatsintergalacticmarketplacebackend.service.interfaces.IProductService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductService implements IProductService {
    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void init() {
        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Anti-Gravity Yarn Ball")
                .price(new BigDecimal("25.99"))
                .description("A yarn ball that floats, providing endless fun for Cosmo Cats.")
                .categoryId(UUID.fromString("11111111-1111-1111-1111-111111111111"))
                .planetOrigin("Catnip-9")
                .rarityLevel(RarityLevel.RARE)
                .isInStock(true)
                .build());

        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Zero-Gravity Nap Pod")
                .price(new BigDecimal("999.99"))
                .description("A cozy pod designed for the perfect weightless nap.")
                .categoryId(UUID.fromString("22222222-2222-2222-2222-222222222222"))
                .planetOrigin("Feline-Prime")
                .rarityLevel(RarityLevel.RARE)
                .isInStock(true)
                .build());

        products.add(Product.builder()
                .id(UUID.randomUUID())
                .name("Laser Pointer Infinity Edition")
                .price(new BigDecimal("49.99"))
                .description("A laser pointer with infinite battery life for endless playtime.")
                .categoryId(UUID.fromString("33333333-3333-3333-3333-333333333333"))
                .planetOrigin("Lightbeam-42")
                .rarityLevel(RarityLevel.LEGENDARY)
                .isInStock(false)
                .build());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductById(UUID id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Product createProduct(Product product) {
        product.setId(UUID.randomUUID());
        products.add(product);
        return product;
    }

    public Optional<Product> updateProduct(UUID id, Product updatedProduct) {
        return getProductById(id).map(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setCategoryId(updatedProduct.getCategoryId());
            existingProduct.setPlanetOrigin(updatedProduct.getPlanetOrigin());
            existingProduct.setRarityLevel(updatedProduct.getRarityLevel());
            existingProduct.setInStock(updatedProduct.isInStock());
            return existingProduct;
        });
    }

    public void deleteProduct(UUID id) {
        products.removeIf(p -> p.getId().equals(id));
    }
}
