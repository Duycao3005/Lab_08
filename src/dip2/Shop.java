package dip2;

// Abstraction
interface PaymentMethod {
    void pay(double amount);
}

// Concrete Low-level modules
class PaypalPayment implements PaymentMethod {
    public void pay(double amount) { System.out.println("Paying " + amount + " via PayPal"); }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) { System.out.println("Paying " + amount + " via Credit Card"); }
}

class CashPayment implements PaymentMethod {
    public void pay(double amount) { System.out.println("Paying " + amount + " via Cash"); }
}

// High-level module depends on abstraction
class CheckoutService {
    private final PaymentMethod paymentMethod;

    public CheckoutService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void checkout(double amount) {
        paymentMethod.pay(amount);
    }
}

public class Shop {
    public static void main(String[] args) {
        CheckoutService s1 = new CheckoutService(new PaypalPayment());
        s1.checkout(100.0);

        CheckoutService s2 = new CheckoutService(new CreditCardPayment());
        s2.checkout(250.0);
    }
}