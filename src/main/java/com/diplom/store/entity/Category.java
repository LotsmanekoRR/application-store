package com.diplom.store.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "category")
@Data
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String categoryName;

	@JsonCreator
	public Category(long id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}
}
