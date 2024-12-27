package com.example.cosmocatsintergalacticmarketplacebackend.service;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Category;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class CategoryService {
    private final List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init() {
        categories.add(new Category(UUID.fromString("11111111-1111-1111-1111-111111111111"), "Toys", "Fun and interactive toys for Cosmo Cats."));
        categories.add(new Category(UUID.fromString("22222222-2222-2222-2222-222222222222"), "Comfort", "Comfort items for relaxation and sleep."));
        categories.add(new Category(UUID.fromString("33333333-3333-3333-3333-333333333333"), "Gadgets", "Innovative gadgets for feline explorers."));
        categories.add(new Category(UUID.fromString("44444444-4444-4444-4444-444444444444"), "Accessories", "Stylish accessories for fashionable cats."));
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }

    public Optional<Category> getCategoryById(UUID id) {
        return categories.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public Category createCategory(Category category) {
        category.setId(UUID.randomUUID());
        categories.add(category);
        return category;
    }

    public Optional<Category> updateCategory(UUID id, Category updatedCategory) {
        return getCategoryById(id).map(existingCategory -> {
            existingCategory.setName(updatedCategory.getName());
            existingCategory.setDescription(updatedCategory.getDescription());
            return existingCategory;
        });
    }

    public boolean deleteCategory(UUID id) {
        return categories.removeIf(c -> c.getId().equals(id));
    }
}
