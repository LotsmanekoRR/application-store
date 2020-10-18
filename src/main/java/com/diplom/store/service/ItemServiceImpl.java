package com.diplom.store.service;

import com.diplom.store.config.PropertiesConfig;
import com.diplom.store.entity.Item;
import com.diplom.store.repos.ItemRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

	private final ItemRepository itemRepository;

	private final PropertiesConfig config;

	private final ServletContext context;

	public Item saveItem(Item item) throws IOException {
		String realPath = context.getRealPath("/img/");
		MultipartFile file = item.getFile();
		if (Objects.nonNull(file) && !file.getOriginalFilename().isEmpty()) {
			String generateFileName = generateFileName(".png");
			String filePath = realPath + generateFileName;
			Path path = Paths.get(filePath);
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			item.setFilePath("/img/" + generateFileName);
		}
		Item save = itemRepository.save(item);
		return save;
	}

	private String generateFileName(String fileFormat) {
		return UUID.randomUUID().toString() + fileFormat;
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
