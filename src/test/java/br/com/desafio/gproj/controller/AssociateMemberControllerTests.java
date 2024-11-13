package br.com.desafio.gproj.controller;

import br.com.desafio.gproj.service.AssociateMemberService;
import br.com.desafio.gproj.service.PersonService;
import br.com.desafio.gproj.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
class AssociateMemberControllerTests {

    @InjectMocks
    private AssociateMemberController controller;
    @Mock
    private AssociateMemberService associateMemberService;
    @Mock
    private ProjectService projectService;
    @Mock
    private PersonService personService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldSuccess_whenAssociateMembersProjects() throws Exception {
        mockMvc.perform(post("/members/associate")
                        .param("idMember", "1")
                        .param("idProject", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/projects"));

        verify(associateMemberService, times(1)).associateMemberProject(1L, 1L);
    }

    @Test
    void shouldError_whenAssociateHasValidationErrors() throws Exception {
        mockMvc.perform(post("/members/associate")
                        .param("idMember", "invalid")
                        .param("idProject", "invalid"))
                .andExpect(status().is4xxClientError());
    }
}
