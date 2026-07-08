package services;
import java.math.BigDecimal;

import enums.TransactionType;
import enums.WalletStatus;
import model.Wallet;

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
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));

        transactionService.saveTransaction(wallet, TransactionType.WITHDRAW, amount);
    }

    public void transfer(Wallet fromWallet, Wallet toWallet, BigDecimal amount) {
        validateWalletIsActive(fromWallet);
        validateWalletIsActive(toWallet);
        validateAmount(amount);

        if (fromWallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        fromWallet.setBalance(fromWallet.getBalance().subtract(amount));
        toWallet.setBalance(toWallet.getBalance().add(amount));

        transactionService.saveTransaction(fromWallet, TransactionType.TRANSFER_OUT, amount);
        transactionService.saveTransaction(toWallet, TransactionType.TRANSFER_IN, amount);
    }

    private void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }
    }

    private void validateWalletIsActive(Wallet wallet) {
        if (wallet.getStatus() != WalletStatus.ACTIVE) {
            throw new RuntimeException("Wallet is not active");
        }
    }
}