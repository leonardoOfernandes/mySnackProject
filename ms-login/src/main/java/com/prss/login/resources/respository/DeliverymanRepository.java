package com.prss.login.resources.respository;

import com.prss.login.domain.dao.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends JpaRepository<Deliveryman, Integer> {
}
