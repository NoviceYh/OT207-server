package com.alkemy.ong.mapper;

import com.alkemy.ong.auth.dto.UserRequestDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.domain.model.UserEntity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {

    public UserEntity userDto2Entity(@NotNull UserRequestDto dto) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
            entity.setRoles(dto.getRoles());
        return entity;
    }

    public UserRequestDto userEntity2Dto(@NotNull UserEntity entity) {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRoles(entity.getRoles());
        return dto;
    }

    public UserResponseDto userEntity2ResponseDto(@NotNull UserEntity entity) {
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
            dto.setRoles(entity.getRoles());
        return dto;
    }

    public List<UserResponseDto> userEntityList2ResponseDtoList(@NotEmpty List<UserEntity> entities) {
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            responseDtos.add(this.userEntity2ResponseDto(entity));
        }
        return responseDtos;
    }

}
