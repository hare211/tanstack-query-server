package com.sist.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeProjection;
import com.sist.web.service.RecipeService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class RecipeRestController {
	
	private final RecipeService rService;
	
	@GetMapping("/api/recipe/list/{page}")
	public ResponseEntity<Map<String, Object>> getRecipeListPagedList(@PathVariable(name = "page") Integer page) {
		Map<String, Object> map = new HashMap<>();

		try {
			int rowSize = 12;
			int start = (rowSize * page) - (rowSize - 1);
			int end = rowSize * page;
			List<RecipeProjection> list = rService.getRecipePagedList(start, end);
			int totalPage = rService.getRecipeTotalPage();
			
			final int BLOCK = 10;
			int startPage = ((page - 1) / BLOCK * BLOCK) + 1;
			int endPage = ((page - 1) / BLOCK * BLOCK) + BLOCK;
			
			if (endPage > totalPage) {
				endPage = totalPage;
			}
			
			map.put("fList", list);
			map.put("curPage", page);
			map.put("totalPage", totalPage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		
		return ResponseEntity.ok(map);
	}
}
