package dev.erickystn.literalura.repository;

import dev.erickystn.literalura.model.Idioma;
import dev.erickystn.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdioma(Idioma idioma);
    Optional<Livro> findByTituloAndAutor_Nome(String titulo, String nomeAutor);
}
