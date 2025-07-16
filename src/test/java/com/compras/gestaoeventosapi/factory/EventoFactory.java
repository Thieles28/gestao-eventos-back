package com.compras.gestaoeventosapi.factory;

import com.compras.gestaoeventosapi.entity.Evento;
import com.compras.gestaoeventosapi.model.request.EventoRequest;
import com.compras.gestaoeventosapi.model.response.EventoResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventoFactory {
    public static Evento criarEvento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Evento.builder()
                .id(1L)
                .titulo("Conferência de Tecnologia 2025")
                .descricao("Evento anual reunindo especialistas em inovação tecnológica, startups e investidores.")
                .dataHoraEvento(LocalDateTime.parse("2025-09-10 09:00:00", formatter))
                .localEvento("Centro de Convenções Digital Tech")
                .deleted(false)
                .build();
    }

    public static EventoRequest criarEventoRequest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return EventoRequest.builder()
                .id(1L)
                .titulo("Conferência de Tecnologia 2025")
                .descricao("Evento anual reunindo especialistas em inovação tecnológica, startups e investidores.")
                .dataHoraEvento(LocalDateTime.parse("2025-09-10 09:00:00", formatter))
                .localEvento("Centro de Convenções Digital Tech")
                .build();
    }

    public static EventoResponse criarEventoResponse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return EventoResponse.builder()
                .id(1L)
                .titulo("Conferência de Tecnologia 2025")
                .descricao("Evento anual reunindo especialistas em inovação tecnológica, startups e investidores.")
                .dataHoraEvento(LocalDateTime.parse("2025-09-10 09:00:00", formatter))
                .localEvento("Centro de Convenções Digital Tech")
                .build();
    }

    public static Evento salvarEvento() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Evento.builder()
                .id(1L)
                .titulo("Conferência de Tecnologia 2025")
                .descricao("Evento anual reunindo especialistas em inovação tecnológica, startups e investidores.")
                .dataHoraEvento(LocalDateTime.parse("2025-09-10 09:00:00", formatter))
                .localEvento("Centro de Convenções Digital Tech")
                .deleted(false)
                .build();
    }
}