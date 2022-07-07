package com.alkemy.ong.controller;

import com.alkemy.ong.controller.documentation.SlideControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.SlidesDTO;
import com.alkemy.ong.dto.SlidesRequestDTO;
import com.alkemy.ong.dto.SlidesResponseDTO;
import com.alkemy.ong.domain.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Url.SLIDES_URI)
public class SlidesController implements SlideControllerDoc {

    @Autowired
    private ISlideService iSlideService;

    @Override
    @GetMapping
    public ResponseEntity<List<SlidesDTO>> getSlides(){
        List<SlidesDTO> result = this.iSlideService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @Override
    @PostMapping
    public ResponseEntity<SlidesResponseDTO> createSlides(@RequestBody SlidesRequestDTO requestDTO){
        SlidesResponseDTO result = this.iSlideService.save(requestDTO);
        return ResponseEntity.ok().body(result);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlidesById(@PathVariable Long id){
        iSlideService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<SlidesResponseDTO> getSlidesResponseDTO(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iSlideService.getSlidesById(id));
    }

}
