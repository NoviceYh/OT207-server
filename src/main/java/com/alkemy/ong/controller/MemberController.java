package com.alkemy.ong.controller;


import com.alkemy.ong.controller.documentation.MemberControllerDoc;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.BadRequestException;

import com.alkemy.ong.domain.service.IMemberService;
import com.alkemy.ong.domain.util.Url;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Url.MEMBERS_URI)
public class MemberController implements MemberControllerDoc {



    @Autowired
    private IMemberService memberService;

    @Override
    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        List<MemberDTO> result = this.memberService.getAll();
        return ResponseEntity.ok().body(result);
    }

    @Override
    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@Valid @RequestBody MemberDTO memberDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        MemberDTO savedMember = memberService.addMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@Valid @RequestBody MemberDTO dto,
                                                  @PathVariable Long id,
                                                  BindingResult result){
        if (result.hasErrors()) {throw new BadRequestException(result);}
        return new ResponseEntity<>(memberService.updateById(dto, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(name = "id") Long id){
        memberService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Operation(summary = "Get all members for paginated user role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all news",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "The list is empty",
                    content = @Content)})
    @GetMapping("/page")
    public ResponseEntity<PageDTO> getAllMembersPageable(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        PageDTO<MemberDTO> memberDTOPageDTO = memberService.getAllMembersPageable(page);
        return ResponseEntity.ok().body(memberDTOPageDTO);
    }
}
