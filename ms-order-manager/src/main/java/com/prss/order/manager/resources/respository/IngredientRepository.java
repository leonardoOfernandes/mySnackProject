package com.prss.order.manager.resources.respository;


import com.prss.order.manager.domain.dao.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredients, Integer> {
}
