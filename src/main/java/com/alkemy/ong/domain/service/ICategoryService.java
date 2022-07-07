package com.alkemy.ong.domain.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.dto.PageDTO;

import java.util.List;


public interface ICategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDto);
  
    List<CategoryDtoName> getAllCategories();

    PageDTO<CategoryDTO> getAllCategoriesPageable(Integer page);

    CategoryDTO getCategoryById(Long id);
  
    void deleteCategory(Long id);
  
    CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto);

}
