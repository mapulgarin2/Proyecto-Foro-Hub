package com.proyectoforohub.api.controller;

import com.proyectoforohub.api.domain.curso.Curso;
import com.proyectoforohub.api.domain.curso.CursoRepository;
import com.proyectoforohub.api.domain.curso.DatosListadoCurso;
import com.proyectoforohub.api.domain.curso.DatosRegistroCurso;
import com.proyectoforohub.api.domain.usuario.DatosListadoUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    public ResponseEntity<Curso> registrarCurso(@RequestBody DatosRegistroCurso datos) {
        Curso nuevoCurso = new Curso(null, datos.nombre(), datos.categoria());
        Curso guardado = cursoRepository.save(nuevoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    public Page<DatosListadoCurso> listarCursos(@PageableDefault(size = 10,sort = "nombre") Pageable paginacion ) {
        return cursoRepository.findAll(paginacion).map(DatosListadoCurso::new);
    }
}
