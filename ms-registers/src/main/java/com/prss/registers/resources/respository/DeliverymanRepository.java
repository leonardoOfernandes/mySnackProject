package com.prss.registers.resources.respository;

import com.prss.registers.domain.dao.Deliveryman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliverymanRepository extends JpaRepository<Deliveryman, Integer> {
}
