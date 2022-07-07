package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
public class OrganizationDTO {

    private Long id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Image cannot be empty")
    private String image;

    @Digits(integer = 10, fraction = 0)
    @Size(min = 9, max = 10)
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    private String address;

    @Column(nullable = false)
    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty(message = "welcome text cannot be empty")
    private String welcomeText;

    @Column(columnDefinition = "text", nullable = false)
    @NotEmpty(message = "About text cannot be empty")
    private String aboutUsText;

    private List<SlidesDTO> slidesDTOS;

}
