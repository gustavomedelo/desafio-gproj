package br.com.desafio.gproj.repository;

import br.com.desafio.gproj.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByIdPessoaAndIdProjeto(Long idMember, Long idPessoa);
}
