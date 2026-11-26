package com.shufflan.placer_backend.catalogo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shufflan.placer_backend.catalogo.model.Producto;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Optional<Producto> findByNombreIgnoreCase(String nombre);

    List<Producto> findByCategoriaIdAndActivoTrue(Long categoriaId);

    List<Producto> findByActivoTrue();

}
