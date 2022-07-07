package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.domain.service.ICategoryService;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.domain.model.Category;
import com.alkemy.ong.domain.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements ICategoryService {


    private static final String URI = Url.URL + Url.CATEGORY_URI + Url.PAGE_URI;
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
        return categoryMapper.CategoryListResponseDtoList(categories);
    }

    @Transactional(readOnly = true)
    @Override
    public PageDTO<CategoryDTO> getAllCategoriesPageable(Integer page) {
        PageDTO<CategoryDTO> categoryPageDTO = new PageDTO<>();
        Page<Category> categories = this.categoryRepository.findAll(PageRequest.of(page - 1, Url.MAX_PAGE, Sort.by("name")));
        return Url.pagination(categoryPageDTO,categories,page,this.categoryMapper.categoryEntityPage2Dto(categories),URI);
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
            return categoryMapper.categoryEntityToCategoryDto(result);

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


