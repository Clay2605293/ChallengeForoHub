package com.api.forohub.dominio.usuario;

import jakarta.validation.constraints.NotBlank;

public record Autenticar(
        @NotBlank
        String email,
        @NotBlank
        String password) {
}
