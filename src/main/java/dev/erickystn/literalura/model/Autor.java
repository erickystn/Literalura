package dev.erickystn.literalura.model;

import dev.erickystn.literalura.dto.AutorDTO;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Livro> livros = new ArrayList<>();

    public Autor(AutorDTO autor) {
        this.nome = autor.name();
        this.anoFalecimento = autor.deathYear();
        this.anoNascimento = autor.birthYear();
    }

    public Autor() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    public void setAnoFalecimento(Integer anoFalecimento) {
        this.anoFalecimento = anoFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;

    }

    @Override
    public String toString() {
        return """
                Autor: %s
                Ano de nascimento: %d
                Ano de falecimento: %d
                Livros: %s
                """.formatted(nome,
                anoNascimento,
                anoFalecimento,
                livros.stream().map(Livro::getTitulo).collect(Collectors.joining(", "))
        );
    }
}
