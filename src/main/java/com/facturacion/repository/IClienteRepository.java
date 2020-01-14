package com.facturacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.facturacion.model.Cliente;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Integer>{

	List<Cliente> findAllByIdentificacionContains(String identificacion);
}
