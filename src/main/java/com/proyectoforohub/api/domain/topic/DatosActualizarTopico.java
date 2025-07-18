package com.proyectoforohub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Status status) {
}
