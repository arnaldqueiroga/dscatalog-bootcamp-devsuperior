package com.devsuperior.dscatalog.services;


import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.RoleDTO;
import com.devsuperior.dscatalog.dto.UserDTO;
import com.devsuperior.dscatalog.entities.Role;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.RoleRepository;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;






@Service 
public class UserService {	
	
	@Autowired
	private UserRepository repository;	
	
	//Fazendo injeção de dependencia do UserRepository
	@Autowired 
	private RoleRepository roleRepository;
	 
	
	@Transactional(readOnly = true) // readOnly = true serve para evitarmos que façamos locking no banco, ou seja, não precisamos travar o banco somente para efetuar uma leitura
	public Page<UserDTO> findAllPaged(PageRequest pageRequest) {
		Page<User> list = repository.findAll(pageRequest);
		
		return list.map(x -> new UserDTO(x));
		
	}
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity); // aqui estamos passando o conjunto de Usuários (que está lá no construtor da classe UserDTO)
	}

	// Criando o método insert
	@Transactional
	public UserDTO insert(UserDTO dto) {
		User entity = new User();
		copyDtoToEntity(dto, entity);
		//entity.setName(dto.getName());
		entity = repository.save(entity);
		return new UserDTO(entity);
		
	}
	


	// Criando o método update
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			User entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new UserDTO(entity);		
		}
		
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " +  id );			
		}
		
	}
	
	// Criando o método Delete
		
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " +  id );			
		}
		
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
		
	}	
	
	// Método auxiliar 	copyDtoToEntity
	private void copyDtoToEntity(UserDTO dto, User entity) {
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setEmail(dto.getEmail());
		
		//Carregando categorias no dto para a entidade
		entity.getRoles().clear(); // para limpar as categorias
		for (RoleDTO roleDto : dto.getRoles()) {
			Role role = roleRepository.getOne(roleDto.getId());
			entity.getRoles().add(role);
			
			
		}
		
		
		
	}
	

}
