package com.prss.order.manager.resources.respository;


import com.prss.order.manager.domain.dao.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuitemRepository extends JpaRepository<MenuItems, Integer> {
}
