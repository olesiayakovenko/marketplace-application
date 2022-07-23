/**
 * Represents the exception for not having enough money to make a purchase.
 */
public class NotEnoughMoneyException extends Exception {
    /**
     * Creates an exception.
     */
    public NotEnoughMoneyException() {
        super("The user does not have enough money to make this purchase.");
    }
}