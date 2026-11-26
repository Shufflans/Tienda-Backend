package com.shufflan.placer_backend.catalogo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private Integer precio;
    private Integer stock;
    private String imagenUrl;
    private Boolean activo;
    private CategoriaResponse categoria;
}
