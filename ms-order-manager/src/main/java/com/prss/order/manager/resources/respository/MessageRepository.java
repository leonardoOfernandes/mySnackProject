package com.prss.order.manager.resources.respository;


import com.prss.order.manager.domain.dao.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Messages, Integer> {

    Optional<Messages> findBy(Integer integer);
}
