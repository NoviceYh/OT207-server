package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.ContactDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import java.util.List;

public interface ContactControllerDoc {

    @Operation(summary = "Add a new contact to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create contact",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid field",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<ContactDTO> createContact(ContactDTO dto, BindingResult result);

    @Operation(summary = "Get all contacts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all contacts",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content)})
    ResponseEntity<List<ContactDTO>> getContacts();
}
