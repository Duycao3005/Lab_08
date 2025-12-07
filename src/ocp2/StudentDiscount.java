package ocp2;

import java.util.HashMap;
import java.util.Map;

// Abstraction
interface DiscountPolicy {
    double calculate(double totalPrice);
}

// Concrete Implementations
class StudentDiscount implements DiscountPolicy {
    public double calculate(double totalPrice) { return totalPrice * 0.15; }
}

class TeacherDiscount implements DiscountPolicy {
    public double calculate(double totalPrice) { return totalPrice * 0.20; }
}

class VipDiscount implements DiscountPolicy {
    public double calculate(double totalPrice) { return totalPrice * 0.25; }
}

// New Requirement added without changing Service
class AlumniDiscount implements DiscountPolicy {
    public double calculate(double totalPrice) { return totalPrice * 0.10; } // e.g. 10%
}

class NoDiscount implements DiscountPolicy {
    public double calculate(double totalPrice) { return 0; }
}

// Refactored Service
class DiscountService {
    private Map<String, DiscountPolicy> policies = new HashMap<>();

    public DiscountService() {
        policies.put("STUDENT", new StudentDiscount());
        policies.put("TEACHER", new TeacherDiscount());
        policies.put("VIP", new VipDiscount());
        policies.put("ALUMNI", new AlumniDiscount());
    }

    public double calculateDiscount(String userType, double totalPrice) {
        DiscountPolicy policy = policies.getOrDefault(userType, new NoDiscount());
        return policy.calculate(totalPrice);
    }

    public static void main(String[] args) {
        DiscountService service = new DiscountService();
        System.out.println("Student Discount: " + service.calculateDiscount("STUDENT", 100));
        System.out.println("Alumni Discount: " + service.calculateDiscount("ALUMNI", 100));
    }
}