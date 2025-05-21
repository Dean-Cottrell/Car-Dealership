package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {
    private final String fileName = "contracts.csv";

    public void saveContract(Contract contract) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            String line = formatContract(contract);
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving contract: " + e.getMessage());
        }
    }

    private String formatContract(Contract contract) {
        StringBuilder sb = new StringBuilder();
        if (contract instanceof SalesContract) {
            SalesContract sc = (SalesContract) contract;
            sb.append("SALE|")
                    .append(sc.getDate()).append("|")
                    .append(sc.getCustomerName()).append("|")
                    .append(sc.getCustomerEmail()).append("|")
                    .append(sc.getVehicle().getVin()).append("|")
                    .append(sc.getVehicle().getYear()).append("|")
                    .append(sc.getVehicle().getMake()).append("|")
                    .append(sc.getVehicle().getModel()).append("|")
                    .append(sc.getVehicle().getVehicleType()).append("|")
                    .append(sc.getVehicle().getColor()).append("|")
                    .append(sc.getVehicle().getOdometer()).append("|")
                    .append(sc.getVehicle().getPrice()).append("|")
                    .append(sc.getTotalPrice()).append("|")
                    .append(sc.getMonthlyPayment()).append("|")
                    .append(sc.getMonthlyPayment() == 0.0 ? "NO" : "YES");
        } else if (contract instanceof LeaseContract) {
            LeaseContract lc = (LeaseContract) contract;
            sb.append("LEASE|")
                    .append(lc.getDate()).append("|")
                    .append(lc.getCustomerName()).append("|")
                    .append(lc.getCustomerEmail()).append("|")
                    .append(lc.getVehicle().getVin()).append("|")
                    .append(lc.getVehicle().getYear()).append("|")
                    .append(lc.getVehicle().getMake()).append("|")
                    .append(lc.getVehicle().getModel()).append("|")
                    .append(lc.getVehicle().getVehicleType()).append("|")
                    .append(lc.getVehicle().getColor()).append("|")
                    .append(lc.getVehicle().getOdometer()).append("|")
                    .append(lc.getVehicle().getPrice()).append("|")
                    .append(lc.getTotalPrice()).append("|")
                    .append(lc.getMonthlyPayment());
        }
        return sb.toString();
    }
}
