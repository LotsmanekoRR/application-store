package com.diplom.store.service;

import com.diplom.store.entity.Item;
import java.util.List;

public interface ItemService {

	Item saveItem(Item item);

	void deleteItem(Long id);

	Item findById(Long id);

	List<Item> findAllItems();
}
