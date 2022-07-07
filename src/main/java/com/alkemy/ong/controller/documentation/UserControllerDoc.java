package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.dto.UserUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.List;

public interface UserControllerDoc {

    @Operation(summary = "Get all users from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all users",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "List of users",
                                            description = "Get all the users")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    ResponseEntity<List<UserResponseDto>> getAllUsers();

    @Operation(summary = "Delete user from the database with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete user",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "User deleted",
                                            description = "User deleted from the database")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    ResponseEntity<?> delete(Long userId);

    @Operation(summary = "Update a user to the database with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update user",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "User updated",
                                            description = "Update a user in the database")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Invalid token or token expired",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    UserResponseDto updateUser(Long userId, UserUpdateDto userUpdateDto, BindingResult bindingResult);
}
