package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.domain.model.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewsMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    public News newsDTO2Entity(@NotNull NewsDTO dto) {
        News entity = new News();
        setDtoAttributes2Entity(dto, entity);
        return entity;
    }

    public NewsDTO newsEntity2DTO(@NotNull News entity) {
        NewsDTO dto = new NewsDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCategory(this.categoryMapper.categoryEntityToCategoryDto(entity.getCategory()));
        return dto;
    }

    public void newsDTO2EntityWithId(News entity, NewsDTO dto) {
        setDtoAttributes2Entity(dto, entity);
    }

    private void setDtoAttributes2Entity(NewsDTO dto, News entity) {
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
    }

    public List<NewsDTO> newsEntityPage2Dto(Page<News> news) {
        List<NewsDTO> newsDTOS = new ArrayList<>();
        news.getContent().forEach(entity -> newsDTOS.add(this.newsEntity2DTO(entity)));
        return newsDTOS;
    }
}
