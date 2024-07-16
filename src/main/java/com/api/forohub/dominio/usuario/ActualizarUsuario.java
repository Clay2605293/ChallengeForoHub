package com.api.forohub.dominio.usuario;

import jakarta.validation.constraints.NotNull;

public record ActualizarUsuario(
        @NotNull
        Long id,
        String nombre,
        String password
) {
}
