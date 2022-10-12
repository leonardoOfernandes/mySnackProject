package com.prss.registers.resources.respository;

import com.prss.registers.domain.dao.Ingredients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredients, Integer> {
}
