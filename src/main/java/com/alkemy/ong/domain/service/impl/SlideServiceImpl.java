package com.alkemy.ong.domain.service.impl;

import com.alkemy.ong.auth.service.IS3Service;
import com.alkemy.ong.domain.service.ISlideService;
import com.alkemy.ong.dto.Base64ImageDTO;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.model.Slides;
import com.alkemy.ong.domain.repository.OrganizationRepository;
import com.alkemy.ong.domain.repository.SlidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SlideServiceImpl implements ISlideService {

    @Autowired
    private IS3Service is3Service;
    @Autowired
    private SlidesRepository slidesRepository;
    @Autowired
    private SlideMapper slidesMapper;
    @Autowired
    OrganizationRepository organizationRepository;


    @Transactional
    @Override
    public SlidesResponseDTO save(SlidesRequestDTO requestDTO) {
        Base64ImageDTO base64ImageDto = new Base64ImageDTO(requestDTO.getBase64Value(), requestDTO.getNameImage());
        String key = is3Service.uploadBase64Image(base64ImageDto);
        requestDTO.setImageUrl(is3Service.getObjectUrl(key));
        if (requestDTO.getOrderSlides() == null || requestDTO.getOrderSlides() < 0) {
            List<Integer> orden = new ArrayList<>();
            List<Slides> slidesList = this.slidesRepository.findAll();
            for (Slides aux : slidesList) {
                orden.add(aux.getOrderSlides());
            }
            if (orden.isEmpty()) {
                requestDTO.setOrderSlides(1);
            } else {
                requestDTO.setOrderSlides(findLargerInteger(orden) + 1);
            }
        }
        Slides entity = this.slidesMapper.requestDto2SlidesEntity(requestDTO);
        return this.slidesMapper.entitySlides2responseDto(this.slidesRepository.save(entity));
    }

    @Override
    public List<SlidesDTO> findAll() {
        List<Slides> entities = this.slidesRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The Slides list is empty");
        }
        List<SlidesDTO> result = this.slidesMapper.entitySlidesList2SlidesDtoList(entities);
        result.sort(Comparator.comparing(SlidesDTO::getOrderSlides));
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Slides slide = slidesRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ID: " + id));
        slidesRepository.delete(slide);
    }

    @Override
    public SlidesResponseDTO getSlidesById(Long id) {
        Slides slidesEntity = slidesRepository.findById(id).orElseThrow(() -> new NotFoundException("Slides with id provided not found"));
        return slidesMapper.entitySlides2responseDto(slidesEntity);

    }


    @Override
    public List<SlidesResponseDTO> getSlidesByOrganiztionId(Long organizationId) {

        Optional<Organization> organization = organizationRepository.findById(organizationId);

        if (!organization.isPresent()) {
            throw new NotFoundException("Organization with id provided not found");
        } else {
            List<Slides> organizationSlidesList;
            organizationSlidesList = slidesRepository.findByOrganizations(organization.get());
            return slidesMapper.organizationSlidesList2organizationSlidesDto(organizationSlidesList);
        }
    }

    public int findLargerInteger(List<Integer> integers) {
        integers.sort(Collections.reverseOrder());
        return integers.get(0);
    }

}
