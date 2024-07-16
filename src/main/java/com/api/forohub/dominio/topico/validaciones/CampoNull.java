package com.api.forohub.dominio.topico.validaciones;

import com.api.forohub.dominio.topico.RegistrarTopico;
import com.api.forohub.infra.errores.Validacion;
import org.springframework.stereotype.Component;

@Component
public class CampoNull implements ValidadorTopico{
    @Override
    public void validar(RegistrarTopico datos) {
        if(datos.idUsuario() == null){
            throw new Validacion("ID usuario no puede estar vacío");
        }
        if(datos.titulo() == null){
            throw new Validacion("Título no puede estar vacío");
        }
        if(datos.mensaje() == null){
            throw new Validacion("Mensaje no puede estar vacío");
        }
        if(datos.curso() == null){
            throw new Validacion("Curso no puede estar vacío");
        }
    }
}
