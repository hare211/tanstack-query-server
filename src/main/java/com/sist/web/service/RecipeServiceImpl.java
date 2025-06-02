package com.sist.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;

import com.sist.web.dao.RecipeDetailRepository;
import com.sist.web.dao.RecipeRepository;
import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeProjection;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
	
	private final RecipeRepository rDao;
	private final RecipeDetailRepository dDao;
	
	@Override
	public List<RecipeProjection> getRecipePagedList(int start, int end) {
		
		return rDao.recipeListData(start, end);
	}
	
	@Override
	public RecipeDetailEntity getReicpeDetailByNo(int no) {
	
		return dDao.findByNo(no);
	}
	
	@Override
	public int getRecipeTotalPage() {
		int totalPage = rDao.recipeTotalPage();
		return totalPage;
	}
}
