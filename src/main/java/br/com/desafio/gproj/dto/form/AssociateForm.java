package br.com.desafio.gproj.dto.form;

import lombok.Builder;
import lombok.Data;
import lombok.With;

import javax.validation.constraints.NotNull;

@With
@Data
@Builder
public class AssociateForm {

    @NotNull
    private Long idMember;
    @NotNull
    private Long idProject;
}
