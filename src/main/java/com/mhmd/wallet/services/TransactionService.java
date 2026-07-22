package com.mhmd.wallet.services;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.mhmd.wallet.enums.TransactionStatus;
import com.mhmd.wallet.enums.TransactionType;
import com.mhmd.wallet.model.Transaction;
import com.mhmd.wallet.model.Wallet;

public class TransactionService {

    private final List<Transaction> transactions = new ArrayList<>();
    private Long transactionIdCounter = 1L;

    public void saveTransaction(Wallet wallet, TransactionType type, BigDecimal amount) {
        Transaction transaction = new Transaction(
                transactionIdCounter,
                wallet,
                type,
                amount,
                TransactionStatus.SUCCESS,
                LocalDateTime.now()
        );

        transactions.add(transaction);
        transactionIdCounter++;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsByWallet(Wallet wallet) {
        List<Transaction> walletTransactions = new ArrayList<>();

        for (Transaction transaction : transactions) {
            if (transaction.getWallet().getId().equals(wallet.getId())) {
                walletTransactions.add(transaction);
            }
        }

        return walletTransactions;
    }
}
