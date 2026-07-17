package exceptions;

public class WalletNotActiveException extends RuntimeException {

    public WalletNotActiveException(String message) {
        super(message);
    }
}