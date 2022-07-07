package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.domain.service.ICategoryService;
import com.alkemy.ong.domain.util.JsonUtils;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ICategoryService categoryService;

    @MockBean
    private UserDetailsCustomService detailsCustomService;

    @MockBean
    private JwtUtils jwtUtils;

    ObjectMapper objectMapper;

    @BeforeEach
    void configure() {
        objectMapper = new ObjectMapper();
    }


    private CategoryDTO createDtoEntity() {
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

    @Test
    @WithMockUser(setupBefore = TestExecutionEvent.TEST_METHOD, username = "Alejandro")
    void testCreateCategory() throws Exception {
        CategoryDTO categoryDTO = createDtoEntity();

        given(categoryService.addCategory(any(CategoryDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        String contactString = objectMapper.writeValueAsString(categoryDTO);

        ResultActions response = mockMvc.perform(post(Url.CATEGORY_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactString));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(categoryDTO.getName())))
                .andExpect(jsonPath("$.image",
                        is(categoryDTO.getImage())))
                .andExpect(jsonPath("$.description",
                        is(categoryDTO.getDescription())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenListOfCategories_whenGetAllCategories_thenReturnCategoriesList() throws Exception {
        CategoryDtoName categoryDTO1 = createDtoNameEntity();
        CategoryDtoName categoryDTO2 = createDtoNameEntity();
        List<CategoryDtoName> listOfCategories = new ArrayList<>();
        listOfCategories.add(categoryDTO1);
        listOfCategories.add(categoryDTO2);
        given(categoryService.getAllCategories()).willReturn(listOfCategories);

        ResultActions response = mockMvc.perform(get(Url.CATEGORY_URI));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(listOfCategories.size())));
    }

    @Test
    void givenListOfCategories_whenGetAllCategories_thenReturnCategoriesList_withNotRights() throws Exception {
        CategoryDtoName categoryDTO1 = createDtoNameEntity();
        CategoryDtoName categoryDTO2 = createDtoNameEntity();
        List<CategoryDtoName> listOfCategories = new ArrayList<>();
        listOfCategories.add(categoryDTO1);
        listOfCategories.add(categoryDTO2);
        given(categoryService.getAllCategories()).willReturn(listOfCategories);

        ResultActions response = mockMvc.perform(get(Url.CATEGORY_URI));

        response.andExpect(status().isForbidden())
                .andDo(print());
    }

    // positive scenario - valid employee id
    // JUnit test for GET category by id REST API
    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenCategoryId_whenGetCategoryById_thenReturnCategoryObject() throws Exception {
        // given - precondition or setup
        long categoryId = 1L;
        CategoryDTO categoryDTO = createDtoEntity();
        given(categoryService.getCategoryById(categoryId)).willReturn(categoryDTO);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get(Url.CATEGORY_URI + "/{id}", categoryId));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name", CoreMatchers.is(categoryDTO.getName())))
                .andExpect(jsonPath("$.image", CoreMatchers.is(categoryDTO.getImage())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(categoryDTO.getDescription())));
    }

    // negative scenario - valid employee id
    // JUnit test for GET employee by id REST API
    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenInvalidCategoryId_whenGetCategoryById_thenReturnIsNotFound() throws Exception {
        long categoryId = 99L;
        given(categoryService.getCategoryById(categoryId)).willReturn(null);

        this.mockMvc.perform(put(Url.CATEGORY_URI + "/" + categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(null)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenUpdatedCategory_whenUpdateCategory_thenReturnIsAccepted() throws Exception {
        long categoryId = 99L;
        CategoryDTO updateCategoryDTO = createDtoEntity();
        given(categoryService.modifyCategory(eq(categoryId), any(CategoryDTO.class))).willReturn(updateCategoryDTO);
        mockMvc.perform(put(Url.CATEGORY_URI + "/" + categoryId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(updateCategoryDTO)))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name", CoreMatchers.is(updateCategoryDTO.getName())))
                .andExpect(jsonPath("$.image", CoreMatchers.is(updateCategoryDTO.getImage())))
                .andExpect(jsonPath("$.description", CoreMatchers.is(updateCategoryDTO.getDescription())))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenCategoryId_whenDeleteCategory_thenReturnIsNoContent() throws Exception {
        // given - precondition or setup
        long categoryId = 1L;
        willDoNothing().given(categoryService).deleteCategory(categoryId);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete(Url.CATEGORY_URI + "/{id}", categoryId));

        // then - verify the output
        response.andExpect(status().isNoContent())
                .andDo(print());
    }
}
