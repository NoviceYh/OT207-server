package com.alkemy.ong.auth.dto;

import com.alkemy.ong.domain.model.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    private String email;

    //@NotNull(message = "Role cannot be null")
    private List<Role> roles;
}
