package com.alkemy.ong.controller;

import com.alkemy.ong.controller.documentation.CategoryControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;


import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(Url.CATEGORY_URI)
public class CategoryController implements CategoryControllerDoc {

    @Autowired
    private ICategoryService categoryService;

    @Override
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<CategoryDtoName>> getAllCategories() {
        List<CategoryDtoName> categoriesDTOs = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoriesDTOs);
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<?> getAllCategoriesPageable(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        PageDTO<CategoryDTO> categoryPage = categoryService.getAllCategoriesPageable(page);
        return ResponseEntity.ok(categoryPage);
    }

    @Override
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> modifyCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDto) {
        CategoryDTO newCategory = categoryService.modifyCategory(categoryId, categoryDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCategory);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryDTO(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

}
