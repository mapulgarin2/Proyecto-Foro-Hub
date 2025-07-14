package com.proyectoforohub.api.controller;

import com.proyectoforohub.api.domain.topic.DatosRegistroTopico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")

public class TopicController {
    @PostMapping
    public void registrarTopicos(@RequestBody DatosRegistroTopico datos) {
        System.out.println(datos);
    }
}
