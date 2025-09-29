import src.*;

public class StrategyPattern {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
        context.payBill(500.0);

        context.setPaymentStrategy(new PayPalPayment("user@gmail.com"));
        context.payBill(750.0);
    }
}
