package com.distribuidora18.springboot.backend.apirest.models.repository;

import com.distribuidora18.springboot.backend.apirest.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoCliente  extends JpaRepository<Cliente,Long >{

    //@Query(value = "update clientes SET nombre =: nombre, apellido =: apellido where idCliente=: IdCliente",nativeQuery = true)
    //void actualizarCliente(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("id_cliente") long id_cliente);



    // @Query(value="select p.id_prestamo, p.fecha, e.nombre, e.apellido, l.titulo, l.autor from prestamo as p inner join estudiante as e on p.documento = e.documento inner join libro as l on p.isbn = l.isbn where p.documento= :est", nativeQuery = true)
   // List<Object[]> findEstudiantePrestamo(String est);

}
