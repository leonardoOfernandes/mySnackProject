package com.prss.login.resources.respository;

import com.prss.registers.domain.dao.Additional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdditionalRepository extends JpaRepository<Additional, Integer> {
}
