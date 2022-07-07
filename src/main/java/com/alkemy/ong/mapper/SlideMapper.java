package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.domain.model.Slides;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    @Autowired
    OrganizationMapper organizationMapper;

    public Slides requestDto2SlidesEntity(@NotNull SlidesRequestDTO requestDTO) {
        Slides entity = new Slides();
        entity.setImageUrl(requestDTO.getImageUrl());
        entity.setOrderSlides(requestDTO.getOrderSlides());
        entity.setOrganizations(requestDTO.getOrganizations());
        entity.setText(requestDTO.getText());
        return entity;
    }

    public SlidesResponseDTO entitySlides2responseDto(@NotNull Slides entity) {
        SlidesResponseDTO responseDTO = new SlidesResponseDTO();
        responseDTO.setId(entity.getId());
        responseDTO.setImageUrl(entity.getImageUrl());
        responseDTO.setOrderSlides(entity.getOrderSlides());
        responseDTO.setText(entity.getText());
        responseDTO.setOrganizations(organizationMapper.organizationEntity2DTO(entity.getOrganizations()));
        return responseDTO;
    }

    public List<SlidesResponseDTO> entitySlidesList2responseDtoList(@NotNull List<Slides> entities) {
        List<SlidesResponseDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(this.entitySlides2responseDto(entity)));
        return dtos;
    }

    public SlidesDTO entitySlides2SlidesDto(Slides entity){
        SlidesDTO dto = new SlidesDTO();
        dto.setId(entity.getId());
        dto.setOrderSlides(entity.getOrderSlides());
        dto.setImageUrl(entity.getImageUrl());
        return dto;
    }

    public List<SlidesDTO> entitySlidesList2SlidesDtoList(List<Slides> entities) {
        List<SlidesDTO> dtos = new ArrayList<>();
        entities.forEach(entity -> dtos.add(this.entitySlides2SlidesDto(entity)));
        return dtos;
    }


    public List<SlidesResponseDTO> organizationSlidesList2organizationSlidesDto(List<Slides> organizationSlidesList){
        List<SlidesResponseDTO> organizationSlidesDto = new ArrayList<>();
        organizationSlidesList.forEach(entity -> organizationSlidesDto.add(this.entitySlides2responseDto(entity)));
        return organizationSlidesDto;
}

}
