package net.java.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.java.springboot.model.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	// Find by ISBN
	public List<Rating> findByBookISBN(long isbn);

	// Find by ISBN Rating Desc
	public List<Rating> findByBookISBNOrderByRatingDesc(long isbn);

	// Get Average
	@Query(value = "SELECT avg(rating) FROM ratings WHERE isbn = ?", nativeQuery = true)
	public Double avg(long isbn);

}
