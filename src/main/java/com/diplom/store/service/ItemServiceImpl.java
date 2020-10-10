package com.diplom.store.service;

import com.diplom.store.entity.Item;
import com.diplom.store.repos.ItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	public Item saveItem(Item item) {
		Item save = itemRepository.save(item);
		return save;
	}

	public void deleteItem(Long id) {
		itemRepository.deleteById(id);
	}

	public Item findById(Long id) {
		Optional<Item> byId = itemRepository.findById(id);
		return itemRepository.findById(id).orElse(null);
	}

	public List<Item> findAllItems() {
		return itemRepository.findAll();
	}

}
