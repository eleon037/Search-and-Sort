package net.java.springboot.dto;

import net.java.springboot.model.Book;

public class BookDto {
	private long ISBN;
	private String name;
	private String genre;
	private String publisher;
	private double price;
	private double rating;
	private String description;
	private int year_published;
	private int copies_sold;
	private Long authorId;

	public BookDto(Book book) {
		this.setISBN(book.getISBN());
		this.setAuthorId(book.getAuthor());
		this.setCopies_sold(book.getCopies_sold());
		this.setDescription(book.getDescription());
		this.setGenre(book.getGenre());
		this.setAuthorId(book.getAuthor());
		this.setPrice(book.getPrice());
		this.setPublisher(book.getPublisher());
		this.setYear_published(book.getYear_published());
	}

	public BookDto(String name, String genre, String publisher, double price, double rating, String description,
			int year_published, int copies_sold, Long authorId) {
		this.name = name;
		this.genre = genre;
		this.publisher = publisher;
		this.price = price;
		this.rating = rating;
		this.description = description;
		this.year_published = year_published;
		this.copies_sold = copies_sold;
		this.authorId = authorId;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear_published() {
		return year_published;
	}

	public void setYear_published(int year_published) {
		this.year_published = year_published;
	}

	public int getCopies_sold() {
		return copies_sold;
	}

	public void setCopies_sold(int copies_sold) {
		this.copies_sold = copies_sold;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}
}
