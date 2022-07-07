package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NewsDTO {

    private Long id;

    @NotNull(message = "The name cannot be empty")
    private String name;

    @NotNull(message = "The content cannot be empty")
    private String content;

    @NotNull(message = "The image cannot be empty")
    private String image;

    private CategoryDTO category;


}
