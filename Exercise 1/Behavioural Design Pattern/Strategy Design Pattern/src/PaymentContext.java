package src;

// Context – Uses a Strategy object to perform an operation; can switch strategy dynamically.

public class PaymentContext {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void payBill(double amount) {
        strategy.pay(amount);
    }
}
