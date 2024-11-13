package br.com.desafio.gproj.controller;

import br.com.desafio.gproj.dto.CreatePersonDTO;
import br.com.desafio.gproj.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final PersonService service;

    @Operation(summary = "Criar novo membro", description = "Endpoint para criação de novo membro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Membro criado com sucesso"),
            @ApiResponse(responseCode = "409", description = "Membro já cadastrado no sistema"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Valid CreatePersonDTO createPersonDTO) {
        service.create(createPersonDTO);
    }
}

