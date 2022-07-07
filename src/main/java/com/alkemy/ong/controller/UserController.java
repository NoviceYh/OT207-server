package com.alkemy.ong.controller;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.dto.UserUpdateDto;
import com.alkemy.ong.controller.documentation.UserControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Url.USER_URI)
public class UserController implements UserControllerDoc {

    @Autowired
    private IUserService userService;

    @Override
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> usersDTOs = this.userService.getAllUsers();
        return ResponseEntity.ok().body(usersDTOs);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long userId) {
        this.userService.deleteUserById(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PatchMapping("users/{id}")
    public UserResponseDto updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return userService.updateUser(userId, userUpdateDto);
    }

}
