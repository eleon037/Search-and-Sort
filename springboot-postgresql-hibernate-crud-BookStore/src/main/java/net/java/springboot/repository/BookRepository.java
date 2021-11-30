package net.java.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.java.springboot.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findByAuthorId(Long authorId);

	// *Get Average
	@Query(value = "SELECT avg(rating) FROM ratings WHERE isbn = ?", nativeQuery = true)
	public Double avg(long isbn);

}
