package com.api.forohub.dominio.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroRespuesta(
        @NotNull
        Long idUsuario,
        @NotNull
        Long idTopico,
        @NotBlank
        String solucion
) {
}
