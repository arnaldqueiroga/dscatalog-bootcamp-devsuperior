package com.devsuperior.dscatalog.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dscatalog.entities.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@GetMapping // Para configurar que o método abaixo seja um web service, utilizamos a anotation @GetMapping
	
	// Criando o 1º End Point
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = new ArrayList<>(); // Instanciando a lista
		
		// Adicionando elementos na lista, add o novo objeto do meu tipo Category (o L maiúsculo serve
		// para me lembrar que o Id da categoria é Long)
		list.add(new Category (1L, "Books"));
		list.add(new Category (2L, "Electonics"));
		
		// Mandar método FindAll retornar essa lista para o corpo da resposta HTTP dessa requisição
		return ResponseEntity.ok().body(list);
		
		
	}

}
