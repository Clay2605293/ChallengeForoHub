package com.api.forohub.controlador;

import com.api.forohub.dominio.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class ControladorUsuario {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<RespuestaUsuario> registrarUsuario(@RequestBody @Valid RegistrarUsuario datos){
        String encodedPassword = passwordEncoder.encode(datos.password());
        var usuarioNuevo = new Usuario(datos.nombre(),datos.email(),encodedPassword);
        usuarioRepositorio.save(usuarioNuevo);

        var response = new RespuestaUsuario(usuarioNuevo);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<RespuestaUsuario>> listaUsuarios(@PageableDefault(size = 4) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepositorio.findAll(paginacion).map(RespuestaUsuario::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaUsuario> buscarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepositorio.getReferenceById(id);

        var response = new RespuestaUsuario(usuario);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaUsuario> actualizarUsuario(@RequestBody @Valid ActualizarUsuario actualizarUsuario) {
        Usuario usuario = usuarioRepositorio.getReferenceById(actualizarUsuario.id());

        String encodedPassword = null;
        if(actualizarUsuario.password() != null){
            encodedPassword = passwordEncoder.encode(actualizarUsuario.password());
        }
        usuario.actualizarDatos(actualizarUsuario.nombre(), encodedPassword);

        var response = new RespuestaUsuario(usuario);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
