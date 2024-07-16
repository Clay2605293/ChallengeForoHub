package com.api.forohub.dominio.topico.validaciones;

import com.api.forohub.dominio.topico.RegistrarTopico;
import com.api.forohub.dominio.topico.TopicoRepositorio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicoDuplicado implements ValidadorTopico{

    @Autowired
    TopicoRepositorio topicoRepositorio;

    @Override
    public void validar(RegistrarTopico datos) {
        var tituloExiste = topicoRepositorio.existsByTitulo(datos.titulo());
        var mensajeExiste = topicoRepositorio.existsByMensaje(datos.mensaje());

        if(tituloExiste && mensajeExiste){
            throw new ValidationException("El topico ya existe. Ingresa uno diferente.");
        }
    }
}
