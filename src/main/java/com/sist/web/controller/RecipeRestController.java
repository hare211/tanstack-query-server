package com.sist.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.RecipeDetailEntity;
import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeProjection;
import com.sist.web.service.RecipeService;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecipeRestController {
	
	private final RecipeService rService;
	
	@GetMapping("/recipes/list/{page}")
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
	
	@GetMapping("/recipes/detail/{no}")
	public ResponseEntity<RecipeDetailEntity> getRecipeDetail(@PathVariable(name = "no") Integer no) {
		if (no == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		Map<String, Object> map = new HashMap<>();
		RecipeDetailEntity vo = new RecipeDetailEntity();
		try {
			vo = rService.getReicpeDetailByNo(no);
			String [] data = vo.getFoodmake().split("\n");
			List<String> mList = new ArrayList<>();
			List<String> iList = new ArrayList<>();
			
			for (String d : data) {
				StringTokenizer st = new StringTokenizer(d, "^");
				mList.add(st.nextToken());
				iList.add(st.nextToken());
			}
			
			String s = vo.getData();
			s = s.replaceAll("[구매]", "");
			vo.setData(s);
			String [] arr = s.split(",");
			List<String> dList = Arrays.asList(arr);
			
			map.put("vo", vo);
			map.put("dList", dList);
			map.put("mList", mList);
			map.put("iList", iList);
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok(vo);
	}
}
