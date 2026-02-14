package com.forohub.forohub.service;

import com.forohub.forohub.domain.topico.DatosListadoTopico;
import com.forohub.forohub.domain.topico.DatosRegistroTopico;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    private final TopicoRepository repository;

    public TopicoService(TopicoRepository repository) {
        this.repository = repository;
    }

    public DatosListadoTopico registrarTopico(DatosRegistroTopico datos) {
        if(repository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Tópico duplicado");
        }
        Topico topico = new Topico(datos);
        repository.save(topico);
        return new DatosListadoTopico(topico);
    }

    public List<DatosListadoTopico> listarTopicos() {
        return repository.findAll()
                .stream()
                .map(DatosListadoTopico::new)
                .collect(Collectors.toList());
    }

    public DatosListadoTopico obtenerTopico(Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return new DatosListadoTopico(topico);
    }
}
