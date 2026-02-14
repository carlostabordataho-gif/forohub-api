package com.forohub.forohub.controller;

import com.forohub.forohub.config.TokenService;
import com.forohub.forohub.domain.usuario.DatosAutenticacionUsuario;
import com.forohub.forohub.domain.usuario.UsuarioPrincipal;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AutenticacionController(AuthenticationManager authenticationManager,
                                   TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public String autenticar(@RequestBody @Valid DatosAutenticacionUsuario datos) {

        var authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        datos.username(),
                        datos.password()
                );

        var authentication = authenticationManager.authenticate(authenticationToken);

        var usuario = (UsuarioPrincipal) authentication.getPrincipal();

        return tokenService.generarToken(usuario.getUsuario());
    }
}
