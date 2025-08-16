# LiterAlura — Catálogo de Livros (Java + Spring Boot + Gutendex + JPA)

Projeto console que consome a API **Gutendex**, mapeia o JSON com **Jackson**, persiste **Livro** e **Autor** em **PostgreSQL** via **Spring Data JPA** e oferece um **menu interativo** pelo console.

## Tecnologias
- Java 17, Spring Boot 3.3.x
- Jackson 2.16 (ObjectMapper)
- HttpClient / HttpRequest / HttpResponse
- PostgreSQL + Spring Data JPA

## Como rodar
1. Configure o PostgreSQL e crie o banco `literalura` (ou ajuste o `application.properties`).
2. Ajuste `spring.datasource.username/password`.
3. Rode:
   ```bash
   mvn spring-boot:run
   ```

## Menu (exemplo)
```
====== LiterAlura ======
1. Buscar livro por título (API -> salvar)
2. Listar todos os livros
3. Listar todos os autores
4. Listar autores vivos em determinado ano
5. Listar livros por idioma (ex.: en, pt, es)
6. Exibir quantidade de livros por idioma
0. Sair
Escolha uma opção:
```
