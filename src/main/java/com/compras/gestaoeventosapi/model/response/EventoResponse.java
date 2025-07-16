package com.compras.gestaoeventosapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataHoraEvento;
    private String localEvento;
}