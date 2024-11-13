package br.com.desafio.gproj.service;

import br.com.desafio.gproj.dto.form.ProjectForm;
import br.com.desafio.gproj.dto.form.ProjectFormInput;
import br.com.desafio.gproj.dto.form.SelectForm;
import br.com.desafio.gproj.enums.RiskEnum;
import br.com.desafio.gproj.enums.StatusEnum;
import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.exception.ErrorCodeEnum;
import br.com.desafio.gproj.helper.MessageHelper;
import br.com.desafio.gproj.mapper.ProjectMapper;
import br.com.desafio.gproj.model.Project;
import br.com.desafio.gproj.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.desafio.gproj.mapper.ProjectMapper.mapProjectFormInput;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final MessageHelper messageHelper;
    private final PersonService personService;
    private final ProjectRepository projectRepository;

    public void create(final ProjectFormInput projectFormInput) {
        projectRepository.findByName(projectFormInput.getName())
                .ifPresent(project -> {
                    log.error(messageHelper.get(ErrorCodeEnum.ERROR_PROJECT_ALREADY_EXISTS, project.getName()));
                    throw BusinessException.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message(messageHelper.get(ErrorCodeEnum.ERROR_PROJECT_ALREADY_EXISTS, project.getName()))
                            .build();
                });

        var manager = personService.findById(projectFormInput.getManager(), Boolean.TRUE);
        projectRepository.save(ProjectMapper.map(projectFormInput, manager));
    }

    public void update(final Long id, final ProjectFormInput projectFormInput) {
        findById(id);
        var manager = personService.findById(projectFormInput.getManager(), Boolean.TRUE);
        projectRepository.save(ProjectMapper.map(projectFormInput, manager).withId(id));
    }

    public void delete(final Long id) {
        var project = findById(id);
        if (notAllowDelete(project.getStatus())) {
            log.info(messageHelper.get(ErrorCodeEnum.ERROR_PROJECT_DELETE, project.getStatus()));
            throw BusinessException.builder()
                    .status(HttpStatus.CONFLICT)
                    .message(messageHelper.get(ErrorCodeEnum.ERROR_PROJECT_DELETE, project.getStatus()))
                    .build();
        }
        projectRepository.deleteById(id);
    }

    private Project findById(final Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(messageHelper.get(ErrorCodeEnum.ERROR_PROJECT_NOT_FOUND, id));
                    return BusinessException.builder()
                            .status(HttpStatus.NOT_FOUND)
                            .message(messageHelper.get(ErrorCodeEnum.ERROR_PROJECT_NOT_FOUND, id))
                            .build();
                });
    }

    private boolean notAllowDelete(final StatusEnum status) {
        return Arrays.asList(StatusEnum.INICIADO, StatusEnum.EM_ANDAMENTO, StatusEnum.ENCERRADO).contains(status);
    }

    public List<ProjectForm> findAllProject() {
        var projects = projectRepository.findAll();
        return projects.stream().map(p -> ProjectMapper.mapProjectForm(p)
                        .withAllowDelete(!notAllowDelete(p.getStatus())))
                .collect(Collectors.toList());
    }

    public ProjectFormInput buildProjectFormInputById(final Long id) {
        return mapProjectFormInput(findById(id))
                .withSelectStatus(getSelectStatus())
                .withSelectRisk(getSelectRisk())
                .withSelectManager(getSelectManager());
    }

    public ProjectFormInput buildCreateProjectForm() {
        return ProjectFormInput.builder()
                .selectStatus(getSelectStatus())
                .selectRisk(getSelectRisk())
                .selectManager(getSelectManager())
                .build();
    }

    List<SelectForm> getSelectStatus() {
        return Arrays.stream(StatusEnum.values()).map(s ->
                        SelectForm.builder()
                                .key(s.getId().toString())
                                .label(s.getDescricao())
                                .build())
                .collect(Collectors.toList());
    }

    List<SelectForm> getSelectRisk() {
        return Arrays.stream(RiskEnum.values()).map(s ->
                        SelectForm.builder()
                                .key(s.getId().toString())
                                .label(s.getDescricao())
                                .build())
                .collect(Collectors.toList());
    }

    List<SelectForm> getSelectManager() {
        var personList = personService.findAllManager();
        return personList.stream().map(s ->
                        SelectForm.builder()
                                .key(s.getId().toString())
                                .label(s.getName())
                                .build())
                .collect(Collectors.toList());
    }
}
