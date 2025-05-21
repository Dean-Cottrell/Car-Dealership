package com.pluralsight;

public class LeaseContract extends Contract {
    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
        super(date, customerName, customerEmail, vehicle);
        double price = vehicle.getPrice();
        this.expectedEndingValue = price * 0.5;
        this.leaseFee = price * 0.07;
    }

    @Override
    public double getTotalPrice() {
        return (getVehicle().getPrice() - expectedEndingValue) + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        double total = getTotalPrice();
        return calculateMonthlyPayment(total, 0.04, 36);
    }

    private double calculateMonthlyPayment(double principal, double annualRate, int months) {
        double totalAmount = principal + (principal * annualRate * (months / 12.0));
        return totalAmount / months;
    }
}
