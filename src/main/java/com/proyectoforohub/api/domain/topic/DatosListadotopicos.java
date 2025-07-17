package com.proyectoforohub.api.domain.topic;


import java.time.LocalDateTime;

public record DatosListadotopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autor,
        String curso) {
    public DatosListadotopicos(Topico topico) {
        //this(topic.getTitulo(),topic.getMensaje(),topic.getFechaCreacion(),topic.getStatus(),new DatosUsuario(topic.getAutor().getId()),new DatosCurso(topic.getCurso().getNombre()));

        this(topico.getId(),topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getAutor().getNombre(), topico.getCurso().getNombre());
    }


}
