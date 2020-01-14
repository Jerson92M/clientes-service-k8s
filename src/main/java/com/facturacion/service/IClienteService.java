package com.facturacion.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.facturacion.model.Cliente;

public interface IClienteService {

	public List<Cliente> getAll(String identificacion);
	
	public ResponseEntity<?> add(MultipartFile imagen, Cliente cliente);
	
	public ResponseEntity<?> update(MultipartFile imagen, Integer id, Cliente cliente);
	
	public ResponseEntity<?> delete(Integer id);
}
