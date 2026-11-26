package com.shufflan.placer_backend.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoRequest {

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio del producto es obligatorio")
    @Positive(message = "El precio no puede ser negativo")
    private Integer precio;

    @NotNull(message = "Para ingresar el producto debe ser al menos 1")
    @Positive(message = "No puedes agregar cantidad negativa o 0")
    private Integer stock;

    private String imagenUrl;

    @NotNull(message = "El id de la categoria es Obligatorio")
    @Positive(message = "No puede agregar una ID 0 o negativa")
    private Long categoriaId;
}
