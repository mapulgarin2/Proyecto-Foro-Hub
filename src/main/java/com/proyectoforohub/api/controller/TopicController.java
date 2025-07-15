package com.proyectoforohub.api.controller;

import com.proyectoforohub.api.domain.curso.CursoRepository;
import com.proyectoforohub.api.domain.topic.DatosRegistroTopico;
import com.proyectoforohub.api.domain.topic.Topic;
import com.proyectoforohub.api.domain.topic.TopicRepository;
import com.proyectoforohub.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")

public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public void registrarTopicos(@RequestBody @Valid DatosRegistroTopico datos) {

        if (topicRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IllegalArgumentException("Tópico ya existe con ese título y mensaje");
        }

        var autor = usuarioRepository.getReferenceById(datos.autorId());
        var curso = cursoRepository.findByNombre(datos.nombreCurso()).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        topicRepository.save(new Topic(datos,autor,curso));
    }
}
