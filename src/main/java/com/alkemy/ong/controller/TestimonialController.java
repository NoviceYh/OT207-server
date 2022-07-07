package com.alkemy.ong.controller;

import com.alkemy.ong.controller.documentation.TestimonialControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.TESTIMONIALS_URI)
public class TestimonialController implements TestimonialControllerDoc {

    @Autowired
    private ITestimonialService testimonialService;

    @Override
    @PostMapping
    public ResponseEntity<TestimonialDTO> createTestimonial(@Valid @RequestBody TestimonialDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(testimonialService.save(dto), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("{id}")
    public ResponseEntity<TestimonialDTO> updateTestimonial(@Valid @RequestBody TestimonialDTO dto, BindingResult bindingResult,
                                                            @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        return new ResponseEntity<>(testimonialService.update(id, dto), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTestimonial(@PathVariable(name = "id") Long id) {
        testimonialService.deleteTestimonial(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
