package com.example.cosmocatsintergalacticmarketplacebackend.domain;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.enums.RarityLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    private UUID id;
    private String name;
    private BigDecimal price;
    private String description;
    private UUID categoryId;
    private String planetOrigin;
    private RarityLevel rarityLevel;
    private boolean isInStock;
}
