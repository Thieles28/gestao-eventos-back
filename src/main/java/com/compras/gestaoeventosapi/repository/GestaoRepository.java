package com.compras.gestaoeventosapi.repository;

import com.compras.gestaoeventosapi.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GestaoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByDeletedFalse();
}