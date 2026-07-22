package com.mhmd.wallet.services;

import java.math.BigDecimal;

import com.mhmd.wallet.enums.TransactionType;
import com.mhmd.wallet.enums.WalletStatus;
import com.mhmd.wallet.exceptions.InsufficientBalanceException;
import com.mhmd.wallet.exceptions.InvalidAmountException;
import com.mhmd.wallet.exceptions.WalletNotActiveException;
import com.mhmd.wallet.model.Wallet;

public class WalletService {

    private final TransactionService transactionService;

    public WalletService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void deposit(Wallet wallet, BigDecimal amount) {
        validateWalletIsActive(wallet);
        validateAmount(amount);

        wallet.setBalance(wallet.getBalance().add(amount));

        transactionService.saveTransaction(wallet, TransactionType.DEPOSIT, amount);
    }

    public void withdraw(Wallet wallet, BigDecimal amount) {
        validateWalletIsActive(wallet);
        validateAmount(amount);

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        transactionService.saveTransaction(wallet, TransactionType.WITHDRAW, amount);
    }

    public void transfer(Wallet fromWallet, Wallet toWallet, BigDecimal amount) {
        validateWalletIsActive(fromWallet);
        validateWalletIsActive(toWallet);
        validateAmount(amount);

        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));

        transactionService.saveTransaction(fromWallet, TransactionType.TRANSFER_OUT, amount);
        transactionService.saveTransaction(toWallet, TransactionType.TRANSFER_IN, amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null) {
            throw new InvalidAmountException("Amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
    }

    private void validateWalletIsActive(Wallet wallet) {
        if (wallet == null) {
            throw new WalletNotActiveException("Wallet cannot be null");
        }

        if (wallet.getStatus() != WalletStatus.ACTIVE) {
            throw new WalletNotActiveException("Wallet is not active");
        }
    }
}
