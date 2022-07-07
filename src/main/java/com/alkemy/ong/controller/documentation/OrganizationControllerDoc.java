package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public interface OrganizationControllerDoc {

    @Operation(summary = "Get an organization from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the organization",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "List organization data",
                                            description = "Get the organization")
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
    ResponseEntity<OrganizationDTO> getOrganizationDTO();

    @Operation(summary = "Update an organization in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update organization",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "organization updated",
                                            description = "Update an organization in the database")
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
    ResponseEntity<OrganizationUpdateDTO> putUpdateOrganization (OrganizationUpdateDTO orgUpdate, BindingResult bindingResult);

    @Operation(summary = "Add a new organization to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create organization",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Organization created",
                                            description = "Create a new organization in the database")
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
    ResponseEntity<OrganizationDTO> addOrganization(OrganizationDTO organizationDTO, BindingResult result);
}
