package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PageDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface NewsControllerDoc {

    @Operation(summary = "Add a new news to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create news",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<NewsDTO> create(NewsDTO dto, BindingResult bindingResult);

    @Operation(summary = "Get a news by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the news",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News not found",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<NewsDTO> getById(Long id);

    @Operation(summary = "Delete a news by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the news",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "News not found",
                    content = @Content)})
    ResponseEntity<?> deleteById(Long id);

    @Operation(summary = "Update news")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful news update",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<NewsDTO> updateNewsById(Long id, NewsDTO dto, BindingResult bindingResult);

    @Operation(summary = "Get all news for paginated user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all news",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "The list is empty",
                    content = @Content)})
    ResponseEntity<PageDTO> getAllNewsPageable(Integer page);
}
