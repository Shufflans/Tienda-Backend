package com.shufflan.placer_backend.catalogo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoriaRequest {

    @NotBlank(message = "El Nombre de la categoría es obligatorio.")
    @Size(min = 3, max = 30, message = "El nombre debe tener entre 3 y 30 carácteres")
    private String nombre;

    private String descripcion;
}
