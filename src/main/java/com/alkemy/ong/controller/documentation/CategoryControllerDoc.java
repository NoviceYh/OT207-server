package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface CategoryControllerDoc {

    @Operation(summary = "Add a new category to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create category",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<CategoryDTO> addCategory(CategoryDTO categoryDTO, BindingResult result);

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all categories",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<List<CategoryDtoName>> getAllCategories();

    @Operation(summary = "Get all categories for paginated user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all categories",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "The list is empty",
                    content = @Content)})
    ResponseEntity<?> getAllCategoriesPageable(Integer page);

    @Operation(summary = "Update category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update category",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<CategoryDTO> modifyCategory(Long categoryId, CategoryDTO categoryDto);

    @Operation(summary = "Delete a category by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the category",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    ResponseEntity<Void> deleteCategory(Long id);

    @Operation(summary = "Get a category by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the category",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<CategoryDTO> getCategoryDTO(Long id);

}
