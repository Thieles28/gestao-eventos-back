package com.compras.gestaoeventosapi.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventoRequest {
    private Long id;
    @NotBlank
    private String titulo;
    @NotBlank
    private String descricao;
    @NotNull
    private LocalDateTime dataHoraEvento;
    @NotBlank
    private String localEvento;
}