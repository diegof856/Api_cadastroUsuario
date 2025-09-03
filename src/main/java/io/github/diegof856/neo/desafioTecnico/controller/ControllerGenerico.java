package io.github.diegof856.neo.desafioTecnico.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public interface ControllerGenerico {
    default URI gerarHeaderLocation(UUID id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}
