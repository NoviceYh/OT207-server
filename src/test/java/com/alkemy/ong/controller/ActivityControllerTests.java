package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.domain.service.IActivityService;
import com.alkemy.ong.domain.service.ICategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ActivityController.class)
public class ActivityControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IActivityService activityService;

    @MockBean
    private UserDetailsCustomService detailsCustomService;

    @MockBean
    private JwtUtils jwtUtils;

    ObjectMapper objectMapper;

    @BeforeEach
    void configure() {
        objectMapper = new ObjectMapper();
    }

    /*
    * private CategoryDTO createDtoEntity() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("News");
        categoryDTO.setImage("image.png");
        categoryDTO.setDescription("This is a description");
        return categoryDTO;
    }

    private CategoryDtoName createDtoNameEntity() {
        CategoryDtoName categoryDTO = new CategoryDtoName();
        categoryDTO.setName("News");
        return categoryDTO;
    }
    * */

}
