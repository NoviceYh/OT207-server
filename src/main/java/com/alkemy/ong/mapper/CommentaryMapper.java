package com.alkemy.ong.mapper;

import com.alkemy.ong.domain.model.Commentary;
import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentaryMapper {

    public Commentary commentaryDTO2Entity(CommentaryDTO dto) {
        Commentary entity = new Commentary();
        entity.setBody(dto.getBody());
        entity.setNews(dto.getNews());
        entity.setUserEntity(dto.getUser());
        return entity;
    }

    public CommentaryDTO commentaryEntity2DTO(Commentary entity) {
        CommentaryDTO dto = new CommentaryDTO();
        dto.setId(entity.getId());
        dto.setBody(entity.getBody());
        dto.setNews(entity.getNews());
        dto.setUser(entity.getUserEntity());
        return dto;
    }

    public CommentaryBodyDTO commentaryEntityBodyToDTO(Commentary entity) {
        CommentaryBodyDTO dto = new CommentaryBodyDTO();
        dto.setBody(entity.getBody());
        return dto;
    }

    public List<CommentaryBodyDTO> entityListToDtoList(List<Commentary> entityList) {        
        List<CommentaryBodyDTO> DtoList = new ArrayList();
        entityList.forEach(entity -> DtoList.add(commentaryEntityBodyToDTO(entity)));
        return DtoList;
    }

    public void entityCommentaryRefreshValues(Commentary entity, CommentaryBodyDTO dto){
        entity.setBody(dto.getBody());
    }
}
