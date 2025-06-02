package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RecipeDetailEntity;

@Repository
public interface RecipeDetailRepository extends JpaRepository<RecipeDetailEntity, Integer>{
	RecipeDetailEntity findByNo(int no);
}
