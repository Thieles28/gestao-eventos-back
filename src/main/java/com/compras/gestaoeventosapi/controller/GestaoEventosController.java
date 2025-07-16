package com.compras.gestaoeventosapi.controller;

import com.compras.gestaoeventosapi.model.request.EventoRequest;
import com.compras.gestaoeventosapi.model.response.DeleteResponse;
import com.compras.gestaoeventosapi.model.response.EventoResponse;
import com.compras.gestaoeventosapi.service.GestaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("eventos")
@RequiredArgsConstructor
@Tag(name = "Evento", description = "Operações relacionadas a eventos")
public class GestaoEventosController {

    private final GestaoService gestaoService;

    @PostMapping
    @Operation(summary = "Cadastrar evento")
    @ApiResponse(responseCode = "201", description = "Cadastrar evento",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoResponse.class)))
    public ResponseEntity<EventoResponse> cadastrarEvento(@RequestBody @Valid EventoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gestaoService.cadastrarEvento(request));
    }

    @GetMapping
    @Operation(summary = "Listar evento")
    @ApiResponse(responseCode = "200", description = "Listar evento",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoResponse.class)))
    public ResponseEntity<List<EventoResponse>> listarEventos() {
        return ResponseEntity.status(HttpStatus.OK).body(gestaoService.listarEventos());
    }

    @GetMapping("{id}")
    @Operation(summary = "Consultar evento")
    @ApiResponse(responseCode = "200", description = "Consultar evento",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoResponse.class)))
    public ResponseEntity<EventoResponse> consultarEvento(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(gestaoService.consultarEvento(id));
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualizar evento")
    @ApiResponse(responseCode = "201", description = "Atualizar evento",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoResponse.class)))
    public ResponseEntity<EventoResponse> atualizarEvento(@PathVariable("id") Long id, @RequestBody @Valid EventoRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(gestaoService.atualizarEvento(id, request));
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remover evento")
    @ApiResponse(responseCode = "201", description = "Remover evento",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoResponse.class)))
    public ResponseEntity<DeleteResponse> removerEvento(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(gestaoService.removerEvento(id));
    }
}