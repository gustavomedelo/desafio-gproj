package br.com.desafio.gproj.dto;

import br.com.desafio.gproj.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePersonDTO {

    private String name;
    private String birthDate;
    @CPF
    private String cpf;
    @Valid
    private RoleEnum role;
}
