package com.forohub.forohub.controller;

import com.forohub.forohub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;

    public TopicoController(TopicoRepository topicoRepository) {
        this.topicoRepository = topicoRepository;
    }

    // ======================
    // Registro de un nuevo tópico
    // ======================
    @PostMapping
    public ResponseEntity<?> registrarTopico(@RequestBody @Valid DatosRegistroTopico datos) {

        // Validar duplicados
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            return ResponseEntity.badRequest()
                    .body("Ya existe un tópico con el mismo título y mensaje");
        }

        // Guardar el tópico
        Topico topico = new Topico(datos);
        topicoRepository.save(topico);

        // Retornar 201 Created con el tópico guardado
        return ResponseEntity.status(201).body(topico);
    }

    // ======================
    // Listado de todos los tópicos
    // ======================
    @GetMapping
    public ResponseEntity<List<DatosListadoTopico>> listarTopicos() {
        List<DatosListadoTopico> topicos = topicoRepository.findAll()
                .stream()
                .map(DatosListadoTopico::new) // Convertir a DTO
                .toList();

        return ResponseEntity.ok(topicos);
    }

    // ======================
    // Detalle de un tópico por ID
    // ======================
    @GetMapping("/{id}")
    public ResponseEntity<?> detalleTopico(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }

        return ResponseEntity.ok(new DatosDetalleTopico(optionalTopico.get()));
    }

    // ======================
    // Actualizar un tópico
    // ======================
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarTopico(@PathVariable Long id,
                                              @RequestBody @Valid DatosActualizarTopico datos) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }

        // Validar duplicados (excepto el mismo ID)
        boolean existeDuplicado = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())
                && !optionalTopico.get().getId().equals(id);
        if (existeDuplicado) {
            return ResponseEntity.badRequest()
                    .body("Ya existe otro tópico con el mismo título y mensaje");
        }

        // Actualizar campos
        Topico topico = optionalTopico.get();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setAutor(datos.autor());
        topico.setCurso(datos.curso());

        topicoRepository.save(topico);

        return ResponseEntity.ok(new DatosDetalleTopico(topico));
    }

    // ======================
    // Eliminación de un tópico
    // ======================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.status(404).body("Tópico no encontrado");
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
