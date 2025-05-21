package com.pluralsight;

public class SalesContract extends Contract {
    private double salesTaxAmount;
    private final double RECORDING_FEE = 100.0;
    private double processingFee;
    private boolean financed;

    public SalesContract(String date, String customerName, String customerEmail,
                         Vehicle vehicle, boolean financed) {
        super(date, customerName, customerEmail, vehicle);
        double price = vehicle.getPrice();
        this.salesTaxAmount = price * 0.05;
        this.processingFee = (price < 10000) ? 295.0 : 495.0;
        this.financed = financed;
    }

    @Override
    public double getTotalPrice() {
        return getVehicle().getPrice() + salesTaxAmount + RECORDING_FEE + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!financed) {
            return 0.0;
        }
        double totalPrice = getTotalPrice();
        if (getVehicle().getPrice() >= 10000) {
            return calculateMonthlyPayment(totalPrice, 0.0425, 48);
        } else {
            return calculateMonthlyPayment(totalPrice, 0.0525, 24);
        }
    }

    private double calculateMonthlyPayment(double principal, double annualRate, int months) {
        double totalAmount = principal + (principal * annualRate * (months / 12.0));
        return totalAmount / months;
    }
}
