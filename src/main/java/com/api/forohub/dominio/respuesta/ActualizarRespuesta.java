package com.api.forohub.dominio.respuesta;

import jakarta.validation.constraints.NotNull;

public record ActualizarRespuesta(
        @NotNull
        Long id,
        String solucion
) {
}
