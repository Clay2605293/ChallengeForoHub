package com.api.forohub.controlador;

import com.api.forohub.dominio.topico.*;
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
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class ControladorTopic {

    @Autowired
    TopicoRepositorio topicoRepositorio;

    @Autowired
    TopicoServicio topicoServicio;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalleTopico> registrarTopico(@RequestBody @Valid RegistrarTopico datos){
        var response = topicoServicio.registrarTopico(datos);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DetalleTopico>> listaTopicos(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepositorio.findByStatusTrue(paginacion).map(DetalleTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleTopico> buscarTopico(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);

        var response = new DetalleTopico(topico);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid ActualizarTopico topicoActualizado) {
        Topico topico = topicoRepositorio.getReferenceById(topicoActualizado.id());
        topico.actualizarDatos(topicoActualizado);

        var response = new DetalleTopico(topico);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepositorio.getReferenceById(id);
        topico.borrarTopico();
        return ResponseEntity.noContent().build();
    }
}
