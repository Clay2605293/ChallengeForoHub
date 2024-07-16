package com.api.forohub.dominio.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepositorio extends JpaRepository<Topico,Long> {
    Page<Topico> findByStatusTrue(Pageable paginacion);

    Boolean existsByTitulo(String titulo);
    Boolean existsByMensaje(String mensaje);
}
