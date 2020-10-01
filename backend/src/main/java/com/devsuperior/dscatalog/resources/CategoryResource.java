package com.devsuperior.dscatalog.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	// Declarando dependência do COntrolador para o Service	
	@Autowired
	private CategoryService service;
	
	@GetMapping // Para configurar que o método abaixo seja um web service, utilizamos a anotation @GetMapping
	
	// Criando o 1º End Point
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<CategoryDTO> list = service.findAll(); // Essa lista chama o Service, que por sua vez chama o Repository, que por sua vez foi no BD, trouxe os objetos, os instanciou, e guardou nessa lista 
				
		// Mandar método FindAll retornar essa lista para o corpo da resposta HTTP dessa requisição
		return ResponseEntity.ok().body(list);
		
		
	}

}
