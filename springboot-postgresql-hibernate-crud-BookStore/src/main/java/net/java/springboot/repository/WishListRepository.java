package net.java.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.java.springboot.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {
	List<WishList> findAllByUserId(Long userId);
}
