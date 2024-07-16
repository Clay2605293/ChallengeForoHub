package com.api.forohub.dominio.topico;

public record DetalleTopico(
        Long id,
        String usuario,
        String titulo,
        String mensaje,
        String curso
) {
    public DetalleTopico(Topico topico){
        this(topico.getId(), topico.getUsuario().getNombre(), topico.getTitulo(), topico.getMensaje(), topico.getCurso());
    }
}
