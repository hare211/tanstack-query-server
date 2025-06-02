package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "recipedetail")
@Getter @Setter
public class RecipeDetailEntity {
	@Id
	private int no;
	private String poster;
	private String title;
	private String chef;
	private String chef_poster;
	private String info1;
	private String info2;
	private String info3;
	private String content;
	private String foodmake;
	private String data;
}
