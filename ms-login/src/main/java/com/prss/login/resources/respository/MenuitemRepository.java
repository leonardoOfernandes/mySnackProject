package com.prss.login.resources.respository;

import com.prss.registers.domain.dao.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuitemRepository extends JpaRepository<MenuItems, Integer> {
}
