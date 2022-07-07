package com.alkemy.ong.domain.service;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;

import java.util.List;

public interface ISlideService {

    SlidesResponseDTO save(SlidesRequestDTO requestDTO);

    List<SlidesDTO> findAll();

    void deleteById(Long id);

    SlidesResponseDTO getSlidesById(Long id);

    List<SlidesResponseDTO> getSlidesByOrganiztionId(Long organizationId);
}
