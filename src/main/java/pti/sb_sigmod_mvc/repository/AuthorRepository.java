package pti.sb_sigmod_mvc.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import pti.sb_sigmod_mvc.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Integer> {
	
	@Query("SELECT * FROM authors WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Author> findByNameContainingIgnoreCase(String name);

}
