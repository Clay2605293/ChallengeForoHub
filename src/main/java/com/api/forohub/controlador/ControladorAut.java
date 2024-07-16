package com.api.forohub.controlador;

import com.api.forohub.dominio.usuario.Autenticar;
import com.api.forohub.dominio.usuario.Usuario;
import com.api.forohub.infra.seguridad.TokenServ;
import com.api.forohub.infra.seguridad.TokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ControladorAut {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServ tokenServ;

    @PostMapping
    public ResponseEntity<TokenJWT> autenticar(@RequestBody @Valid Autenticar dataUsuario){
        Authentication authToken = new UsernamePasswordAuthenticationToken(dataUsuario.email(), dataUsuario.password());
        var usuarioAutenticado = authenticationManager.authenticate(authToken);
        var JWTtoken = tokenServ.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(JWTtoken));
    }
}
