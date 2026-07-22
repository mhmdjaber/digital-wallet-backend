package com.mhmd.wallet.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.mhmd.wallet.enums.TransactionStatus;
import com.mhmd.wallet.enums.TransactionType;

public class Transaction {

    private Long id;
    private Wallet wallet;
    private TransactionType type;
    private BigDecimal amount;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public Transaction(Long id, Wallet wallet, TransactionType type, BigDecimal amount,
                       TransactionStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.wallet = wallet;
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public TransactionType getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
