package net.java.springboot.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "books")
public class Book implements Comparable<Book>, Comparator<Book> { // *Comparable <Book>

	@Id
	@NotNull
	private long ISBN;

	// @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private Author author;

	@JsonIgnore
	@OneToMany(mappedBy = "book")
	private List<WishList> wishListList;

	@Column(name = "name")
	private String name;

	@Column(name = "genre")
	private String genre;

	@Column(name = "price")
	private double price;

	@Column(name = "publisher")
	private String publisher;

	public List<WishList> getWishListList() {
		return wishListList;
	}

	public void setWishListList(List<WishList> wishListList) {
		this.wishListList = wishListList;
	}

	@Column(name = "description")
	private String description;

	@Column(name = "year_published")
	private int year_published;

	@Column(name = "copies_sold")
	private int copies_sold;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Rating> ratings = new ArrayList<Rating>();

	// *New Column to hold the average rating of a book
	@Column(name = "avgRating")
	private Double avgRating;

	public Book() {
		super();
	}

	public Book(String name, String genre, double price, String publisher, int year_published, String description,
			int copies_sold, double rating) {
		super();
		this.name = name;
		this.genre = genre;
		this.price = price;
		this.publisher = publisher;
		this.description = description;
		this.year_published = year_published;
		this.copies_sold = copies_sold;
		// *Attempting to initialize the average rating to 0
		this.avgRating = 0.0;

	}

	public long getAuthor() {
		return author.getId();
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public long getISBN() {
		return ISBN;
	}

	public void setISBN(long ISBN) {
		this.ISBN = ISBN;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getGenre() {
		return genre;
	}

	public double getPrice() {
		return price;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	// *Getter and Setter for the average rating
	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}

	// *Added author first and last name
	public String getAuthorFirstName() {
		return author.getFirst_name();
	}

	public String getAuthorLastName() {
		return author.getLast_name();
	}

	// *compareTo to use for Collections
	@Override
	public int compareTo(Book book) {

		return Integer.compare(book.copies_sold, this.copies_sold);

	}

	@Override
	public int compare(Book book1, Book book2) {

		return 0;

	}

}
