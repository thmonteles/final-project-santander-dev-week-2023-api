package com.thmonteles.controller.dto;

import com.thmonteles.domain.model.Transaction;

import java.math.BigDecimal;

public record TransactionDto(
        Long id,
        String senderEmail,
        String receiverEmail,
        BigDecimal amount
) {

    public TransactionDto(String senderEmail, String receiverEmail, BigDecimal amount) {
        this(null, senderEmail, receiverEmail, amount);
    }

    public TransactionDto(Transaction transaction) {
        this(transaction.getId(), transaction.getSender().getEmail(), transaction.getReceiver().getEmail(), transaction.getAmount());
    }
    public String getSenderEmail() {
        return senderEmail;
    }

    public String getReceiverEmail() {
        return senderEmail;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}

