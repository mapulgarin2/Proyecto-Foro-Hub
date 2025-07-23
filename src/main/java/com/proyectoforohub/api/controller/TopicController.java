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
import org.springframework.web.util.UriComponentsBuilder;

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
    public ResponseEntity registrarTopicos(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder) {

        if (topicRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new IllegalArgumentException("Tópico ya existe con ese título y mensaje");
        }

        var autor = usuarioRepository.getReferenceByNombre(datos.nombreAutor());
        var curso = cursoRepository.findByNombre(datos.nombreCurso()).orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        var topico = new Topico(datos,autor,curso);
        topicRepository.save(topico);


        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));

    }

    @GetMapping
    public ResponseEntity<Page<DatosListadotopicos>> listarTopicos(@PageableDefault(size = 10,sort = "fechaCreacion") Pageable paginacion ) {
        var page = topicRepository.findAll(paginacion).map(DatosListadotopicos::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos) {
        var topico = topicRepository.getReferenceById(datos.id());
        topico.actualizarInformacionTopico(datos);
        return ResponseEntity.ok(new DatosDetalleTopico(topico));

    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id) {
        //var topico = topicRepository.getReferenceById(id);
        var topicoOpcional = topicRepository.findById(id);
        if (topicoOpcional.isPresent()) {
            var topico = topicoOpcional.get();
        return ResponseEntity.ok(new DatosDetalleTopico(topico));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        var topicoOpcional = topicRepository.findById(id);

        if (topicoOpcional.isPresent()) {
            topicRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();

    }

}
