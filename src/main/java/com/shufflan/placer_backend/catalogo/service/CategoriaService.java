package com.shufflan.placer_backend.catalogo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shufflan.placer_backend.catalogo.dto.CategoriaRequest;
import com.shufflan.placer_backend.catalogo.dto.CategoriaResponse;
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

    private CategoriaResponse mapearACategoriaResponse(Categoria c) {
        return CategoriaResponse.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .descripcion(c.getDescripcion())
                .build();
    }
}
