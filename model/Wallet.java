package model;
import java.math.BigDecimal;

import enums.Currency;
import enums.WalletStatus;

public class Wallet {

    private Long id;
    private User user;
    private BigDecimal balance;
    private Currency currency;
    private WalletStatus status;

    public Wallet(Long id, User user, BigDecimal balance, Currency currency) {
        this.id = id;
        this.user = user;
        this.balance = balance;
        this.currency = currency;
        this.status = WalletStatus.ACTIVE;//default status is Active
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
      public WalletStatus getStatus() {
        return status;
    }

}