package com.alkemy.ong.auth.dto;

import com.alkemy.ong.domain.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class UserRequestDto {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

      private List<Role> roles;
}
