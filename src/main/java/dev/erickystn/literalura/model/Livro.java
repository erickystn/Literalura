package dev.erickystn.literalura.model;

import dev.erickystn.literalura.dto.LivroDTO;
import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer downloads;

    @ManyToOne
    private Autor autor;

    public Livro(LivroDTO livro) {
        this.titulo = livro.title();
        this.idioma = Idioma.porCodigo(livro.languages().getFirst());
        this.downloads = livro.downloads();
    }

    public Livro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma.getCodigo();
    }

    public void setIdioma(String idioma) {
        this.idioma = Idioma.porCodigo(idioma);
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return """
                ****** LIVRO ******
                Título: %s
                Autor: %s
                Idioma: %s
                Número de downloads: %d
                ----------------
                """.formatted(titulo, autor.getNome(), idioma.getCodigo(), downloads);
    }
}
