package com.shufflan.placer_backend.catalogo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shufflan.placer_backend.catalogo.dto.CategoriaRequest;
import com.shufflan.placer_backend.catalogo.dto.CategoriaResponse;
import com.shufflan.placer_backend.catalogo.exception.CategoriaYaExisteException;
import com.shufflan.placer_backend.catalogo.exception.CategoriaDuplicadaException;
import com.shufflan.placer_backend.catalogo.exception.CategoriaNoEncontradaException;
import com.shufflan.placer_backend.catalogo.model.Categoria;
import com.shufflan.placer_backend.catalogo.repository.CategoriaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Transactional
    public CategoriaResponse nuevaCategoria(CategoriaRequest cr) {
        log.info("Creando categoría: {}", cr.getNombre());

        if (categoriaRepository.existsByNombreIgnoreCase(cr.getNombre())) {
            log.warn("La categoría || {} || ya existe.", cr.getNombre());
            throw new CategoriaYaExisteException(
                    "El nombre " + cr.getNombre() + " ya existe.");
        }

        Categoria crearCategoria = Categoria.builder()
                .nombre(cr.getNombre())
                .descripcion(cr.getDescripcion())
                .build();

        Categoria categoriaGuardada = categoriaRepository.save(crearCategoria);

        return mapearACategoriaResponse(categoriaGuardada);
    }

    @Transactional(readOnly = true)
    public CategoriaResponse mostrarCategoriaNombre(String nombre) {
        log.info("Buscando Categoria por el nombre: {}", nombre);

        Categoria categoriaEncontrada = categoriaRepository
                .findByNombreIgnoreCase(nombre)
                .orElseThrow(() -> {
                    log.warn("Categoria con el nombre {} no encontrado", nombre);
                    return new CategoriaNoEncontradaException(
                            "Categoria con el nombre " + nombre + " no encontrada.");
                });

        return mapearACategoriaResponse(categoriaEncontrada);
    }

    @Transactional(readOnly = true)
    public CategoriaResponse mostrarCategoriaId(Long id) {
        log.info("No existe una categoría con la ID: {}", id);

        Categoria categoriaEncontrada = categoriaRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("No se encontró la categoría con la ID: {}", id);
                    return new CategoriaNoEncontradaException(
                            "Categoría con el ID " + id + " no encontrada.");
                });

        return mapearACategoriaResponse(categoriaEncontrada);
    }

    @Transactional
    public CategoriaResponse actualizarNombreCategoria(Long id, CategoriaRequest cr) {
        log.info("Actualizando la categoría con la ID {}, con el nombre {}", id, cr.getNombre());

        Categoria categoriaEncontrada = categoriaRepository
                .findById(id)
                .orElseThrow(() -> {
                    log.warn("No se ha encontrado una categoría con la ID: {}", id);
                    return new CategoriaNoEncontradaException(
                            "Categoría con el ID " + id + " no encontrada.");
                });

        if (!categoriaEncontrada.getNombre().equals(cr.getNombre())
                && categoriaRepository.existsByNombreIgnoreCase(cr.getNombre())) {
            log.warn("El nombre {} ya está siendo ocupado por otra categoria", cr.getNombre());
            throw new CategoriaDuplicadaException(
                    "El nombre " + cr.getNombre() + " ya está siendo utilizado por otra categoría");
        }

        categoriaEncontrada.setNombre(cr.getNombre());
        categoriaEncontrada.setDescripcion(categoriaEncontrada.getDescripcion());

        Categoria categoriaGuardada = categoriaRepository.save(categoriaEncontrada);

        return mapearACategoriaResponse(categoriaGuardada);

    }

    private CategoriaResponse mapearACategoriaResponse(Categoria c) {
        return CategoriaResponse.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .descripcion(c.getDescripcion())
                .build();
    }
}
