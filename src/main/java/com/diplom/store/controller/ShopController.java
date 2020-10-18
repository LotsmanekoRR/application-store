package com.diplom.store.controller;

import com.diplom.store.entity.Category;
import com.diplom.store.entity.Item;
import com.diplom.store.repos.CategoriesRepository;
import com.diplom.store.repos.ItemRepository;
import com.diplom.store.service.ItemService;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class ShopController {

	private final ItemService itemService;

	private final CategoriesRepository categoriesRepository;

	private final ItemRepository itemRepository;

	@GetMapping(value = "/")
	public ModelAndView display() {
		List<Category> categories = categoriesRepository.findAll();
//		for (int i = 0; i < 5; i++) {
//			categories.add(new Category(i, "category" + i));
//		}
		ModelAndView index = new ModelAndView("home");
		index.addObject("categories", categories);
		List<Item> allItems = itemService.findAllItems();
//		for (int i = 0; i < 10; i++) {
//			allItems.add(new Item(i, i + i, "Name" + i, "Description" + i, new Category(1L, "Item")));
//		}
		index.addObject("allItems", allItems);
		return index;
	}

	@GetMapping(value = "/admin")
	public ModelAndView adminDisplay(Model model) {
		ModelAndView admin = new ModelAndView("admin");
		Item item = new Item();
		admin.addObject("item", item);
		admin.addObject("newCategory", new Category());
		List<Category> all = categoriesRepository.findAll();
		admin.addObject("categories", all);
		return admin;
	}

	@PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ModelAndView addItem(@ModelAttribute  Item item) {
		try {
			Item item1 = itemService.saveItem(item);
		} catch (IOException e) {
			return null;
		}
		return new ModelAndView("redirect:/");
	}

	@GetMapping("/item")
	public ResponseEntity<Item> getItem(@RequestParam Long id) {
		Item byId = itemService.findById(id);
		return new ResponseEntity<Item>(byId, HttpStatus.OK);
	}

	@GetMapping("/category/{id}")
	public ModelAndView getItemsByCategory(@PathVariable Long id) {
		List<Item> allItemsByCategoryId = itemRepository.findAllItemsByCategoryId(id);
		ModelAndView home = new ModelAndView("home");
		home.addObject("allItems", allItemsByCategoryId);
		return home;
	}

	@PostMapping("/category")
	public ModelAndView addCategory(@ModelAttribute(value = "category") Category category) {
		categoriesRepository.save(category);
		return new ModelAndView("redirect:/admin");
	}

}
