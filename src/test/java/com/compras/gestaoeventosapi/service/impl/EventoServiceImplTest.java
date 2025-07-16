package com.compras.gestaoeventosapi.service.impl;

import com.compras.gestaoeventosapi.entity.Evento;
import com.compras.gestaoeventosapi.exception.RecursoNaoEncontradoException;
import com.compras.gestaoeventosapi.factory.EventoFactory;
import com.compras.gestaoeventosapi.model.request.EventoRequest;
import com.compras.gestaoeventosapi.model.response.DeleteResponse;
import com.compras.gestaoeventosapi.model.response.EventoResponse;
import com.compras.gestaoeventosapi.repository.GestaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static com.compras.gestaoeventosapi.util.MensagensErro.EVENTO_REMOVIDO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventoServiceImplTest {
    @InjectMocks
    private GestaoServiceImpl gestaoService;

    @Mock
    private GestaoRepository gestaoRepository;

    @Mock
    private ModelMapper modelMapper;

    private Evento evento;
    private Evento salvarEvento;
    private EventoRequest eventoRequest;
    private EventoResponse eventoResponse;

    @BeforeEach
    void setUp() {
        evento = EventoFactory.criarEvento();
        eventoRequest = EventoFactory.criarEventoRequest();
        eventoResponse = EventoFactory.criarEventoResponse();
        salvarEvento = EventoFactory.salvarEvento();
    }

    @Test
    void cadastrarEvento() {
        when(modelMapper.map(eventoRequest, Evento.class)).thenReturn(evento);
        when(gestaoRepository.save(evento)).thenReturn(evento);
        when(modelMapper.map(evento, EventoResponse.class)).thenReturn(eventoResponse);

        var response = gestaoService.cadastrarEvento(eventoRequest);

        assertEquals(1L, response.getId());
        assertEquals(evento.getTitulo(), response.getTitulo());
        verify(gestaoRepository).save(evento);
    }

    @Test
    void listarEventos() {
        var eventos = List.of(evento);
        when(gestaoRepository.findByDeletedFalse()).thenReturn(eventos);
        when(modelMapper.map(evento, EventoResponse.class)).thenReturn(eventoResponse);

        var response = gestaoService.listarEventos();

        assertEquals(1L, response.size());
        assertEquals(evento.getTitulo(), response.getFirst().getTitulo());
    }

    @Test
    void consultarEvento_quandoExiste() {
        when(gestaoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(modelMapper.map(evento, EventoResponse.class)).thenReturn(eventoResponse);

        var response = gestaoService.consultarEvento(1L);

        assertEquals(evento.getTitulo(), response.getTitulo());
    }

    @Test
    void consultarEvento_quandoNaoExiste() {
        when(gestaoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RecursoNaoEncontradoException.class, () -> gestaoService.consultarEvento(1L));
    }

    @Test
    void atualizarEvento() {
        when(gestaoRepository.findById(1L)).thenReturn(Optional.of(evento));
        doNothing().when(modelMapper).map(eventoRequest, evento);
        when(modelMapper.map(any(Evento.class), eq(Evento.class))).thenReturn(evento);
        when(gestaoRepository.save(evento)).thenReturn(evento);
        when(modelMapper.map(evento, EventoResponse.class)).thenReturn(eventoResponse);
        var response = gestaoService.atualizarEvento(1L, eventoRequest);
        assertNotNull(response);
        assertEquals(evento.getTitulo(), response.getTitulo());
        verify(gestaoRepository).findById(1L);
        verify(modelMapper).map(eventoRequest, evento);
        verify(modelMapper).map(evento, EventoResponse.class);
        verify(gestaoRepository).save(evento);
    }

    @Test
    void removerEvento_softDelete_ComSucesso() {
        when(gestaoRepository.findById(evento.getId())).thenReturn(Optional.of(evento));
        when(modelMapper.map(evento, EventoResponse.class)).thenReturn(eventoResponse);

        when(modelMapper.map(eventoResponse, Evento.class)).thenReturn(salvarEvento);
        when(gestaoRepository.save(any(Evento.class))).thenAnswer(inv -> inv.getArgument(0));

        DeleteResponse resp = gestaoService.removerEvento(evento.getId());

        assertNotNull(resp);
        assertEquals(evento.getId(), resp.getId());
        assertEquals(EVENTO_REMOVIDO, resp.getMensagem());

        ArgumentCaptor<Evento> captor = ArgumentCaptor.forClass(Evento.class);
        verify(gestaoRepository).save(captor.capture());
        Evento salvo = captor.getValue();
        assertTrue(salvo.isDeleted(), "Evento deveria ter sido marcado como deleted = true.");
    }
}