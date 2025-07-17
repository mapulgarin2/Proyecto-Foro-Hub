package com.proyectoforohub.api.controller;

import com.proyectoforohub.api.domain.curso.CursoRepository;
import com.proyectoforohub.api.domain.topic.*;
import com.proyectoforohub.api.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @Transactional
    public ResponseEntity<String> registrarTopicos(@RequestBody @Valid DatosRegistroTopico datos) {

        if (topicRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IllegalArgumentException("Tópico ya existe con ese título y mensaje");
        }

        var autor = usuarioRepository.getReferenceByNombre(datos.nombreAutor());
        var curso = cursoRepository.findByNombre(datos.nombreCurso()).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        topicRepository.save(new Topico(datos,autor,curso));

        return ResponseEntity.ok("Topico registrado con exito");

    }

    @GetMapping
    public Page<DatosListadotopicos> listarTopicos(@PageableDefault(size = 10,sort = "fechaCreacion") Pageable paginacion ) {
        return topicRepository.findAll(paginacion).map(DatosListadotopicos::new);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        var topico = topicRepository.getReferenceById(datos.id());
        topico.actualizarInformacionTopico(datos);
        return ResponseEntity.ok("Datos actualizados");

    }
}
