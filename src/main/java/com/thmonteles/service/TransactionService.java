package com.thmonteles.service;

import com.thmonteles.controller.dto.TransactionDto;
import com.thmonteles.domain.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction create(TransactionDto transactionDto);
    List<Transaction> findAll();

}
