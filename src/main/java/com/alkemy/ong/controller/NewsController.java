package com.alkemy.ong.controller;

import com.alkemy.ong.controller.documentation.NewsControllerDoc;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.NewsDTO;
import com.alkemy.ong.dto.PageDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.domain.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.NEWS_URI)
public class NewsController implements NewsControllerDoc {

    @Autowired
    private INewsService newsService;

    @Override
    @PostMapping
    public ResponseEntity<NewsDTO> create(@Valid @RequestBody NewsDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        NewsDTO result = newsService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> getById(@PathVariable("id") Long id) {
        NewsDTO newsDTO = this.newsService.getById(id);
        return ResponseEntity.ok().body(newsDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.newsService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<NewsDTO> updateNewsById(@PathVariable("id") Long id,
                                                  @Valid @RequestBody NewsDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        NewsDTO newsDTO = this.newsService.updateNewsById(id, dto);
        return ResponseEntity.ok().body(newsDTO);
    }

    @Override
    @GetMapping("/page")
    public ResponseEntity<PageDTO> getAllNewsPageable(@RequestParam(name = "page", defaultValue = "1") Integer page) {
        PageDTO<NewsDTO> newsDTOPageDTO = newsService.getAllNewsPageable(page);
        return ResponseEntity.ok().body(newsDTOPageDTO);
    }

}
