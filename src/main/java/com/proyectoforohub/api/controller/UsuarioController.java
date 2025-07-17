package com.proyectoforohub.api.controller;

import com.proyectoforohub.api.domain.topic.DatosListadotopicos;
import com.proyectoforohub.api.domain.usuario.DatosListadoUsuario;
import com.proyectoforohub.api.domain.usuario.DatosRegistroUsuario;
import com.proyectoforohub.api.domain.usuario.Usuario;
import com.proyectoforohub.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> registrar(@RequestBody DatosRegistroUsuario datos) {
        Usuario usuario = new Usuario(
                null,
                datos.nombre(),
                datos.email(),
                datos.contrasena()
        );

        Usuario guardado = usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado);
    }

    @GetMapping
    public Page<DatosListadoUsuario> listarUsuarios(@PageableDefault(size = 10,sort = "nombre") Pageable paginacion ) {
        return usuarioRepository.findAll(paginacion).map(DatosListadoUsuario::new);
    }

}
