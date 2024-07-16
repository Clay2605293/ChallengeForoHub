package com.api.forohub.dominio.usuario;

import jakarta.validation.constraints.NotBlank;

public record RegistrarUsuario(
        @NotBlank
        String email,
        @NotBlank
        String nombre,
        @NotBlank
        String password
) {
}
