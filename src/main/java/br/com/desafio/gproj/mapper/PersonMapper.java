package br.com.desafio.gproj.mapper;

import br.com.desafio.gproj.dto.CreatePersonDTO;
import br.com.desafio.gproj.enums.RoleEnum;
import br.com.desafio.gproj.model.Person;
import lombok.experimental.UtilityClass;

import static br.com.desafio.gproj.util.Utils.formatStringToLocalDate;
import static br.com.desafio.gproj.util.Utils.nonNullAndNonEmpty;

@UtilityClass
public class PersonMapper {

    public static Person map(CreatePersonDTO dto) {
        return Person.builder()
                .name(dto.getName())
                .birthDate(nonNullAndNonEmpty(dto.getBirthDate())
                        ? formatStringToLocalDate(dto.getBirthDate()) : null)
                .cpf(dto.getCpf())
                .manager(dto.getRole().equals(RoleEnum.GERENTE))
                .employee(dto.getRole().equals(RoleEnum.FUNCIONARIO))
                .build();
    }
}
