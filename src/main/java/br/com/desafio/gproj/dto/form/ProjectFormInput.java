package br.com.desafio.gproj.dto.form;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import java.util.List;

@With
@Data
@Builder
public class ProjectFormInput {

    private Long id;
    private String name;
    private String description;
    private String initDate;
    private String endDateForecast;
    private String endDate;
    private List<SelectForm> selectStatus;
    private Long status;
    private Float budget;
    private List<SelectForm> selectRisk;
    private Long risk;
    private List<SelectForm> selectManager;
    private Long manager;
    private Boolean readOnly;
}
