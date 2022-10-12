package com.prss.login.resources.respository;

import com.prss.registers.domain.dao.OrderDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDeliveryRepository extends JpaRepository<OrderDelivery, Integer> {
}
