package com.sist.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.entity.RecipeEntity;
import com.sist.web.entity.RecipeProjection;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer>{
	@Query(value = "SELECT no, poster, chef, hit, likecount, title, num "
				 + "FROM (SELECT no, poster, chef, hit, likecount, title, rownum as num "
				 	   + "FROM (SELECT /* INDEX_ASC(recipe recipe_no_pk) */ no, poster, chef, hit, likecount, title "
				 	   		 + "FROM recipe WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail))) "
				 + "WHERE num BETWEEN :start AND :end", nativeQuery = true)
	public List<RecipeProjection> recipeListData(@Param("start") Integer start, @Param("end") Integer end);
	
	public RecipeEntity findByNo(int no);
	
	@Query(value = "SELECT CEIL(COUNT(*) / 12.0) FROM recipe "
				 + "WHERE no IN(SELECT no FROM recipe INTERSECT SELECT no FROM recipeDetail)", nativeQuery = true)
	public int recipeTotalPage();
}
