package com.proyectoforohub.api.domain.curso;


public record DatosCurso(String nombre) {
    public DatosCurso(Curso curso) {
        //this(curso.getId(), curso.getNombre(), curso.getCategoria());
        this(curso.getNombre());
    }
}