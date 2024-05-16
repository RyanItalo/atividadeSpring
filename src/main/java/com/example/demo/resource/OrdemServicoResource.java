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

import com.example.demo.model.OrdemServico;
import com.example.demo.repository.OrdemServicoRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("ordemservico")
public class OrdemServicoResource {
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@GetMapping
	public List<OrdemServico> list() {
		
		return ordemServicoRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<OrdemServico> findById(@PathVariable Long id) {
		return ordemServicoRepository.findById(id);
	}
			
	@PostMapping
	public ResponseEntity<OrdemServico> create(@RequestBody OrdemServico ordemServico, HttpServletResponse response) {
		
		OrdemServico save = ordemServicoRepository.save(ordemServico);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(save.getId()).toUri();
		return ResponseEntity.created(uri).body(save);
	}
			
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		ordemServicoRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrdemServico> update(@PathVariable Long id, @Valid @RequestBody OrdemServico ordemServico) {
		Optional<OrdemServico> ordemServicoBanco = ordemServicoRepository.findById(id);
		BeanUtils.copyProperties(ordemServico, ordemServicoBanco.get(), "id");
		ordemServicoRepository.save(ordemServicoBanco.get());
		return ResponseEntity.ok(ordemServico);
	}

}
