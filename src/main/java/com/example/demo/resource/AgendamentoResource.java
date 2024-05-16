package com.example.demo.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.example.demo.model.Agendamento;
import com.example.demo.repository.AgendamentoRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
@CrossOrigin(origins = "http://localhost4200")
@RestController
@RequestMapping("agendamento")
public class AgendamentoResource {
	
	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@GetMapping
	public List<Agendamento> list() {
		
		return agendamentoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Agendamento> findById(@PathVariable Long id) {
		return agendamentoRepository.findById(id);
	}
			
	@PostMapping
	public ResponseEntity<Agendamento> create(@RequestBody Agendamento Agendamento, HttpServletResponse response) {
		
		Agendamento save = agendamentoRepository.save(Agendamento);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
			
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		agendamentoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Agendamento> update(@PathVariable Long id, @Valid @RequestBody Agendamento Agendamento) {
		Optional<Agendamento> AgendamentoBanco = agendamentoRepository.findById(id);
		BeanUtils.copyProperties(Agendamento, AgendamentoBanco.get(), "id");
		agendamentoRepository.save(AgendamentoBanco.get());
		return ResponseEntity.ok(Agendamento);
	}
		
}

