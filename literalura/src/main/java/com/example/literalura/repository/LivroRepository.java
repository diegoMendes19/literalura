package com.example.literalura.repository;

import com.example.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByIdiomaIgnoreCase(String idioma);
    long countByIdiomaIgnoreCase(String idioma);
}
