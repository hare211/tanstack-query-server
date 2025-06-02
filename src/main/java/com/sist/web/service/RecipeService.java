package com.sist.web.service;

import java.util.List;

import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeProjection;

public interface RecipeService {
	List<RecipeProjection> getRecipePagedList(int start, int end);
	//RecipeEntity getReicpeDetailByNo(int no);
	int getRecipeTotalPage();
	RecipeDetailEntity getReicpeDetailByNo(int no);
}
