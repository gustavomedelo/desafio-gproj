package br.com.desafio.gproj.service;

import br.com.desafio.gproj.exception.BusinessException;
import br.com.desafio.gproj.exception.ErrorCodeEnum;
import br.com.desafio.gproj.helper.MessageHelper;
import br.com.desafio.gproj.model.Member;
import br.com.desafio.gproj.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssociateMemberService {

    private final MessageHelper messageHelper;
    private final PersonService personService;
    private final MemberRepository memberRepository;

    public void associateMemberProject(final Long idMember, final Long idProject) {
        personService.findById(idMember, Boolean.FALSE);

        memberRepository.findByIdPessoaAndIdProjeto(idMember, idProject)
                        .ifPresent((member -> {
                            log.error(messageHelper.get(ErrorCodeEnum.ERROR_MEMBER_ALREADY_EXISTS_DETAILS, idMember, idProject));
                            throw BusinessException.builder()
                                    .status(HttpStatus.NOT_FOUND)
                                    .message(messageHelper.get(ErrorCodeEnum.ERROR_MEMBER_ALREADY_EXISTS))
                                    .build();
                        }));

        memberRepository.save(Member.builder()
                .idProjeto(idProject)
                .idPessoa(idMember)
                .build());
    }
}
