package com.alkemy.ong.controller;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsCustomService;
import com.alkemy.ong.controller.ContactController;
import com.alkemy.ong.domain.service.IContactService;
import com.alkemy.ong.dto.ContactDTO;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IContactService contactService;

    @MockBean
    private UserDetailsCustomService detailsCustomService;

    @MockBean
    private JwtUtils jwtUtils;

    ObjectMapper objectMapper;

    @BeforeEach
    void configure() {
        objectMapper = new ObjectMapper();
    }

    @BeforeEach
    private ContactDTO createDtoEntity() {
        ContactDTO contactDTO = new ContactDTO();
        contactDTO.setName("Alejandro");
        contactDTO.setPhone("+548959526");
        contactDTO.setEmail("ale@gmail.com");
        contactDTO.setMessage("Ale, mi buen amigo");
        return contactDTO;
    }

    @Test
    @WithMockUser(setupBefore = TestExecutionEvent.TEST_METHOD, username = "Alejandro")
    void testCreateContact() throws Exception {
        ContactDTO contactDTO = createDtoEntity();

        given(contactService.save(any(ContactDTO.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        String contactString = objectMapper.writeValueAsString(contactDTO);

        ResultActions response = mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactString));

        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.name",
                        is(contactDTO.getName())))
                .andExpect(jsonPath("$.phone",
                        is(contactDTO.getPhone())))
                .andExpect(jsonPath("$.email",
                        is(contactDTO.getEmail())))
                .andExpect(jsonPath("$.message",
                        is(contactDTO.getMessage())));
    }

    @Test
    @WithMockUser(setupBefore = TestExecutionEvent.TEST_METHOD, username = "Alejandro")
    void testCreateContactWithNameNull() throws Exception {
        ContactDTO contactDTO = createDtoEntity();
        contactDTO.setName(null);

        given(contactService.save(any(ContactDTO.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        String contactString = objectMapper.writeValueAsString(contactDTO);

        ResultActions response = mockMvc.perform(post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(contactString));

        response.andDo(print()).
                andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(setupBefore = TestExecutionEvent.TEST_METHOD, username = "Alejandro")
    void givenListOfContacts_whenGetAllContacts_thenReturnContactsList() throws Exception{
        ContactDTO contact1 = createDtoEntity();
        ContactDTO contact2 = createDtoEntity();
        List<ContactDTO> listOfContacts = new ArrayList<>();
        listOfContacts.add(contact1);
        listOfContacts.add(contact2);
        given(contactService.getAll()).willReturn(listOfContacts);

        ResultActions response = mockMvc.perform(get("/contacts"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        CoreMatchers.is(listOfContacts.size())));
    }

    @Test
    void givenListOfContacts_whenGetAllContacts_thenReturnContactsList_withNotRights() throws Exception{
        ContactDTO contact1 = createDtoEntity();
        ContactDTO contact2 = createDtoEntity();
        List<ContactDTO> listOfContacts = new ArrayList<>();
        listOfContacts.add(contact1);
        listOfContacts.add(contact2);
        given(contactService.getAll()).willReturn(listOfContacts);

        ResultActions response = mockMvc.perform(get("/contacts"));

        response.andExpect(status().isForbidden())
                .andDo(print());
    }




}
