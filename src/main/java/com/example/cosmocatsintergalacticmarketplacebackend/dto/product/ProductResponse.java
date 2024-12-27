package com.example.cosmocatsintergalacticmarketplacebackend.dto.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;
    private UUID categoryId;
    private String planetOrigin;
    private String rarityLevel;
    private boolean isInStock;
}
