package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.TestimonialDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface TestimonialControllerDoc {

    @Operation(summary = "Add a new testimonial to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create testimonial",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<TestimonialDTO> createTestimonial(TestimonialDTO dto, BindingResult bindingResult);

    @Operation(summary = "Update testimonial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update testimonial",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<TestimonialDTO> updateTestimonial(TestimonialDTO dto, BindingResult bindingResult, Long id);

    @Operation(summary = "Delete a testimonial by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete the testimonial",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Testimonial not found",
                    content = @Content)})
    ResponseEntity<Void> deleteTestimonial(Long id);
}
