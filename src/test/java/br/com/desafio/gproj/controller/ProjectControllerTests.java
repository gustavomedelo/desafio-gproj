package br.com.desafio.gproj.controller;

import br.com.desafio.gproj.constant.ModelViewConstants;
import br.com.desafio.gproj.dto.form.ProjectFormInput;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
class ProjectControllerTests {

    @InjectMocks
    private ProjectController controller;
    @Mock
    private ProjectService projectService;
    @Mock
    private BindingResult bindingResult;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    void shouldSuccess_whenShowListProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/projects"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name(ModelViewConstants.PROJECT_LIST_VIEW))
                .andExpect(model().attributeExists("itemList", "currentPage", "totalPages", "projects"));
    }

    @Test
    void shouldSuccess_whenCreateProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/projects/create")
                        .flashAttr(ModelViewConstants.PROJECT_FORM, ProjectFormInput.builder().build()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name(ModelViewConstants.REDIRECT_PROJECTS));
        verify(projectService, times(1)).create(any());
    }

    @Test
    void shouldSuccess_whenShowViewProjectPage() throws Exception {
        Long projectId = 1L;
        when(projectService.buildProjectFormInputById(projectId)).thenReturn(ProjectFormInput.builder().build());

        mockMvc.perform(MockMvcRequestBuilders.get("/projects/{id}", projectId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name(ModelViewConstants.PROJECT_VIEW))
                .andExpect(model().attributeExists("id", ModelViewConstants.PROJECT_FORM));
    }

    @Test
    void shouldSuccess_whenUpdateProject() throws Exception {
        Long projectId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/projects/{id}/update", projectId)
                        .flashAttr(ModelViewConstants.PROJECT_FORM, ProjectFormInput.builder().build()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name(ModelViewConstants.REDIRECT_PROJECTS));
        verify(projectService, times(1)).update(any(), any());
    }

    @Test
    void shouldSuccess_whenDeleteProject() throws Exception {
        Long projectId = 1L;
        mockMvc.perform(MockMvcRequestBuilders.post("/projects/{id}/delete", projectId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(view().name(ModelViewConstants.REDIRECT_PROJECTS));
    }

    @Test
    void shouldError_whenDeleteProject() throws Exception {
        Long id = 1L;
        doThrow(BusinessException.builder().message("Business error").build())
                .when(projectService).delete(id);

        mockMvc.perform(MockMvcRequestBuilders.post("/projects/{id}/delete", id))
                .andExpect(view().name(ModelViewConstants.PROJECT_LIST_VIEW))
                .andExpect(model().attributeExists("error"));
    }
}
