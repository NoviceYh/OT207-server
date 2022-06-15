package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.dto.CategoryPageDTO;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private static final int MAX_PAGE = 10;
    private static final String URL = "http://localhost:8080/categories/page?page=";
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDto) {
        Optional<Category> categoryEntity = this.categoryRepository.findByName(categoryDto.getName());
        if (categoryEntity.isPresent()) {
            throw new ConflictException("There is already a category with that name");
        }
        Category CategoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
        Category savedEntity = categoryRepository.save(CategoryEntity);
        return categoryMapper.categoryEntityToCategoryDto(savedEntity);
    }

    @Override
    public List<CategoryDtoName> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<CategoryDtoName> allCategories = categoryMapper.CategoryListResponseDtoList(categories);
        return allCategories;
    }

    @Override
    public CategoryPageDTO getAllCategoriesPageable(Integer page) {
        CategoryPageDTO categoryPageDTO = new CategoryPageDTO();
        Page<Category> categories = categoryRepository.findAll(PageRequest.of(page - 1, MAX_PAGE, Sort.by("name")));
        if (categories.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        if (categories.hasNext()) {
            categoryPageDTO.setNext(URL + Integer.toString(page + 1));
        }
        if (!categories.isFirst()) {
            categoryPageDTO.setPrevious(URL + Integer.toString(page - 1));
        }
        categoryPageDTO.setCurrent(Integer.toString(page));
        categoryPageDTO.setCategoryList(this.categoryMapper.categoryEntityPage2Dto(categories));

        return categoryPageDTO;
    }


    public CategoryDTO getCategoryById(Long id) {
        Category categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        return categoryMapper.categoryEntityToCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto) {

        if (categoryRepository.existsById(categoryId)) {

            Category category = categoryRepository.getById(categoryId);
            category = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
            Category result = categoryRepository.save(category);
            CategoryDTO newCategoryDto = categoryMapper.categoryEntityToCategoryDto(result);

            return newCategoryDto;

        } else {
            throw new NotFoundException("Category id not found");
        }

    }

    @Override
    public void deleteCategory(Long id) throws NotFoundException {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Category id does not exist");
        }

    }
}


