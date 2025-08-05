package org.spring.depositservice.repository;

import org.spring.depositservice.entity.Deposit;
import org.springframework.data.repository.CrudRepository;

public interface DepositRepository extends CrudRepository<Deposit, Long> {
}
