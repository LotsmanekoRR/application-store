package com.diplom.store.repos;

import com.diplom.store.entity.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	public List<Item> findAllItemsByCategoryId(Long id);
}
