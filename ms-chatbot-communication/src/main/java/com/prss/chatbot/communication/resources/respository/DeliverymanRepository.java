package com.prss.chatbot.communication.resources.respository;


import com.prss.order.manager.domain.dao.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends JpaRepository<Deliveryman, Integer> {
}
