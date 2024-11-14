package dev.erickystn.literalura.repository;

import dev.erickystn.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    List<Autor> findByAnoFalecimentoGreaterThanEqualAndAnoNascimentoLessThanEqual(Integer ano1, Integer ano2);
    Optional<Autor> findByNomeAndAnoNascimento(String nome, Integer dataNascimento);
}
