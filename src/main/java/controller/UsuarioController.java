package com.forohub.forohub.controller;

import com.forohub.forohub.domain.usuario.Usuario;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> registrarUsuario(@RequestBody @Valid Usuario datos) {
        if (usuarioRepository.existsByUsername(datos.getUsername())) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }

        // Encriptar la contraseña antes de guardar
        datos.setPassword(passwordEncoder.encode(datos.getPassword()));
        usuarioRepository.save(datos);

        return ResponseEntity.status(201).body("Usuario creado correctamente");
    }

}
