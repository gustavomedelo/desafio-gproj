package br.com.desafio.gproj.mapper;

import br.com.desafio.gproj.dto.form.ProjectForm;
import br.com.desafio.gproj.dto.form.ProjectFormInput;
import br.com.desafio.gproj.enums.RiskEnum;
import br.com.desafio.gproj.enums.StatusEnum;
import br.com.desafio.gproj.model.Person;
import br.com.desafio.gproj.model.Project;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

import static br.com.desafio.gproj.util.Utils.formatLocalDateToString;
import static br.com.desafio.gproj.util.Utils.nonNullAndNonEmpty;
import static java.util.Objects.nonNull;

@UtilityClass
public class ProjectMapper {

    public static Project map(ProjectFormInput dto, Person gerente) {
        return Project.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .initDate(nonNullAndNonEmpty(dto.getInitDate()) ? LocalDate.parse(dto.getInitDate()) : null)
                .endDateForecast(nonNullAndNonEmpty(dto.getEndDateForecast()) ? LocalDate.parse(dto.getEndDateForecast()) : null)
                .endDate(nonNullAndNonEmpty(dto.getEndDate()) ? LocalDate.parse(dto.getEndDate()) : null)
                .status(StatusEnum.getById(dto.getStatus()))
                .budget(dto.getBudget())
                .risk(RiskEnum.getById(dto.getRisk()))
                .manager(gerente)
                .build();
    }

    public static ProjectForm mapProjectForm(Project project) {
        return ProjectForm.builder()
                .id(project.getId())
                .name(project.getName())
                .initDate(nonNull(project.getInitDate()) ? formatLocalDateToString(project.getInitDate()) : null)
                .description(nonNull(project.getDescription()) ? project.getDescription() : null)
                .status(nonNull(project.getStatus()) ? project.getStatus().getDescricao() : null)
                .budget(nonNull(project.getBudget()) ? project.getBudget() : null)
                .risk(nonNull(project.getRisk()) ? project.getRisk().name() : null)
                .manager(project.getManager())
                .build();
    }

    public static ProjectFormInput mapProjectFormInput(Project project) {
        return ProjectFormInput.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .initDate(nonNull(project.getInitDate()) ? project.getInitDate().toString() : null)
                .endDateForecast(nonNull(project.getEndDateForecast()) ? project.getEndDateForecast().toString() : null)
                .endDate(nonNull(project.getEndDate()) ? project.getEndDateForecast().toString() : null)
                .status(nonNull(project.getStatus()) ? project.getStatus().getId() : null)
                .budget(project.getBudget())
                .risk(nonNull(project.getRisk()) ? project.getRisk().getId() : null)
                .manager(nonNull(project.getManager()) ? project.getManager().getId() : null)
                .build();
    }
}
