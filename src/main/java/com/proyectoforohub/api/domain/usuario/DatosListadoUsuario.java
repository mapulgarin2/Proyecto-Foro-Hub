package com.proyectoforohub.api.domain.usuario;

public record DatosListadoUsuario(Long id,String nombre,String email,String contrasena) {
    public DatosListadoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(),usuario.getEmail(),usuario.getContrasena());
    }
}
