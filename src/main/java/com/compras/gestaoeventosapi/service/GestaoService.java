package com.compras.gestaoeventosapi.service;

import com.compras.gestaoeventosapi.model.request.EventoRequest;
import com.compras.gestaoeventosapi.model.response.DeleteResponse;
import com.compras.gestaoeventosapi.model.response.EventoResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface GestaoService {
    EventoResponse cadastrarEvento(@Valid EventoRequest request);

    List<EventoResponse> listarEventos();

    EventoResponse consultarEvento(Long id);

    EventoResponse atualizarEvento(Long id, @Valid EventoRequest request);

    DeleteResponse removerEvento(Long id);
}