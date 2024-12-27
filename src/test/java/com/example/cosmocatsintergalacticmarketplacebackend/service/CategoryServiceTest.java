package com.example.cosmocatsintergalacticmarketplacebackend.service;

import com.example.cosmocatsintergalacticmarketplacebackend.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryServiceTest {

    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryService();
        categoryService.init();
    }

    @Test
    void getAllCategories_ShouldReturnAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        assertThat(categories).hasSize(4);
        assertThat(categories.getFirst().getName()).isEqualTo("Toys");
    }

    @Test
    void getCategoryById_WithExistingId_ShouldReturnCategory() {
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");

        Optional<Category> category = categoryService.getCategoryById(id);

        assertThat(category).isPresent();
        assertThat(category.get().getName()).isEqualTo("Toys");
    }

    @Test
    void getCategoryById_WithNonExistingId_ShouldReturnEmpty() {
        UUID nonExistingId = UUID.randomUUID();

        Optional<Category> category = categoryService.getCategoryById(nonExistingId);

        assertThat(category).isEmpty();
    }

    @Test
    void createCategory_ShouldAddNewCategory() {
        Category newCategory = new Category(null, "Test Category", "Test Description");

        Category createdCategory = categoryService.createCategory(newCategory);

        assertThat(createdCategory.getId()).isNotNull();
        assertThat(categoryService.getAllCategories()).hasSize(5);
        assertThat(categoryService.getAllCategories())
                .extracting(Category::getName)
                .contains("Test Category");
    }

    @Test
    void updateCategory_WithExistingId_ShouldUpdateCategory() {
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        Category updatedCategory = new Category(id, "Updated Toys", "Updated Description");

        Optional<Category> result = categoryService.updateCategory(id, updatedCategory);

        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Updated Toys");
        assertThat(result.get().getDescription()).isEqualTo("Updated Description");
    }

    @Test
    void updateCategory_WithNonExistingId_ShouldReturnEmpty() {
        UUID nonExistingId = UUID.randomUUID();
        Category updatedCategory = new Category(nonExistingId, "Non-Existing", "Doesn't Exist");

        Optional<Category> result = categoryService.updateCategory(nonExistingId, updatedCategory);

        assertThat(result).isEmpty();
    }

    @Test
    void deleteCategory_WithExistingId_ShouldRemoveCategory() {
        UUID id = UUID.fromString("11111111-1111-1111-1111-111111111111");
        int initialSize = categoryService.getAllCategories().size();

        boolean result = categoryService.deleteCategory(id);

        assertThat(result).isTrue();
        assertThat(categoryService.getAllCategories()).hasSize(initialSize - 1);
        assertThat(categoryService.getAllCategories())
                .extracting(Category::getId)
                .doesNotContain(id);
    }

    @Test
    void deleteCategory_WithNonExistingId_ShouldReturnFalse() {
        UUID nonExistingId = UUID.randomUUID();

        boolean result = categoryService.deleteCategory(nonExistingId);

        assertThat(result).isFalse();
        assertThat(categoryService.getAllCategories()).hasSize(4);
    }
}