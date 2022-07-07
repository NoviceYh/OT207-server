package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.auth.dto.AuthenticationRequest;
import com.alkemy.ong.auth.dto.AuthenticationResponse;
import com.alkemy.ong.auth.dto.UserRequestDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

public interface AuthContollerDoc {

    @Operation(summary = "Add a new user to the datebase")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user created",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "User",
                                            description = "create user")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid data request",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict Exception - There is already an account with this email",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    ResponseEntity<AuthenticationResponse> userRegistration(@Valid @RequestBody UserRequestDto userRequestDto, BindingResult bindingResult) throws Exception;


    @Operation(summary = "Login to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "logged user, returns a token",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Usermail and password",
                                            description = "log")
                            }
                    )),
            @ApiResponse(responseCode = "403", description = "Incorrect username or password",
                    content = @Content),})
    ResponseEntity<AuthenticationResponse> singIn(@RequestBody AuthenticationRequest authRequest);

    @Operation(summary = "Obtain user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user log",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "User",
                                            description = "user log")
                            }
                    )),
            @ApiResponse(responseCode = "400", description = "Invalid data request",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "user not logged in",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal error",
                    content = @Content)})
    ResponseEntity<UserResponseDto> getProfile(HttpServletRequest request);
}
