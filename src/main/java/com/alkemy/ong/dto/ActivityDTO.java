package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityDTO {

    private Long id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "Image cannot be empty")
    private String image;

}
