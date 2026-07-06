import java.math.BigDecimal;

public class WalletService {

    public void deposit(Wallet wallet, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        wallet.setBalance(wallet.getBalance().add(amount));
    }

    public void withdraw(Wallet wallet, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        if (wallet.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(amount));
    }

    public void transfer(Wallet fromWallet, Wallet toWallet, BigDecimal amount) {
        withdraw(fromWallet, amount);
        deposit(toWallet, amount);
    }
}