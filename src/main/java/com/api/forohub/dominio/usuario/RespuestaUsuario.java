package com.api.forohub.dominio.usuario;

public record RespuestaUsuario(
        Long id,
        String nombre,
        String email
) {
    public RespuestaUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
