package com.api.forohub.dominio.respuesta;

import com.api.forohub.dominio.topico.Topico;
import com.api.forohub.dominio.topico.TopicoRepositorio;
import com.api.forohub.dominio.usuario.Usuario;
import com.api.forohub.dominio.usuario.UsuarioRepositorio;
import com.api.forohub.infra.errores.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TopicoRepositorio topicoRepositorio;

    @Autowired
    RespuestaRepositorio respuestaRepositorio;

    public MostrarRespuesta registrarRespuesta(RegistroRespuesta datos){
        if(!usuarioRepositorio.existsById(datos.idUsuario())){
            throw new Validacion("ID de usuario no se encuentra registrado");
        }

        if(!topicoRepositorio.existsById(datos.idTopico())){
            throw new Validacion("Id de topico no se encuentra registrado");
        }

        Usuario usuario = usuarioRepositorio.getReferenceById(datos.idUsuario());
        Topico topico = topicoRepositorio.getReferenceById(datos.idTopico());

        Respuesta respuestaNueva = new Respuesta(datos.solucion(),topico,usuario);
        respuestaRepositorio.save(respuestaNueva);

        return new MostrarRespuesta(respuestaNueva);
    }
}
