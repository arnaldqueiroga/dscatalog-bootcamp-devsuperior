package com.devsuperior.dscatalog.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	// Declarando dependência do Controlador para o Service	
	@Autowired
	private CategoryService service;
	
	@GetMapping // Para configurar que o método abaixo seja um web service, utilizamos a anotation @GetMapping
	
	// Criando o 1º End Point
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = service.findAll(); // Essa lista chama o Service, que por sua vez chama o Repository, que por sua vez foi no BD, trouxe os objetos, os instanciou, e guardou nessa lista 
				
		// Mandar método FindAll retornar essa lista para o corpo da resposta HTTP dessa requisição
		return ResponseEntity.ok().body(list);
				
	}
	
	// Criando End Point para buscar categoria por id
		@GetMapping(value = "/{id}")		
		public ResponseEntity<CategoryDTO> findById(@PathVariable Long id ) {
			CategoryDTO dto = service.findById(id);		
			return ResponseEntity.ok().body(dto);
			
			
		}
		
		// Criando End Point para inserir uma nova categoria 
		@PostMapping
		public ResponseEntity<CategoryDTO> insert(@RequestBody CategoryDTO dto){
			dto = service.insert(dto);
			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(dto.getId()).toUri();
					
			return ResponseEntity.created(uri).body(dto);
			
		}
		
	
	
	

}
