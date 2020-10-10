package com.diplom.store.controller;

import com.diplom.store.entity.Item;
import com.diplom.store.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {

	private final ItemService itemService;

	@PostMapping(value = "/add")
	public ResponseEntity<Item> addItem(@RequestBody Item item) {
		Item item1 = itemService.saveItem(item);
		return new ResponseEntity<Item>(item1, HttpStatus.OK);
	}

	@GetMapping("/item")
	public ResponseEntity<Item> getItem(@RequestParam Long id) {
		Item byId = itemService.findById(id);
		return new ResponseEntity<Item>(byId, HttpStatus.OK);
	}

//	@GetMapping
//	public Model getIndex(){
//
//	}

}
