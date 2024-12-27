package com.example.cosmocatsintergalacticmarketplacebackend.service;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Product;
import com.example.cosmocatsintergalacticmarketplacebackend.domain.enums.RarityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ProductService.class})
class ProductServiceImplTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService();
        productService.init();
    }

    @Test
    void getProductById_NonExistingProduct_ReturnsEmptyOptional() {
        Optional<Product> found = productService.getProductById(UUID.randomUUID());

        assertThat(found).isNotPresent();
    }

    @Test
    void getAllProducts_ReturnsAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertThat(products).hasSize(3);
        assertThat(products.getFirst().getName()).isEqualTo("Anti-Gravity Yarn Ball");
    }

    @Test
    void getProductById_ExistingProduct_ReturnsProduct() {
        Product product = productService.getAllProducts().getFirst();
        Optional<Product> found = productService.getProductById(product.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo(product.getName());
    }

    @Test
    void createProduct_ValidProduct_AddsToProductList() {
        Product newProduct = Product.builder()
                .name("Cosmic Catnip")
                .price(new BigDecimal("10.99"))
                .description("Out-of-this-world catnip.")
                .categoryId(UUID.randomUUID())
                .planetOrigin("Catnip-Prime")
                .rarityLevel(RarityLevel.COMMON)
                .isInStock(true)
                .build();

        Product createdProduct = productService.createProduct(newProduct);

        assertThat(createdProduct.getId()).isNotNull();
        assertThat(productService.getAllProducts()).hasSize(4);
    }

    @Test
    void updateProduct_ExistingProduct_UpdatesDetails() {
        Product existingProduct = productService.getAllProducts().getFirst();
        Product updatedProduct = Product.builder()
                .name("Updated Name")
                .price(new BigDecimal("50.99"))
                .description("Updated description.")
                .categoryId(existingProduct.getCategoryId())
                .planetOrigin("Updated Origin")
                .rarityLevel(RarityLevel.LEGENDARY)
                .isInStock(false)
                .build();

        Optional<Product> updated = productService.updateProduct(existingProduct.getId(), updatedProduct);

        assertThat(updated).isPresent();
        assertThat(updated.get().getName()).isEqualTo("Updated Name");
    }

    @Test
    void deleteProduct_ExistingProduct_RemovesFromList() {
        Product product = productService.getAllProducts().getFirst();
        int initialSize = productService.getAllProducts().size();

        productService.deleteProduct(product.getId());

        assertThat(productService.getAllProducts()).hasSize(initialSize - 1);
        assertThat(productService.getProductById(product.getId())).isEmpty();
    }
}
