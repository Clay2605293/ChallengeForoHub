package com.api.forohub.controlador;

import com.api.forohub.dominio.respuesta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class ControladorRespuesta {
    @Autowired
    RespuestaRepositorio respuestaRepositorio;

    @Autowired
    RespuestaServicio respuestaServicio;

    @PostMapping
    @Transactional
    public ResponseEntity<MostrarRespuesta> registrarRespuesta(@RequestBody @Valid RegistroRespuesta datos){
        var response = respuestaServicio.registrarRespuesta(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarRespuesta>> listaRespuestas(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(respuestaRepositorio.findAll(paginacion).map(MostrarRespuesta::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MostrarRespuesta> buscarRespuesta(@PathVariable Long id) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(id);

        var response = new MostrarRespuesta(respuesta);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid ActualizarRespuesta rtaActualizada) {
        Respuesta respuesta = respuestaRepositorio.getReferenceById(rtaActualizada.id());
        respuesta.actualizarDatos(rtaActualizada);

        var response = new MostrarRespuesta(respuesta);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarRespuesta(@PathVariable Long id) {
        respuestaRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
