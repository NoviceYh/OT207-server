package com.alkemy.ong.domain.service;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.dto.UserUpdateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IUserService {

    @Transactional(readOnly = true)
    List<UserResponseDto> getAllUsers();

    @Transactional()
    void deleteUserById(Long userId);

    @Transactional()
    UserResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto);
}
