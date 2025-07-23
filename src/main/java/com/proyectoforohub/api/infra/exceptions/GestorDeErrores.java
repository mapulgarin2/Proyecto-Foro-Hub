package com.proyectoforohub.api.infra.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionError404(MethodArgumentNotValidException ex) {
        var errores = ex.getFieldErrors();
        //return ResponseEntity.badRequest().body(errores);
        return ResponseEntity.badRequest().body(errores.stream().map(DatosErroresValidacion :: new).toList());
    }


    /*@ExceptionHandler(ValidacionException.class)
    public ResponseEntity gestionErrorDeValidacion(ValidacionException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }*/

    public record DatosErroresValidacion(String campo,String mensaje) {
        public DatosErroresValidacion(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }


}
