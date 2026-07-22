package com.mhmd.wallet.exceptions;

public class WalletNotActiveException extends RuntimeException {

    public WalletNotActiveException(String message) {
        super(message);
    }
}
