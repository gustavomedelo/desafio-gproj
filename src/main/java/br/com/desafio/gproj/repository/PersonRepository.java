package br.com.desafio.gproj.repository;

import br.com.desafio.gproj.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByCpf(String cpf);
    List<Person> findByManagerAndEmployee(boolean manager, boolean employee);
}
