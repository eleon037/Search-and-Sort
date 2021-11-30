package net.java.springboot.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "ratings")
public class Rating {

	@Id
	@Column(name = "rid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long RID;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "isbn")
	private Book book;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private User user;

	@Column(name = "rating")
	@Range(min = 1, max = 5)
	private int rating;

	@Column(name = "comment")
	private String comment;

	@Column(name = "date")
	private LocalDate date;

	public Rating() {
		super();
	}

	public Rating(User user, int rating, String comment, Book book, LocalDate date) {
		super();
		this.user = user;
		this.rating = rating;
		this.comment = comment;
		this.book = book;
		this.date = date;
	}

	public void setRID(long RID) {
		this.RID = RID;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public long getRID(long RID) {
		return RID;
	}

	public long getUser() {
		return user.getId();
	}

	public long getISBN() {
		return book.getISBN();
	}

	public int getRating() {
		return rating;
	}

	public String getComment() {
		return comment;
	}

	public LocalDate getDate() {
		return LocalDate.now();
	}

}
