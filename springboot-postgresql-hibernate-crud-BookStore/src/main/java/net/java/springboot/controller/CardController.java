package net.java.springboot.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.java.springboot.exception.ResourceNotFoundException;
import net.java.springboot.model.CreditCard;
import net.java.springboot.repository.CardRepository;


@RestController
@RequestMapping("/api/")
public class CardController {

	@Autowired
	private CardRepository cardRepository;

	// get cards
	@GetMapping("/cards")
	public List<CreditCard> getAllCards() {
		return this.cardRepository.findAll();
	}

	@GetMapping("/cards/users/{id}")
	public List<CreditCard> getByUserId(@PathVariable Long id) {
		List<CreditCard> cards = new ArrayList<>();
		cardRepository.findByUserId(id).forEach(cards::add);
		return cards;
	}
	/*
	 * //get card by card id
	 * 
	 * @GetMapping("/cards/{card_id}") public ResponseEntity<CreditCard>
	 * getByCardId(@PathVariable(value = "card_id") Long user) throws
	 * ResourceNotFoundException { CreditCard creditCard =
	 * cardRepository.findById(user) .orElseThrow(() -> new
	 * ResourceNotFoundException("Credit Card not found for this id:" + user));
	 * 
	 * return ResponseEntity.ok().body(creditCard); } //get card by cardnumber
	 * 
	 * @GetMapping("/cards/{cardId}") public ResponseEntity<CreditCard>
	 * getCardByCardById(@PathVariable(value = "card_id") Long cardId) throws
	 * ResourceNotFoundException { CreditCard creditCard =
	 * cardRepository.findById(cardId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Credit Card not found for this id ::" + cardId));
	 * 
	 * return ResponseEntity.ok().body(creditCard); }
	 */

	// post credit card
	@PostMapping("/cards")
	public CreditCard createCard(@RequestBody CreditCard creditCard) {
		return this.cardRepository.save(creditCard);
	}

	// update visitor
	@PutMapping("/cards/{card_number}") // you can change card number
	public ResponseEntity<CreditCard> updateCreditCard(@PathVariable(value = "card_id") Long cardId,
			@Valid @RequestBody CreditCard cardDetails) throws ResourceNotFoundException {

		CreditCard creditCard = cardRepository.findById(cardId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id:" + cardId));

		creditCard.setCardNumber(cardDetails.getCardNumber());

		return ResponseEntity.ok(this.cardRepository.save(creditCard));

	}

	// delete visitor

	@DeleteMapping("cards/{card_id}") // this delete by card id
	public Map<String, Boolean> deleteCreditCard(@PathVariable(value = "card_id") Long cardId)
			throws ResourceNotFoundException {

		CreditCard creditCard = cardRepository.findById(cardId)
				.orElseThrow(() -> new ResourceNotFoundException("Card not found for this id ::" + cardId));

		this.cardRepository.delete(creditCard);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}
}
