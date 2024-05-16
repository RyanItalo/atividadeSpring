package com.example.demo.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("cliente")
public class ClienteResource {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public List<Cliente> list(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id){
		Optional<Cliente> opF = clienteRepository.findById(id);
		if(opF.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(opF);
		}
	}
	
	@PostMapping
	public ResponseEntity<Cliente> create(@RequestBody Cliente cliente, HttpServletResponse response){
		
		Cliente save = clienteRepository.save(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
		.buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id,
			@RequestBody Cliente cliente){
		Optional<Cliente> clienteBanco = clienteRepository.findById(id);
		BeanUtils.copyProperties(cliente, clienteBanco.get(), "id");
		clienteRepository.save(clienteBanco.get());
		cliente.setId(id);
		return ResponseEntity.ok(cliente);
	}
	

}
