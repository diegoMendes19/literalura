package com.example.literalura;

import com.example.literalura.model.Autor;
import com.example.literalura.model.Livro;
import com.example.literalura.service.CatalogoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final CatalogoService catalogo;

    public LiteraluraApplication(CatalogoService catalogo) {
        this.catalogo = catalogo;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean continuar = true;
            while (continuar) {
                mostrarMenu();
                String input = scanner.nextLine();
                int opcao;
                try {
                    opcao = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Digite um número.");
                    continue;
                }

                switch (opcao) {
                    case 1 -> buscarLivro(scanner);
                    case 2 -> listarLivros();
                    case 3 -> listarAutores();
                    case 4 -> autoresVivosNoAno(scanner);
                    case 5 -> listarLivrosPorIdioma(scanner);
                    case 6 -> estatisticaPorIdioma(scanner);
                    case 0 -> {
                        continuar = false;
                        System.out.println("Encerrando... Obrigado por usar o LiterAlura!");
                    }
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println("====== LiterAlura ======");
        System.out.println("1. Buscar livro por título (API -> salvar)");
        System.out.println("2. Listar todos os livros");
        System.out.println("3. Listar todos os autores");
        System.out.println("4. Listar autores vivos em determinado ano");
        System.out.println("5. Listar livros por idioma (ex.: en, pt, es)");
        System.out.println("6. Exibir quantidade de livros por idioma");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void buscarLivro(Scanner scanner) {
        System.out.print("Digite o título do livro: ");
        String titulo = scanner.nextLine().trim();
        if (titulo.isEmpty()) {
            System.out.println("Título não pode ser vazio.");
            return;
        }
        Optional<Livro> salvo = catalogo.buscarESalvarLivroPorTitulo(titulo);
        if (salvo.isPresent()) {
            System.out.println("Livro salvo: " + salvo.get());
        } else {
            System.out.println("Nenhum livro encontrado para o título informado.");
        }
    }

    private void listarLivros() {
        List<Livro> livros = catalogo.listarTodosLivros();
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
            return;
        }
        livros.forEach(System.out::println);
    }

    private void listarAutores() {
        List<Autor> autores = catalogo.listarTodosAutores();
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
            return;
        }
        autores.forEach(System.out::println);
    }

    private void autoresVivosNoAno(Scanner scanner) {
        System.out.print("Informe o ano (ex.: 1900): ");
        String entrada = scanner.nextLine();
        try {
            int ano = Integer.parseInt(entrada);
            List<Autor> vivos = catalogo.listarAutoresVivosNoAno(ano);
            if (vivos.isEmpty()) {
                System.out.println("Nenhum autor vivo no ano informado.");
            } else {
                vivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println("Ano inválido.");
        }
    }

    private void listarLivrosPorIdioma(Scanner scanner) {
        System.out.print("Informe o idioma (código ISO 2, ex.: en, pt, es, fr): ");
        String idioma = scanner.nextLine().trim();
        if (idioma.isEmpty()) {
            System.out.println("Idioma não pode ser vazio.");
            return;
        }
        List<Livro> livros = catalogo.listarLivrosPorIdioma(idioma);
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado para o idioma informado.");
        } else {
            livros.forEach(System.out::println);
        }
    }

    private void estatisticaPorIdioma(Scanner scanner) {
        System.out.print("Informe o idioma (ex.: en, pt): ");
        String idioma = scanner.nextLine().trim();
        if (idioma.isEmpty()) {
            System.out.println("Idioma não pode ser vazio.");
            return;
        }
        long qtd = catalogo.contarLivrosPorIdioma(idioma);
        System.out.printf("Quantidade de livros em '%s': %d%n", idioma, qtd);
    }
}
