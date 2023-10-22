package com.thmonteles.domain.repository;

import com.thmonteles.domain.model.Transaction;
import com.thmonteles.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BalanceRepository extends JpaRepository<Transaction, Long> {

    BigDecimal GetBalanceByUser(User user);
}
