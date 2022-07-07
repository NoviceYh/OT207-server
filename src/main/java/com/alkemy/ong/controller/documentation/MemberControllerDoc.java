package com.alkemy.ong.controller.documentation;

import com.alkemy.ong.dto.MemberDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface MemberControllerDoc {

    @Operation(summary = "Get all members from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all members",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "List of members",
                                            description = "Get all the members")
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
    ResponseEntity<List<MemberDTO>> getAllMembers();

    @Operation(summary = "Add a new member to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create member",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Member created",
                                            description = "Create a new member in the database")
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
    ResponseEntity<MemberDTO> addMember(MemberDTO memberDto, BindingResult result);

    @Operation(summary = "Update a member to the database with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Update member",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Member updated",
                                            description = "Update a member in the database")
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
    ResponseEntity<MemberDTO> updateMember(MemberDTO dto, Long id, BindingResult result);

    @Operation(summary = "Delete member from the database with Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete member",
                    content = @Content(
                            examples = {
                                    @ExampleObject(
                                            name = "Example1",
                                            summary = "Member deleted",
                                            description = "Member deleted from the database")
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
    ResponseEntity<?> deleteMember(Long id);
}
