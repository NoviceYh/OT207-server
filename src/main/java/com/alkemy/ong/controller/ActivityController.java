package com.alkemy.ong.controller;

import com.alkemy.ong.controller.documentation.ActivityControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.ACTIVITIES_URI)
public class ActivityController implements ActivityControllerDoc {

    @Autowired
    private IActivityService activityService;

    @Override
    @PostMapping
    public ResponseEntity<ActivityDTO> createActivity(@Valid @RequestBody ActivityDTO activityDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        ActivityDTO resultDTO = activityService.save(activityDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ActivityDTO> updateActivity(@Valid @RequestBody ActivityDTO activityDTO, BindingResult bindingResult,
                                                      @PathVariable Long id) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        ActivityDTO resultDTO = activityService.update(id, activityDTO);
        return ResponseEntity.ok().body(resultDTO);
    }

}
