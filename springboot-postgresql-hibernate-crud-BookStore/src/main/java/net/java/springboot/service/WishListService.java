package net.java.springboot.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import net.java.springboot.model.WishList;
import net.java.springboot.repository.WishListRepository;

@Service
@Transactional
public class WishListService {
	private final WishListRepository wishListRepository;

	public WishListService(WishListRepository wishListRepository) {
		this.wishListRepository = wishListRepository;
	}

	public void createWishList(WishList wishList) {
		wishListRepository.save(wishList);
	}

	public List<WishList> readWishList(long userId) {
		return wishListRepository.findAllByUserId(userId);
	}
}
