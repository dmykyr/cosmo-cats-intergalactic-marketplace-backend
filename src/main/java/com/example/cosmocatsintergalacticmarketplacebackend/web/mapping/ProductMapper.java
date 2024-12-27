package com.example.cosmocatsintergalacticmarketplacebackend.web.mapping;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Product;
import com.example.cosmocatsintergalacticmarketplacebackend.domain.enums.RarityLevel;
import com.example.cosmocatsintergalacticmarketplacebackend.dto.product.ProductRequest;
import com.example.cosmocatsintergalacticmarketplacebackend.dto.product.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rarityLevel", source = "rarityLevel", qualifiedByName = "mapRarityLevel")
    @Mapping(target = "isInStock", source = "inStock")
    Product toProduct(ProductRequest productRequest);

    @Mapping(target = "rarityLevel", source = "rarityLevel", qualifiedByName = "rarityLevelToString")
    @Mapping(target = "isInStock", source = "inStock")
    ProductResponse toResponse(Product product);

    List<ProductResponse> toResponseList(List<Product> products);

    @Named("mapRarityLevel")
    default RarityLevel mapRarityLevel(String rarityLevel) {
        return RarityLevel.valueOf(rarityLevel.toUpperCase());
    }

    @Named("rarityLevelToString")
    default String rarityLevelToString(RarityLevel rarityLevel) {
        return rarityLevel != null ? rarityLevel.name() : null;
    }
}
