package com.alkemy.ong.dto;

import com.alkemy.ong.domain.model.Organization;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SlidesRequestDTO {
    @NotEmpty
    private String nameImage;
    private String imageUrl;
    private String text;
    private Integer orderSlides;
    private Organization organizations;
    @NotEmpty
    private String base64Value;
}
