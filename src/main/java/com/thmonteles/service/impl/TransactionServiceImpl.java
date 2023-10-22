package com.thmonteles.service.impl;

import com.thmonteles.controller.dto.TransactionDto;
import com.thmonteles.domain.model.Transaction;
import com.thmonteles.domain.model.User;
import com.thmonteles.domain.repository.TransactionRepository;
import com.thmonteles.domain.repository.UserRepository;
import com.thmonteles.service.TransactionService;
import com.thmonteles.service.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }


    @Transactional
    public Transaction create(TransactionDto transactionDto) {
        User sender = userRepository.findByEmail(transactionDto.getSenderEmail()).orElseThrow(() -> new BusinessException("Sender does not exists"));
        User receiver = userRepository.findByEmail(transactionDto.getReceiverEmail()).orElseThrow(() -> new BusinessException("Receiver does not exists")) ;

        BigDecimal amount = transactionDto.getAmount();

        if (transactionRepository.calculateBalance(sender).compareTo(amount) < 0) {
            throw new BusinessException("insufficient balance from sender");
        }

        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(amount);

        transactionRepository.save(transaction);
        return transaction;
    }

}
