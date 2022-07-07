package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private String description;

    private String image;

}