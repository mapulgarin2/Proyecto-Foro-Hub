package com.proyectoforohub.api.domain.usuario;

public record DatosUsuario(Long id) {
    public DatosUsuario(Usuario usuario) {
        this(usuario.getId());
    }
}
