package com.alkemy.ong.controller;

import com.alkemy.ong.controller.documentation.ContactControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.ContactDTO;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.IContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.CONTACTS_URI)
public class ContactController implements ContactControllerDoc {

    @Autowired
    private IContactService contactService;

    @Override
    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }

        return new ResponseEntity<>(contactService.save(dto), HttpStatus.CREATED);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ContactDTO>> getContacts() {
        List<ContactDTO> listDto = this.contactService.getAll();
        return ResponseEntity.ok().body(listDto);
    }
}
