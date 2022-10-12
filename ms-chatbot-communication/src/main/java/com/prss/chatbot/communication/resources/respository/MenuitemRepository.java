package com.prss.chatbot.communication.resources.respository;

import com.prss.chatbot.communication.domain.dao.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuitemRepository extends JpaRepository<MenuItems, Integer> {
}
