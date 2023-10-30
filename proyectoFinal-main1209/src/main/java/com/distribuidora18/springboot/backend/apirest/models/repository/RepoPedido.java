package com.distribuidora18.springboot.backend.apirest.models.repository;

import com.distribuidora18.springboot.backend.apirest.models.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoPedido extends JpaRepository<Pedido,Long> {
}
