package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {

    public Category categoryDtoToCategoryEntity(CategoryDTO categoryDto) {
        Category categoryEntity = new Category();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setDescription(categoryDto.getDescription());
        categoryEntity.setImage(categoryDto.getImage());
        return categoryEntity;
    }
    public CategoryDTO categoryEntityToCategoryDto(Category savedEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(savedEntity.getId());
        categoryDTO.setName(savedEntity.getName());
        categoryDTO.setDescription(savedEntity.getDescription());
        categoryDTO.setImage(savedEntity.getImage());
        return categoryDTO;
    }
    public CategoryDtoName categoryResponseDto(@NotNull Category category) {
        CategoryDtoName dto = new CategoryDtoName();
        dto.setName(category.getName());
        return dto;
    }

    public List<CategoryDtoName> CategoryListResponseDtoList(@NotEmpty List<Category> categories) {
        List<CategoryDtoName> responseDTos = new ArrayList<>();
        for (Category category : categories) {
            responseDTos.add(this.categoryResponseDto(category));
        }
        return responseDTos;
    }

    public List<CategoryDTO> categoryEntityPage2Dto(Page<Category> entities){
        List<CategoryDTO> dtos = new ArrayList<>();
        entities.getContent().forEach(entity -> dtos.add(this.categoryEntityToCategoryDto(entity)));
        return dtos;
    }
}
