package com.alkemy.ong.controller;


import com.alkemy.ong.controller.documentation.OrganizationControllerDoc;
import com.alkemy.ong.domain.model.Organization;
import com.alkemy.ong.domain.service.IOrganizationService;
import com.alkemy.ong.domain.service.ISlideService;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.OrganizationDTO;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.exception.BadRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.ORGANIZATION_URI)
public class OrganizationController implements OrganizationControllerDoc {

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISlideService slidesService;

    @GetMapping("/public")
    public ResponseEntity<OrganizationDTO> getOrganizationDTO(){
        OrganizationDTO organizationDTO = this.organizationService.getOrg();
        return ResponseEntity.ok().body(organizationDTO);
    }

    @PostMapping("/public")
    public ResponseEntity<OrganizationUpdateDTO> putUpdateOrganization (@RequestBody @Valid OrganizationUpdateDTO orgUpdate, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }
        Organization org = organizationService.updateOrganizationDto(orgUpdate);
        organizationService.updateOrganization(org);
        return ResponseEntity.ok().body(orgUpdate);
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> addOrganization(@Valid @RequestBody OrganizationDTO organizationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        OrganizationDTO organizationDto = organizationService.addOrganization(organizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationDto);
}


}
