package br.com.desafio.gproj.service;

import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.helper.MessageHelper;
import br.com.desafio.gproj.model.Member;
import br.com.desafio.gproj.model.Person;
import br.com.desafio.gproj.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class AssociateMemberServiceTests {

    @InjectMocks
    private AssociateMemberService associateMemberService;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PersonService personService;
    @Mock
    private MessageHelper messageHelper;

    @Test
    void shouldSuccess_whenAssociateMemberProject() {
        var idMember = 1L;
        var idProject = 1L;
        var person = Person.builder()
                .cpf("12345678901")
                .build();
        when(personService.findById(idMember, Boolean.FALSE)).thenReturn(person);
        when(memberRepository.findByIdPessoaAndIdProjeto(idMember, idProject)).thenReturn(Optional.empty());

        associateMemberService.associateMemberProject(idMember, idProject);

        verify(personService, times(1)).findById(anyLong(), anyBoolean());
        verify(memberRepository, times(1)).findByIdPessoaAndIdProjeto(anyLong(), anyLong());
        verify(memberRepository, times(1)).save(any());
    }

    @Test
    void shouldThrow_whenAlreadyAssociated() {
        var idMember = 1L;
        var idProject = 1L;
        var person = Person.builder()
                .cpf("12345678901")
                .build();
        var associate = Member.builder().build();
        when(personService.findById(idMember, Boolean.FALSE)).thenReturn(person);
        when(memberRepository.findByIdPessoaAndIdProjeto(idMember, idProject)).thenReturn(Optional.of(associate));

        assertThrows(BusinessException.class, () ->
                associateMemberService.associateMemberProject(idMember, idProject)
        );

        verify(personService, times(1)).findById(anyLong(), anyBoolean());
        verify(memberRepository, times(1)).findByIdPessoaAndIdProjeto(anyLong(), anyLong());
        verify(memberRepository, never()).save(any());
    }
}
