package dev.erickystn.literalura.principal;

import com.fasterxml.jackson.core.JsonProcessingException;
import dev.erickystn.literalura.dto.LivroDTO;
import dev.erickystn.literalura.model.Autor;
import dev.erickystn.literalura.model.Idioma;
import dev.erickystn.literalura.model.Livro;
import dev.erickystn.literalura.service.AutorService;
import dev.erickystn.literalura.service.ConsumoApi;
import dev.erickystn.literalura.service.Conversor;
import dev.erickystn.literalura.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Principal {
    private final Scanner leitor = new Scanner(System.in);
    private final String URL_BASE = "https://gutendex.com/books?search=";
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final Conversor conversor = new Conversor();

    private LivroService livroService;
    private AutorService autorService;


    public Principal(LivroService livroService, AutorService autorService) {
        this.livroService = livroService;
        this.autorService = autorService;
    }


    public void exibeMenu() {

        Integer opcaoMenuEscolhida = -1;

        String menu = """
                Escolha o número da sua opção:
                1- Buscar livro pelo título
                2- Listar livros registrados
                3- Listar autores registrados
                4- Listar autores vivos em um determinado ano
                5- Listar livros em um determinado idioma
                0- Sair
                """;

        while (opcaoMenuEscolhida != 0) {
            System.out.println(menu);
            opcaoMenuEscolhida = leitor.nextInt();
            leitor.nextLine();

            switch (opcaoMenuEscolhida) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 0 -> System.exit(0);
                default -> System.out.println("Opção Inválida!");
            }
        }
    }


    private void buscarLivroPorTitulo() {
        System.out.println("Insira o nome do livro que voce deseja procurar: ");
        var livroDigitado = URLEncoder.encode(leitor.nextLine(), StandardCharsets.UTF_8);

        String dadosJson = consumoApi.obterDados(URL_BASE + livroDigitado);

        if (conversor.containsResults(dadosJson)) {
            try {
                LivroDTO livroDTO = conversor.converterDados(dadosJson, LivroDTO.class);
                Livro livro = new Livro(livroDTO);
                Autor autor = autorService.salvarAutor(new Autor(livroDTO.authors().getFirst()));
                livro.setAutor(autor);
                livroService.salvarLivro(livro);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Não foram encontrados livros com esse título!");
        }
    }

    private void listarLivros() {
        livroService.obterTodosLivros().forEach(System.out::println);
    }

    private void listarAutores() {
        autorService.obterTodosAutores().forEach(System.out::println);
    }

    private void listarAutoresPorAno() {
        System.out.println("Insira o ano que deseja pesquisar");
        var anoDigitado = leitor.nextInt(); leitor.nextLine();
        var autores = autorService.obterAutoresPorAno(anoDigitado);
        autores.forEach(System.out::println);
        if(autores.isEmpty()){
            System.out.println("Nao existem Autores que já haviam nascido nesse ano no banco de dados!");
        }
    }


    private void listarLivrosPorIdioma() {
        System.out.println("""
                
                
                Insira o idioma para realizar a busca:
                es - espanhol
                en - inglês
                fr - francês
                pt - português
                """);
        var idiomaDigitado = leitor.nextLine();

        Idioma idioma = Idioma.porCodigo(idiomaDigitado);
        if(idioma != null){
           var livros = livroService.obterLivroPorIdioma(idioma);
           livros.forEach(System.out::println);
           if(livros.isEmpty()){
               System.out.println("Não existem livros pesquisados com esse idioma!");
           }
        }


    }

}
