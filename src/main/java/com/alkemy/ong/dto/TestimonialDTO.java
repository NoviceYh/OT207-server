package com.alkemy.ong.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestimonialDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "image")
    private String image;

    @NotNull(message = "Content cannot be null")
    @Column(name = "content")
    private String content;

}
