package com.compras.gestaoeventosapi.service.impl;

import com.compras.gestaoeventosapi.entity.Evento;
import com.compras.gestaoeventosapi.exception.RecursoNaoEncontradoException;
import com.compras.gestaoeventosapi.model.request.EventoRequest;
import com.compras.gestaoeventosapi.model.response.DeleteResponse;
import com.compras.gestaoeventosapi.model.response.EventoResponse;
import com.compras.gestaoeventosapi.repository.GestaoRepository;
import com.compras.gestaoeventosapi.service.GestaoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.compras.gestaoeventosapi.util.MensagensErro.EVENTO_NAO_ENCONTRADO;
import static com.compras.gestaoeventosapi.util.MensagensErro.EVENTO_REMOVIDO;

@Service
@RequiredArgsConstructor
public class GestaoServiceImpl implements GestaoService {

    private final GestaoRepository gestaoRepository;
    private final ModelMapper modelMapper;

    @Override
    public EventoResponse cadastrarEvento(EventoRequest request) {
        var evento = modelMapper.map(request, Evento.class);
        return modelMapper.map(gestaoRepository.save(evento), EventoResponse.class);
    }

    @Override
    public List<EventoResponse> listarEventos() {
        return gestaoRepository.findByDeletedFalse()
                .stream()
                .map(evento -> modelMapper.map(evento, EventoResponse.class))
                .toList();
    }

    @Override
    public EventoResponse consultarEvento(Long id) {
        return gestaoRepository.findById(id)
                .map(evento -> modelMapper.map(evento, EventoResponse.class)
                ).orElseThrow(() -> new RecursoNaoEncontradoException(EVENTO_NAO_ENCONTRADO));
    }

    @Override
    public EventoResponse atualizarEvento(Long id, EventoRequest request) {
        var evento = obterEvento(id);
        modelMapper.map(request, evento);
        return modelMapper.map(gestaoRepository.save(modelMapper.map(evento, Evento.class)), EventoResponse.class);
    }

    public DeleteResponse removerEvento(Long id) {
        var evento = consultarEvento(id);
        var entidade = modelMapper.map(evento, Evento.class);

        entidade.setDeleted(true);
        gestaoRepository.save(entidade);

        return DeleteResponse.builder()
                .id(entidade.getId())
                .mensagem(EVENTO_REMOVIDO)
                .build();
    }

    private Evento obterEvento(Long id) {
        return gestaoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException(EVENTO_NAO_ENCONTRADO));
    }
}