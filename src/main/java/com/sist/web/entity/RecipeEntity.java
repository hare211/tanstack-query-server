package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "recipe")
@Getter @Setter
public class RecipeEntity {
	@Id
	private int no;
	private String title;
	private String poster;
	private String chef;
	private String link;
	private int hit;
	private int likecount;
	private int replycount;
}
