package com.proyectoforohub.api.domain.usuario;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario getReferenceByNombre(@NotNull String nombreAutor);

    UserDetails findByEmail(String email);


}
