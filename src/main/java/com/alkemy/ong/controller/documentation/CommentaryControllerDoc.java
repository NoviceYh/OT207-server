package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.CommentaryBodyDTO;
import com.alkemy.ong.dto.CommentaryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CommentaryControllerDoc {

    @Operation(summary = "Get all comments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all comments",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<List<CommentaryBodyDTO>> getComments();

    @Operation(summary = "Add a new commentary to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create commentary",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<CommentaryDTO> createCommentary(CommentaryDTO dto, BindingResult result);

    @Operation(summary = "Update commentary")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update commentary",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<CommentaryBodyDTO> updateCommentary(CommentaryBodyDTO dto, BindingResult bindingResult, Long id, HttpServletRequest request);

    @Operation(summary = "Delete a category by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the category",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found",
                    content = @Content)})
    ResponseEntity<Void> deleteCommentary(Long id, HttpServletRequest request);

    @Operation(summary = "Get all commentaries for post user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all commentaries",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "The list is empty",
                    content = @Content)})
    ResponseEntity<?> getCommentaryByPost(Long id);
}
