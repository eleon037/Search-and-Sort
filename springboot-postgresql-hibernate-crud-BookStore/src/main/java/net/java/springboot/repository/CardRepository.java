package net.java.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.java.springboot.model.CreditCard;

@Repository
public interface CardRepository extends JpaRepository<CreditCard, Long> {
	public List<CreditCard> findByUserId(long id);
}
