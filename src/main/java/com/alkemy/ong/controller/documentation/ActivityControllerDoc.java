package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.ActivityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface ActivityControllerDoc {

    @Operation(summary = "Add a new activity to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create activity",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<ActivityDTO> createActivity(ActivityDTO activityDTO, BindingResult bindingResult);

    @Operation(summary = "Update activity")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update activity",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                    content = @Content)})
    ResponseEntity<ActivityDTO> updateActivity(ActivityDTO activityDTO, BindingResult bindingResult, Long id);
}
