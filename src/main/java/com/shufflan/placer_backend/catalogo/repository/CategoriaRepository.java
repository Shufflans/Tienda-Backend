package com.shufflan.placer_backend.catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shufflan.placer_backend.catalogo.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNombreIgnoreCase(String nombre);

    Boolean existsByNombreIgnoreCase(String nombre);

}
