package com.api.forohub.dominio.topico;

import com.api.forohub.dominio.topico.validaciones.ValidadorTopico;
import com.api.forohub.dominio.usuario.Usuario;
import com.api.forohub.dominio.usuario.UsuarioRepositorio;
import com.api.forohub.infra.errores.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    TopicoRepositorio topicoRepositorio;

    @Autowired
    List<ValidadorTopico> validadores;

    public DetalleTopico registrarTopico(RegistrarTopico datos){
        if(!usuarioRepositorio.existsById(datos.idUsuario())){
            throw new Validacion("Id de usuario no registrado");
        }

        validadores.forEach(v->v.validar(datos));

        Usuario usuario = usuarioRepositorio.getReferenceById(datos.idUsuario());
        var topicoNuevo = new Topico(datos.titulo(), datos.mensaje(),usuario, datos.curso());

        topicoRepositorio.save(topicoNuevo);

        return new DetalleTopico(topicoNuevo);
    }
}
