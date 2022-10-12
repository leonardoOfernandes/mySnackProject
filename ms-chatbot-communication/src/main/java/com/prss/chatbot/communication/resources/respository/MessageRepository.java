package com.prss.chatbot.communication.resources.respository;

import com.prss.registers.domain.dao.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Integer> {
}
