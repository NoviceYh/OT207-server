package com.alkemy.ong.mapper;

import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.repository.OrganizationRepository;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDTO;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
public class OrganizationMapper {

    private OrganizationRepository organizationRepository;

    public OrganizationDTO organizationEntity2DTO(Organization entity) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setWelcomeText(entity.getWelcomeText());
        dto.setAboutUsText(entity.getAboutUsText());
        return dto;
    }

    public OrganizationDTO organizationEntity2DTOSlides(@NotNull Organization entity,  List<SlidesDTO> slidesDTOS) {
        OrganizationDTO dto = new OrganizationDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setWelcomeText(entity.getWelcomeText());
        dto.setAboutUsText(entity.getAboutUsText());
        dto.setSlidesDTOS(slidesDTOS);
        return dto;
    }

    public Organization organizationUpdateDTO2Entity(@NotNull OrganizationUpdateDTO orgUpdate, @NotNull Organization org){

        org.setName(orgUpdate.getName());
        org.setImage(orgUpdate.getImage());
        org.setAddress(orgUpdate.getAddress());
        org.setPhone(orgUpdate.getPhone());
        org.setEmail(orgUpdate.getEmail());
        org.setWelcomeText(orgUpdate.getWelcomeText());
        org.setAboutUsText(orgUpdate.getAboutUsText());
        org.setFacebookUrl(orgUpdate.getFacebookUrl());
        org.setInstagramUrl(orgUpdate.getInstagramUrl());
        org.setLinkedinUrl(orgUpdate.getLinkedinUrl());

        return org;

    }

    public OrganizationUpdateDTO entity2organizationUpdateDTO(Organization org) {

        OrganizationUpdateDTO organizationUpdateDto = new OrganizationUpdateDTO();

        organizationUpdateDto.setId(org.getId());
        organizationUpdateDto.setName(org.getName());
        organizationUpdateDto.setImage(org.getImage());
        organizationUpdateDto.setAddress(org.getAddress());
        organizationUpdateDto.setPhone(org.getPhone());
        organizationUpdateDto.setEmail(org.getEmail());
        organizationUpdateDto.setWelcomeText(org.getWelcomeText());
        organizationUpdateDto.setAboutUsText(org.getAboutUsText());
        organizationUpdateDto.setFacebookUrl(organizationUpdateDto.getFacebookUrl());
        organizationUpdateDto.setInstagramUrl(organizationUpdateDto.getInstagramUrl());
        organizationUpdateDto.setLinkedinUrl(organizationUpdateDto.getLinkedinUrl());

        return organizationUpdateDto;

    }

    public Organization organizationDto2Entity(OrganizationDTO organizationDto) {
        Organization organization = new Organization();
        organization.setId(organizationDto.getId());
        organization.setName(organizationDto.getName());
        organization.setImage(organizationDto.getImage());
        organization.setPhone(organizationDto.getPhone());
        organization.setAddress(organizationDto.getAddress());
        organization.setEmail(organizationDto.getEmail());
        organization.setWelcomeText(organizationDto.getWelcomeText());
        organization.setAboutUsText(organizationDto.getAboutUsText());
        return organization;
    }


}
