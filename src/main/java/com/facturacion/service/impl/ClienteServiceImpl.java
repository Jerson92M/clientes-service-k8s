package com.facturacion.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.facturacion.model.Cliente;
import com.facturacion.repository.IClienteRepository;
import com.facturacion.service.IClienteService;
import com.facturacion.utils.Comunes;
import com.facturacion.utils.Response;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	IClienteRepository repository;

	@Override
	public List<Cliente> getAll(String identificacion) {
		
		List<Cliente> clientes;
		
		if (identificacion == null || identificacion.isEmpty()) {
			clientes = repository.findAll();
		} else {	
			clientes = repository.findAllByIdentificacionContains(identificacion);
		}
		
		return clientes;
	}

	@Override
	public ResponseEntity<?> add(MultipartFile imagen, Cliente cliente) {

		String message = "OK";
		HttpStatus status = HttpStatus.OK;
		Comunes comunes = Comunes.getComunes();

		try {

			String encode64 = comunes.convertirBase64(imagen);
			cliente.setFoto(encode64);

		} catch (IOException e) {

			message = "Error al cargar la imagen";
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}

		repository.save(cliente);

		return new ResponseEntity<>(new Response(message), status);

	}

	@Override
	public ResponseEntity<?> update(MultipartFile imagen, Integer id, Cliente cliente) {

		String message = "OK";
		HttpStatus status = HttpStatus.CREATED;
		Comunes comunes = Comunes.getComunes();

		repository.getOne(id);

		try {

			String encode64 = comunes.convertirBase64(imagen);
			cliente.setFoto(encode64);

		} catch (IOException e) {

			message = "Error al cargar la imagen";
			status = HttpStatus.INTERNAL_SERVER_ERROR;

		}

		cliente.setId(id);
		repository.save(cliente);

		return new ResponseEntity<>(new Response(message), status);

	}

	@Override
	public ResponseEntity<?> delete(Integer id) {

		String message = "OK";
		HttpStatus status = HttpStatus.NO_CONTENT;

		repository.deleteById(id);

		return new ResponseEntity<>(new Response(message), status);

	}

}
