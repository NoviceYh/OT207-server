package com.alkemy.ong.controller;

import com.alkemy.ong.domain.service.IMemberService;
import com.alkemy.ong.domain.util.JsonUtils;
import com.alkemy.ong.domain.util.Url;
import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class MemberControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MemberController controller;
    @Mock
    private IMemberService service;
    @Spy
    private MemberMapper mapper;

    private MemberDTO request;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(ApiExceptionHandler.class)
                .build();
        mapper = new MemberMapper();
        request = MemberDTO.builder()
                .name("member")
                .facebookUrl("facebook")
                .instagramUrl("instagram")
                .linkedinUrl("linkedin")
                .image("image")
                .description("some description")
                .build();

    }

    @Test
    void createMember_shouldReturn201() throws Exception {


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
    void updateMember_shouldReturn200() throws Exception {

        request.setId(99L);
        given(service.updateById(any(MemberDTO.class), eq(request.getId()))).willReturn(request);
        mockMvc.perform(put(Url.MEMBERS_URI + "/" + request.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtils.objectToJson(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(request.getName())))
                .andExpect(jsonPath("$.facebook_url", is(request.getFacebookUrl())))
                .andExpect(jsonPath("$.instagram_url", is(request.getInstagramUrl())))
                .andExpect(jsonPath("$.linkedin_url", is(request.getLinkedinUrl())))
                .andExpect(jsonPath("$.image", is(request.getImage())))
                .andExpect(jsonPath("$.description", is(request.getDescription())))
                .andDo(print());
    }

    //
    @Test
    void deleteMember_shouldReturn204() throws Exception {

        mockMvc.perform(delete(Url.MEMBERS_URI + "/1"))
                .andExpect(status().isNoContent())
                .andDo(print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void listAllUsers_whenGetMethod()
            throws Exception {
        List<MemberDTO> allMembers = Arrays.asList(request);
        given(service.getAll()).willReturn(allMembers);
        this.mockMvc.perform(get(Url.MEMBERS_URI)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(request.getName())))
                .andExpect(jsonPath("$[0].facebook_url", is(request.getFacebookUrl())))
                .andExpect(jsonPath("$[0].instagram_url", is(request.getInstagramUrl())))
                .andExpect(jsonPath("$[0].linkedin_url", is(request.getLinkedinUrl())))
                .andExpect(jsonPath("$[0].image", is(request.getImage())))
                .andExpect(jsonPath("$[0].description", is(request.getDescription())));
    }

//    @Test TODO:Implementar cuando se agrege paginacion al endpoint
//    void getMembers_shouldReturn200() throws Exception {
//
//
//        Member member = new Member();
//        member.setId(99L);
//        member.setName("member");
//        member.setImage("image");
//        member.setDescription("description");
//
//
//        MemberList list = new MemberList(List.of(member), Pageable.ofSize(1), 1);
//
//        given(service.getAll(any(PageRequest.class))).willReturn(list);
//
//        String content = mockMvc.perform(get(ApiConstants.MEMBERS_URI+"?page=0&size=1"))
//                .andExpect(status().isOk())
//                .andDo(print())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        assertThat(content).isNotBlank();
//
//        MemberResponseList response = JsonUtils.jsonToObject(content, MemberResponseList.class);
//
//        assertThat(response.getTotalElements()).isEqualTo(1);
//        assertThat(response.getTotalPages()).isEqualTo(1);
//        assertThat(response.getNextUri()).isEqualTo("http://localhost/v1/members?page=1");
//        assertThat(response.getPreviousUri()).isEqualTo("http://localhost/v1/members?page=0");
//        assertThat(response.getContent()).isNotEmpty();
//    }

}
