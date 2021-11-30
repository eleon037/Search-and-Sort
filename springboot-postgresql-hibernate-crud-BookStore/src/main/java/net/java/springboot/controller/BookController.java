package net.java.springboot.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.springboot.exception.ResourceNotFoundException;
import net.java.springboot.model.Book;
import net.java.springboot.repository.BookRepository;
import net.java.springboot.service.BookService;

@RestController
@RequestMapping("/api/v1/")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	// get books
	@GetMapping("/books")
	public List<Book> getAllBooks() {

		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);

		for (int i = 0; i < books.size(); i++) {

			books.get(i).setAvgRating(bookRepository.avg(books.get(i).getISBN()));

		}

		return books;
	}

	// Addition to the search by ISBN
	// get book by id
	@GetMapping("/books/{isbn}")
	public ResponseEntity<Book> getBookByID(@PathVariable(value = "isbn") Long bookISBN)
			throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookISBN)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this ID ::" + bookISBN));
		return ResponseEntity.ok().body(book);
	}

	// *Get top ten best seller books
	@GetMapping("/books/bestsellers")
	public List<Book> getBestSellers() {

		List<Book> bestSellers = new ArrayList<>();

		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);

		Collections.sort(books);

		for (int i = 0; i < 10; i++) {

			bestSellers.add(books.get(i));

			bestSellers.get(i).setAvgRating(bookRepository.avg(bestSellers.get(i).getISBN()));

		}

		return bestSellers;
	}

	// *Get Book by books's name
	@GetMapping("/books/name/{bookName}")
	public Book getBookByName(@PathVariable String bookName) {

		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);

		for (int i = 0; i < books.size(); i++) {

			books.get(i).setAvgRating(bookRepository.avg(books.get(i).getISBN()));

			if (books.get(i).getName().equalsIgnoreCase(bookName)) {
				return books.get(i);
			}
		}

		return null;
	}

	// *Get books by genre
	@GetMapping("/books/genre/{bookGenre}")
	public List<Book> getBookByGenre(@PathVariable String bookGenre) {

		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);

		List<Book> booksByGenre = new ArrayList<>();

		for (int i = 0; i < books.size(); i++) {

			if (books.get(i).getGenre().equalsIgnoreCase(bookGenre)) {

				booksByGenre.add(books.get(i));

			}

		}

		for (int i = 0; i < booksByGenre.size(); i++) {

			booksByGenre.get(i).setAvgRating(bookRepository.avg(booksByGenre.get(i).getISBN()));

		}

		return booksByGenre;
	}

	// *Search by rating
	@GetMapping("/books/rating/{rating}")
	public List<Book> getBookByRating(@PathVariable Double rating) {

		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);

		List<Book> booksByRating = new ArrayList<>();

		for (int i = 0; i < books.size(); i++) {

			books.get(i).setAvgRating(bookRepository.avg(books.get(i).getISBN()));

			if (books.get(i).getAvgRating() >= rating) {

				booksByRating.add(books.get(i));

			}

		}

		return booksByRating;
	}

	// get books by author id
	@GetMapping("/books/authors/{authorID}")
	public List<Book> getByAuthorId(@PathVariable Long authorID) {
		List<Book> books = new ArrayList<>();
		bookRepository.findByAuthorId(authorID).forEach(books::add);
		return books;
	}

	// post book
	@PostMapping("/books")
	public Book createBook(@RequestBody Book book) {
		return this.bookRepository.save(book);
	}

	// update book
	@PutMapping("books/{isbn}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "isbn") Long bookISBN,
			@Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookISBN)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this ID ::" + bookISBN));

		book.setISBN(bookDetails.getISBN());
		book.setGenre(bookDetails.getGenre());
		book.setName((bookDetails.getName()));
		book.setPrice(bookDetails.getPrice());
		book.setDescription(bookDetails.getDescription());
		book.setRatings(bookDetails.getRatings());
		book.setCopies_sold(bookDetails.getCopies_sold());
		book.setYear_published(bookDetails.getYear_published());

		return ResponseEntity.ok(this.bookRepository.save(book));
	}

	// delete book
	@DeleteMapping("books/{isbn}")
	public Map<String, Boolean> deleteBook(@PathVariable(value = "isbn") Long bookISBN)
			throws ResourceNotFoundException {
		Book book = bookRepository.findById(bookISBN)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this ID ::" + bookISBN));

		this.bookRepository.delete(book);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;

	}

	// *Pagination
	@GetMapping("books/page/{pageNo}")
	public List<Book> findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {

		int pageSize = 5;

		Page<Book> page = bookService.findPaginated(pageNo, pageSize);
		List<Book> listBooks = page.getContent();

		for (int i = 0; i < listBooks.size(); i++) {

			listBooks.get(i).setAvgRating(bookRepository.avg(listBooks.get(i).getISBN()));

		}

		return listBooks;

	}

}
