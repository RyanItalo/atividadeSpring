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

import com.example.demo.model.Funcionario;
import com.example.demo.repository.FuncionarioRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("funcionario")
public class FuncionarioResource {
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@GetMapping
	public List<Funcionario> list() {
		return funcionarioRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Funcionario> findById(@PathVariable Long id) {
		return funcionarioRepository.findById(id);
	}
			
	@PostMapping
	public ResponseEntity<Funcionario> create(@RequestBody Funcionario Funcionario, HttpServletResponse response) {
		
		Funcionario save = funcionarioRepository.save(Funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
			
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		funcionarioRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Long id, @Valid @RequestBody Funcionario Funcionario) {
		Optional<Funcionario> funcionarioBanco = funcionarioRepository.findById(id);
		BeanUtils.copyProperties(Funcionario, funcionarioBanco.get(), "id");
		funcionarioRepository.save(funcionarioBanco.get());
		return ResponseEntity.ok(Funcionario);
	}
		
}
