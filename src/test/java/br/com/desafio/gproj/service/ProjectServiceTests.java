package br.com.desafio.gproj.service;

import br.com.desafio.gproj.dto.form.ProjectFormInput;
import br.com.desafio.gproj.dto.form.SelectForm;
import br.com.desafio.gproj.enums.RiskEnum;
import br.com.desafio.gproj.enums.StatusEnum;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.helper.MessageHelper;
import br.com.desafio.gproj.model.Person;
import br.com.desafio.gproj.model.Project;
import br.com.desafio.gproj.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import java.util.stream.Collectors;

import static br.com.desafio.gproj.exception.ErrorCodeEnum.ERROR_PROJECT_DELETE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ProjectServiceTests {

    @InjectMocks
    private ProjectService projectService;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private PersonService personService;
    @Mock
    private MessageHelper messageHelper;


    @Test
    void shouldSuccess_whenCreateProject() {
        var projectFormInput = ProjectFormInput.builder()
                .name("Project 1")
                .build();
        when(projectRepository.findByName(projectFormInput.getName())).thenReturn(Optional.empty());
        when(personService.findById(any(), eq(Boolean.TRUE))).thenReturn(Person.builder().build());

        projectService.create(projectFormInput);

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void shouldThrow_whenProjectAlreadyExists() {
        var projectFormInput = ProjectFormInput.builder()
                .name("Project 2")
                .build();
        var project = Project.builder()
                .name("Project 1")
                .build();
        when(projectRepository.findByName(projectFormInput.getName())).thenReturn(Optional.of(project));

        assertThrows(BusinessException.class, () ->
                projectService.create(projectFormInput)
        );

        verify(projectRepository, never()).save(any(Project.class));
    }

    @Test
    void shouldSuccess_whenUpdateProject() {
        var id = 1L;
        var projectFormInput = ProjectFormInput.builder()
                .name("Project 1")
                .build();
        var project = Project.builder().build();
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));
        when(personService.findById(any(), eq(Boolean.TRUE))).thenReturn(Person.builder().build());

        projectService.update(id, projectFormInput);

        verify(projectRepository, times(1)).save(any(Project.class));
    }

    @Test
    void shouldThrow_whenProjectNotFound() {
        var id = 1L;
        var projectFormInput = ProjectFormInput.builder()
                .name("Project 1")
                .build();
        when(projectRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () ->
                projectService.update(id, projectFormInput)
        );
    }

    @Test
    void shouldSuccess_whenDeleteProject() {
        var id = 1L;
        var project = Project.builder()
                .id(1L)
                .name("Project 1")
                .status(StatusEnum.PLANEJADO)
                .build();
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        projectService.delete(id);

        verify(projectRepository, times(1)).deleteById(id);
    }

    @Test
    void shouldThrow_whenNotAllowedDeleteProject() {
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);
        project.setStatus(StatusEnum.INICIADO);

        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));
        when(messageHelper.get(ERROR_PROJECT_DELETE, project.getStatus())).thenReturn("Cannot delete project with status INICIADO");

        assertThrows(BusinessException.class, () -> projectService.delete(projectId));

        verify(projectRepository, never()).deleteById(projectId);
    }

    @Test
    void shouldSuccess_whenFindAllProjects() {
        var project = Project.builder()
                .id(1L)
                .name("Project 1")
                .status(StatusEnum.PLANEJADO)
                .build();
        when(projectRepository.findAll()).thenReturn(Collections.singletonList(project));

        var projects = projectService.findAllProject();

        assert !projects.isEmpty();
        assert projects.get(0).isAllowDelete();
        verify(projectRepository, times(1)).findAll();
    }

    @Test
    void shouldSuccess_whenBuildProjectFormInputById() {
        Long id = 1L;
        var project = Project.builder()
                .id(1L)
                .name("Project 1")
                .status(StatusEnum.PLANEJADO)
                .build();
        when(projectRepository.findById(id)).thenReturn(Optional.of(project));

        var projectFormInput = projectService.buildProjectFormInputById(id);

        assert projectFormInput != null;
        verify(projectRepository, times(1)).findById(id);
    }

    @Test
    void shouldSuccess_whenBuildCreateProjectForm() {
        var person = Person.builder()
                .id(1L)
                .name("manager")
                .build();
        var statusList = Arrays.stream(StatusEnum.values()).map(s ->
                        SelectForm.builder()
                                .key(s.getId().toString())
                                .label(s.getDescricao())
                                .build())
                .toList();
        var riskList = Arrays.stream(RiskEnum.values()).map(s ->
                        SelectForm.builder()
                                .key(s.getId().toString())
                                .label(s.getDescricao())
                                .build())
                .toList();
        var managerList = List.of(new SelectForm("1", "manager"));

        when(personService.findAllManager()).thenReturn(List.of(person));

        var projectFormInput = projectService.buildCreateProjectForm();

        assert projectFormInput.getSelectStatus().equals(statusList);
        assert projectFormInput.getSelectRisk().equals(riskList);
        assert projectFormInput.getSelectManager().equals(managerList);
        verify(personService, times(1)).findAllManager();

    }
}

