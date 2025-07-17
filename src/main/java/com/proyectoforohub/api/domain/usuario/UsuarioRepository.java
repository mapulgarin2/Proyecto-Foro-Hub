package com.proyectoforohub.api.domain.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario getReferenceByNombre(@NotNull String nombreAutor);
}
