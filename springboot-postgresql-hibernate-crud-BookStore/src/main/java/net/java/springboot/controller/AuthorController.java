package net.java.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.springboot.exception.ResourceNotFoundException;
import net.java.springboot.model.Author;
import net.java.springboot.repository.AuthorRepository;

@RestController
@RequestMapping("/api/v1")

public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	// gets all authors
	@GetMapping("/authors")
	public List<Author> getAllAuthors() {
		return this.authorRepository.findAll();
	}

	// *Get authors by name
	@GetMapping("/authors/name/{authorFirstName}")
	public Author getAuthorByName(@PathVariable String authorFirstName) {

		List<Author> authors = new ArrayList<>();
		authorRepository.findAll().forEach(authors::add);

		for (int i = 0; i < authors.size(); i++) {
			if (authors.get(i).getFirst_name().equalsIgnoreCase(authorFirstName)) {
				return authors.get(i);
			}
		}

		return null;

	}

	// posts author
	@PostMapping("/authors")
	public Author createAuthor(@Valid @RequestBody Author author) {
		return this.authorRepository.save(author);
	}

	// delete book by id
	@DeleteMapping("/authors/{authorID}")
	public Map<String, Boolean> deleteAuthor(@PathVariable(value = "authorID") Long authorID)
			throws ResourceNotFoundException {

		Author author = authorRepository.findById(authorID)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this ID ::" + authorID));
		this.authorRepository.delete(author);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;

	}



}
