package com.proyectoforohub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizarTopico(
        Long id,
        String titulo,
        String mensaje,
        Status status) {
}
