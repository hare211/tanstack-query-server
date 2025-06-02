package com.sist.web.service;

import java.util.List;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeProjection;

public interface RecipeService {
	public List<RecipeProjection> getRecipePagedList(int start, int end);
	public RecipeEntity getReicpeDetailByNo(int no);
	public int getRecipeTotalPage();
}
