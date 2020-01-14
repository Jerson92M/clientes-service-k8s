package com.facturacion.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.facturacion.model.Cliente;
import com.facturacion.service.IClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	IClienteService service;
	
	@GetMapping
	public List<Cliente> getAll(@RequestParam( required = false) String identificacion){
		
		List<Cliente> clientes =  service.getAll(identificacion);
		
		return clientes;
	}
	
	@PostMapping
	public ResponseEntity<?> add(@RequestPart(required = false) MultipartFile imagen, @RequestPart Cliente cliente){
		
		return service.add(imagen, cliente);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@RequestPart(required = false) MultipartFile imagen,
									@PathVariable("id") Integer id,
									@RequestPart Cliente cliente){
		
		return service.update(imagen, id, cliente);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Integer id){
		
		return service.delete(id);
		
	}
}
