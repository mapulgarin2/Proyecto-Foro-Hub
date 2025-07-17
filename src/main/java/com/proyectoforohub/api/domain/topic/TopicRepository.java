package com.proyectoforohub.api.domain.topic;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topico,Long> {
    boolean existsByTituloAndMensaje(@NotBlank String titulo, @NotBlank String mensaje);
}
