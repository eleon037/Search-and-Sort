
package net.java.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.java.springboot.dto.BookDto;
import net.java.springboot.model.Book;
import net.java.springboot.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	// *For pagination
	public Page<Book> findPaginated(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		return this.bookRepository.findAll(pageable);

	}

	public List<BookDto> listBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = new ArrayList<>();
		for (Book book : books) {
			BookDto bookDto = getDtoFromBook(book);
			bookDtos.add(bookDto);
		}
		return bookDtos;
	}

	public static BookDto getDtoFromBook(Book book) {
		BookDto bookDto = new BookDto(book);
		return bookDto;
	}

}
