package net.java.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.springboot.model.WishList;
import net.java.springboot.repository.WishListRepository;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	private WishListRepository wishListRepository;

	@GetMapping("/list")
	public List<WishList> getAllWishLists() {
		return this.wishListRepository.findAll();
	}

	@PostMapping("/add")
	public WishList createWishList(@Valid @RequestBody WishList wishList) {
		return this.wishListRepository.save(wishList);
	}

}
