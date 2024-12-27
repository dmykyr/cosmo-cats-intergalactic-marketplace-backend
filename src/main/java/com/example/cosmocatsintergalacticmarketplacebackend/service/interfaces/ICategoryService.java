package com.example.cosmocatsintergalacticmarketplacebackend.service.interfaces;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Category;
import com.example.cosmocatsintergalacticmarketplacebackend.dto.category.CategoryResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ICategoryService{
    List<CategoryResponse> getAllCategories();
    Optional<Category> getCategoryById(UUID id);
}
