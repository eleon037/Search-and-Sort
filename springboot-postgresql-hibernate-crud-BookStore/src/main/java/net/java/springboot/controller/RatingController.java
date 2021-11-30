package net.java.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.springboot.exception.ResourceNotFoundException;
import net.java.springboot.model.Rating;
import net.java.springboot.repository.BookRepository;
import net.java.springboot.repository.RatingRepository;

@RestController
@RequestMapping("/api/v1")
public class RatingController {

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private BookRepository bookRepository;

	// get ratings in a descending order
	@GetMapping("ratings")
	public List<Rating> getAllRatings() {

		// return this.ratingRepository.findAll(Sort.by(Sort.Direction.DESC, "rating"));

		List<Rating> ratings = new ArrayList<>();
		ratingRepository.findAll().forEach(ratings::add);

		return ratings;

	}

	// get ratings by ISBN
	/*
	 * @GetMapping("/ratings/{ISBN}") public ResponseEntity<Rating>
	 * getRatingByISBN(@PathVariable(value= "ISBN") Long ISBN) throws
	 * ResourceNotFoundException { Rating rating = ratingRepository.findById(ISBN)
	 * .orElseThrow(() -> new
	 * ResourceNotFoundException("Ratings for this ISBN not found: " + ISBN));
	 * return ResponseEntity.ok().body(rating); }
	 */

	// get the average rating for a book
	@GetMapping("/ratings/book/{isbn}")
	public Double avg(@PathVariable Long isbn) {
		return ratingRepository.avg(isbn);
	}

	// save rating
	@PostMapping("ratings")
	public Rating createRating(@RequestBody Rating rating) {
		return this.ratingRepository.save(rating);
	}

	// update rating
	@PutMapping("ratings/{rid}")
	public ResponseEntity<Rating> updateRating(@PathVariable(value = "RID") Long RID,
			@Valid @RequestBody Rating ratingDetails) throws ResourceNotFoundException {
		Rating rating = ratingRepository.findById(RID)
				.orElseThrow(() -> new ResourceNotFoundException("Ratings for this RID not found: " + RID));

		rating.setComment(ratingDetails.getComment());
		rating.setRating(ratingDetails.getRating());

		return ResponseEntity.ok(this.ratingRepository.save(rating));
	}

	// delete rating
	@DeleteMapping("ratings/{rid}")
	public Map<String, Boolean> deleteRating(@PathVariable(value = "RID") Long RID) throws ResourceNotFoundException {
		Rating rating = ratingRepository.findById(RID)
				.orElseThrow(() -> new ResourceNotFoundException("Ratings for this RID not found: " + RID));

		this.ratingRepository.delete(rating);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}


}
