package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CategoryDtoName {

    @NotNull(message = "Name cannot be null")
    private String name;
}
