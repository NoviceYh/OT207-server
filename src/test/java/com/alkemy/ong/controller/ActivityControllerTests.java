package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.domain.service.IActivityService;
import com.alkemy.ong.domain.util.JsonUtils;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.ActivityDTO;
import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ActivityController.class)
class ActivityControllerTests {

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


     private ActivityDTO createDtoEntity() {
        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setName("Activity");
        activityDTO.setImage("image.jpg");
        activityDTO.setContent("content");
        return activityDTO;
    }

    @Test
    @WithMockUser(setupBefore = TestExecutionEvent.TEST_METHOD, username = "Alejandro")
    void testCreateActivityWithNotRights() throws Exception {
        ActivityDTO activityDTO = createDtoEntity();

        given(activityService.save(any(ActivityDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        String activityString = objectMapper.writeValueAsString(activityDTO);

        ResultActions response = mockMvc.perform(post(Url.ACTIVITIES_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(activityString));

        response.andDo(print()).
                andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void testCreateActivity() throws Exception {
        ActivityDTO activityDTO = createDtoEntity();

        given(activityService.save(any(ActivityDTO.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        String activityString = objectMapper.writeValueAsString(activityDTO);

        ResultActions response = mockMvc.perform(post(Url.ACTIVITIES_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(activityString));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(activityDTO.getName())))
                .andExpect(jsonPath("$.image",
                        is(activityDTO.getImage())))
                .andExpect(jsonPath("$.content",
                        is(activityDTO.getContent())));
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenUpdatedActivity_whenUpdateActivity_thenReturnIsAccepted() throws Exception {
        long activityId = 99L;
        ActivityDTO updateActivityDTO = createDtoEntity();
        given(activityService.update(eq(activityId), any(ActivityDTO.class))).willReturn(updateActivityDTO);
        mockMvc.perform(put(Url.ACTIVITIES_URI + "/" + activityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(updateActivityDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(updateActivityDTO.getName())))
                .andExpect(jsonPath("$.image", CoreMatchers.is(updateActivityDTO.getImage())))
                .andExpect(jsonPath("$.content", CoreMatchers.is(updateActivityDTO.getContent())))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
    void givenInvalidActivityId_whenGetActivityById_thenReturnIsNotFound() throws Exception {
        long activityId = 99L;
        given(activityService.update(eq(activityId), any(ActivityDTO.class))).willReturn(null);

        this.mockMvc.perform(put(Url.ACTIVITIES_URI + "/" + activityId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(null)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}
