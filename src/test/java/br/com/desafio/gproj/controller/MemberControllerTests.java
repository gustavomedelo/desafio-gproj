package br.com.desafio.gproj.controller;

import br.com.desafio.gproj.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
class MemberControllerTests {

    @InjectMocks
    private MemberController controller;
    @Mock
    private PersonService personService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void shouldReturn201_whenCreateMember() throws Exception {
        String data = "{\"name\": \"Joe Doe\", \"cpf\": \"39639658006\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void shouldReturn4xx_whenInvalidCPFMember() throws Exception {
        String data = "{\"name\": \"Joe Doe\", \"cpf\": \"12345678900\"}";

        this.mockMvc.perform(MockMvcRequestBuilders.post("/members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(data)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
