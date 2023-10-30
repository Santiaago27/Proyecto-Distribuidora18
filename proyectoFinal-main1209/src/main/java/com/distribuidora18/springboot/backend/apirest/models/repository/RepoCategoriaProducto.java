package com.distribuidora18.springboot.backend.apirest.models.repository;

import com.distribuidora18.springboot.backend.apirest.models.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoCategoriaProducto extends JpaRepository<CategoriaProducto,Long> {
}
