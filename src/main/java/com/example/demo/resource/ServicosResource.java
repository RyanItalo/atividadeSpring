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

import com.example.demo.model.Servicos;
import com.example.demo.repository.*;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("servico")
public class ServicosResource {
	
	@Autowired
	private ServicosRepository servicosRepository;
	
	@GetMapping
	public List<Servicos> list(){
		return servicosRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Servicos> findById(@PathVariable Long id){
		return servicosRepository.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Servicos> create(@RequestBody Servicos servicos, HttpServletResponse response){
		
		Servicos save = servicosRepository.save(servicos);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		servicosRepository.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Servicos> update(@PathVariable Long id, @RequestBody Servicos servicos){
		Optional<Servicos>servicosBanco = servicosRepository.findById(id);
		BeanUtils.copyProperties(servicos, servicosBanco.get(), "id");
		servicosRepository.save(servicosBanco.get());
		return ResponseEntity.ok(servicos);
	}

}
