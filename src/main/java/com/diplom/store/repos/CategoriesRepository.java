package com.diplom.store.repos;

import com.diplom.store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Category,Long> {

}
