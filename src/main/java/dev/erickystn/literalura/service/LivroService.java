package dev.erickystn.literalura.service;


import dev.erickystn.literalura.model.Idioma;
import dev.erickystn.literalura.model.Livro;
import dev.erickystn.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    public void salvarLivro(Livro livro) {
        if(!livroRepository.findByTituloAndAutor_Nome(livro.getTitulo(), livro.getAutor().getNome()).isPresent()){
            livroRepository.save(livro);
        }

    }

    public List<Livro> obterTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Livro> obterLivroPorIdioma(Idioma idioma) {
        return livroRepository.findByIdioma(idioma);
    }
}
