package com.example.cosmocatsintergalacticmarketplacebackend.web;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Product;
import com.example.cosmocatsintergalacticmarketplacebackend.domain.enums.RarityLevel;
import com.example.cosmocatsintergalacticmarketplacebackend.dto.product.ProductRequest;
import com.example.cosmocatsintergalacticmarketplacebackend.dto.product.ProductResponse;
import com.example.cosmocatsintergalacticmarketplacebackend.service.interfaces.IProductService;
import com.example.cosmocatsintergalacticmarketplacebackend.web.mapping.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @MockBean
    private ProductMapper productMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getAllProducts_ReturnsProducts() throws Exception {
        Product product = createTestProduct();
        ProductResponse response = createTestProductResponse();

        when(productService.getAllProducts()).thenReturn(Collections.singletonList(product));
        when(productMapper.toResponseList(any())).thenReturn(List.of(response));

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(response.getName()));

        verify(productService).getAllProducts();
    }

    @Test
    void getProductById_ProductExists_ReturnsProduct() throws Exception {
        UUID id = UUID.randomUUID();
        Product product = createTestProduct();
        ProductResponse response = createTestProductResponse();

        when(productService.getProductById(id)).thenReturn(Optional.of(product));
        when(productMapper.toResponse(product)).thenReturn(response);

        mockMvc.perform(get("/api/v1/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(response.getName()));
    }

    @Test
    void createProduct_ValidRequest_CreatesProduct() throws Exception {
        ProductRequest request = createTestProductRequest();
        Product product = createTestProduct();

        when(productMapper.toProduct(request)).thenReturn(product);
        when(productService.createProduct(product)).thenReturn(product);

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void updateProduct_ProductExists_UpdatesProduct() throws Exception {
        UUID id = UUID.randomUUID();
        ProductRequest request = createTestProductRequest();
        Product product = createTestProduct();

        when(productMapper.toProduct(request)).thenReturn(product);
        when(productService.updateProduct(id, product)).thenReturn(Optional.of(product));

        mockMvc.perform(put("/api/v1/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteProduct_ProductExists_DeletesProduct() throws Exception {
        UUID id = UUID.randomUUID();

        mockMvc.perform(delete("/api/v1/products/" + id))
                .andExpect(status().isNoContent());

        verify(productService).deleteProduct(id);
    }

    private Product createTestProduct() {
        return Product.builder()
                .id(UUID.randomUUID())
                .name("Test Product")
                .price(new BigDecimal("99.99"))
                .description("Test Description")
                .categoryId(UUID.randomUUID())
                .planetOrigin("Test Planet")
                .rarityLevel(RarityLevel.RARE)
                .isInStock(true)
                .build();
    }

    private ProductRequest createTestProductRequest() {
        ProductRequest request = new ProductRequest();
        request.setName("Test Product");
        request.setPrice(new BigDecimal("99.99"));
        request.setDescription("Test Description");
        request.setCategoryId(UUID.randomUUID());
        request.setPlanetOrigin("Test Planet");
        request.setRarityLevel("RARE");
        request.setInStock(true);
        return request;
    }

    private ProductResponse createTestProductResponse() {
        ProductResponse response = new ProductResponse();
        response.setName("Test Product");
        return response;
    }
}