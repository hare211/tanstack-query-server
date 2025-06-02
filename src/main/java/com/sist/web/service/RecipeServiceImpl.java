package com.sist.web.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.sist.web.dao.RecipeRepository;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository rDao;
	
	@Override
	public List<RecipeProjection> getRecipePagedList(int start, int end) {
		
		return rDao.recipeListData(start, end);
	}
	
	@Override
	public RecipeEntity getReicpeDetailByNo(int no) {
		
		return rDao.findByNo(no);
	}
	
	@Override
	public int getRecipeTotalPage() {
		int totalPage = rDao.recipeTotalPage();
		return totalPage;
	}
}
