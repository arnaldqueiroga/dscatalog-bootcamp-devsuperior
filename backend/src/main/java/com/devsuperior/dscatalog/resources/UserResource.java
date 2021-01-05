package com.devsuperior.dscatalog.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	// Declarando dependência do Controlador para o Service	
	@Autowired
	private UserService service;
	
	
	
	// Criando o 1º End Point
	@GetMapping // Para configurar que o método abaixo seja um web service, utilizamos a anotation @GetMapping
	public ResponseEntity<Page<UserDTO>> findAll(
			
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "firstName") String orderBy
						
			) {
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);		
		
		// Essa lista chama o Service, que por sua vez chama o Repository, que por sua vez foi no BD, trouxe os objetos, os instanciou, 
		//e guardou nessa lista				
		// Mandar método FindAll retornar essa lista para o corpo da resposta HTTP dessa requisição
		Page<UserDTO> list = service.findAllPaged(pageRequest); 
		
		return ResponseEntity.ok().body(list);
				
	}
	
	// Criando End Point para buscar categoria por id - GET
		@GetMapping(value = "/{id}")		
		public ResponseEntity<UserDTO> findById(@PathVariable Long id ) {
			UserDTO dto = service.findById(id);		
			return ResponseEntity.ok().body(dto);		
			
		}
		
		
		// Criando End Point para inserir uma nova categoria - POST
		@PostMapping
		public ResponseEntity<UserDTO> insert(@Valid @RequestBody UserInsertDTO dto){
			UserDTO newDTO = service.insert(dto);			
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(newDTO.getId()).toUri();
					
			return ResponseEntity.created(uri).body(newDTO);			
		}
		
		// Criando End Point para atualizar Categoria - PUT
		@PutMapping(value = "/{id}")
		public ResponseEntity<UserDTO> update(@Valid @PathVariable Long id, @RequestBody UserUpdateDTO dto){
			UserDTO newDto = service.update(id, dto);
			return ResponseEntity.ok().body(newDto);
					
		}
		
		// Criando End Point para deletar Categoria - DELET
				@DeleteMapping(value = "/{id}")
				public ResponseEntity<UserDTO> delete(@PathVariable Long id){
					service.delete(id);
					return ResponseEntity.noContent().build();
							
				}
	
	
	

}
