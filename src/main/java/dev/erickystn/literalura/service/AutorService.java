package dev.erickystn.literalura.service;

import dev.erickystn.literalura.model.Autor;
import dev.erickystn.literalura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    AutorRepository autorRepository;

    public Autor salvarAutor(Autor autor){
        Optional<Autor> optionalAutor = autorRepository.findByNomeAndAnoNascimento(autor.getNome(),autor.getAnoNascimento());

        if(optionalAutor.isPresent()){
            return optionalAutor.get();
        }

        return  autorRepository.save(autor);
    }

    public List<Autor> obterTodosAutores(){
       return  autorRepository.findAll();
    }

    public List<Autor> obterAutoresPorAno(Integer ano) {
        return autorRepository.findByAnoFalecimentoGreaterThanEqualAndAnoNascimentoLessThanEqual(ano, ano);
    }
}
