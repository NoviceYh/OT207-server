package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SlideControllerDoc {

    @Operation(summary = "Get all slides from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all slides",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "List of slides",
                                            description = "Get all the slides")
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
    ResponseEntity<List<SlidesDTO>> getSlides();

    @Operation(summary = "Add a new slide to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create slide",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Slide created",
                                            description = "Create a new slide in the database")
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
    ResponseEntity<SlidesResponseDTO> createSlides(SlidesRequestDTO requestDTO);

    @Operation(summary = "Delete slide from the database with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete slide",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Slide deleted",
                                            description = "Slide deleted from the database")
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
    ResponseEntity<Void> deleteSlidesById(Long id);

    @Operation(summary = "Get slide by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get slide",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Slide",
                                            description = "Get slide by id")
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
    ResponseEntity<SlidesResponseDTO> getSlidesResponseDTO(Long id);
}
