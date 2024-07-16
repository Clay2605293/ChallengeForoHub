package com.api.forohub.dominio.respuesta;

import java.time.LocalDateTime;

public record MostrarRespuesta(
        Long id,
        String autor,
        String tituloTopico,
        LocalDateTime fechaCreacion,
        String solucion
) {
    public MostrarRespuesta(Respuesta respuesta){
        this(respuesta.getId(),
                respuesta.getAutor().getNombre(),
                respuesta.getTopico().getTitulo(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion());
    }
}
