package com.thmonteles.domain.repository;

import com.thmonteles.domain.model.Transaction;
import com.thmonteles.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//    @Query("SELECT COALESCE(SUM(t.amount) , 0) FROM Transaction t WHERE t.sender = :user OR t.receiver = :user ORDER BY t.createdAt")
    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.sender = :user OR t.receiver = :user")
    BigDecimal calculateBalance(@Param("user") User user);
}
