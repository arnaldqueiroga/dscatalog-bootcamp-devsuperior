package com.devsuperior.dscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.dscatalog.entities.User;

// O JpaRepository é um tipo genérico, e ele espera dois paramêtros, que é o tipo
// da Entidade (Category) e o tipo do Id (Id)

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	// Método que busque no BD um usuário pelo e-mail (esse método está sendo usado na classe UserInsertValidator)
	User findByEmail (String email);	

}
