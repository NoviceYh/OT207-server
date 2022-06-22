package com.alkemy.ong.controller;

import com.alkemy.ong.H2Config;
import com.alkemy.ong.domain.service.IMemberService;
import com.alkemy.ong.domain.util.JsonUtils;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.MemberDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@SpringJUnitConfig(classes = H2Config.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Mock
    IMemberService service;

    @Test
    @Order(1)
    @WithUserDetails("test@admin.com")
    void createMembers_shouldReturn201() throws Exception {
        MemberDTO request = MemberDTO.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        given(service.addMember(any(MemberDTO.class))).willAnswer((invocation) -> invocation.getArgument(0));


        this.mockMvc.perform(post(Url.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(request.getName())))
                .andExpect(jsonPath("$.facebook_url", is(request.getFacebookUrl())))
                .andExpect(jsonPath("$.instagram_url", is(request.getInstagramUrl())))
                .andExpect(jsonPath("$.linkedin_url", is(request.getLinkedinUrl())))
                .andExpect(jsonPath("$.image", is(request.getImage())))
                .andExpect(jsonPath("$.description", is(request.getDescription())));
    }


    @Test
    @Order(2)
    @WithUserDetails("test@admin.com")
    void createMembers_shouldReturn400_name_null() throws Exception {
        MemberDTO request = MemberDTO.builder()
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        given(service.addMember(any(MemberDTO.class))).willAnswer((invocation) -> invocation.getArgument(0));


        this.mockMvc.perform(post(Url.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }


    @Test
    @Order(3)
    void createMembers_shouldReturn403_Forbidden() throws Exception {
        MemberDTO request = MemberDTO.builder()
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        given(service.addMember(any(MemberDTO.class))).willAnswer((invocation) -> invocation.getArgument(0));


        this.mockMvc.perform(post(Url.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }


    @Test
    @Order(4)
    @WithUserDetails("test@admin.com")
    void createMembers_shouldReturn400_image_null() throws Exception {
        MemberDTO request = MemberDTO.builder()
                .name("name")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .description("some description")
                .build();

        given(service.addMember(any(MemberDTO.class))).willAnswer((invocation) -> invocation.getArgument(0));


        this.mockMvc.perform(post(Url.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @Order(5)
    @WithUserDetails("test@admin.com")
    void updateMembers_shouldReturn404() throws Exception {
        MemberDTO request = MemberDTO.builder()
                .id(99L)
                .name("updatename")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        given(service.updateById(any(MemberDTO.class), eq(request.getId()))).willReturn(request);
        mockMvc.perform(put(Url.MEMBERS_URI + "/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isNotFound())
                .andDo(print());

    }

    @Test
    @Order(6)
    void updateMembers_shouldReturn401_Forbidden() throws Exception {
        MemberDTO request = MemberDTO.builder()
                .id(99L)
                .name("updatename")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        given(service.updateById(any(MemberDTO.class), eq(request.getId()))).willReturn(request);
        mockMvc.perform(put(Url.MEMBERS_URI + "/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(7)
    @WithUserDetails("test@admin.com")
    void getAllMembers_shouldReturn200() throws Exception {
        MemberDTO member = MemberDTO.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

        List<MemberDTO> allMembers = Arrays.asList(member);

        given(service.getAll()).willReturn(allMembers);

        this.mockMvc.perform(get(Url.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


    @Test
    @Order(8)
    @WithUserDetails("test@admin.com")
    void deteleMember_shouldReturn204() throws Exception {
        mockMvc.perform(delete(Url.MEMBERS_URI + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(16)
    @WithUserDetails("test@user.com")
    void deteleMember_shouldReturn403_role_user_forbidden() throws Exception {
        mockMvc.perform(delete(Url.MEMBERS_URI + "/1"))
                .andExpect(status().isForbidden());
    }
}
