package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ContactDTO {

    private Long id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    private String phone;

    @Email(message = "Has to be an email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String message;
}
