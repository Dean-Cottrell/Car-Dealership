package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
    }

    public void display() {
        init();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            displayMenu();

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            processUserChoice(choice);
        } while (choice != 00);
        scanner.close();
    }

    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
        if (dealership != null) {
            System.out.println("Dealership loaded successfully: " + dealership.getName());
        } else {
            System.out.println("Failed to load dealership data.");
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Car Dealership Menu ---");
        System.out.println("1  - Find vehicles within a price range");
        System.out.println("2  - Find vehicles by make and model");
        System.out.println("3  - Find vehicles by year range");
        System.out.println("4  - Find vehicles by color");
        System.out.println("5  - Find vehicles by mileage range");
        System.out.println("6  - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7  - List ALL vehicles");
        System.out.println("8  - Add a vehicle");
        System.out.println("9  - Remove a vehicle");
        System.out.println("00 - Quit");
        System.out.print("Enter your choice: ");
    }

    private void processUserChoice(int choice) {
        switch (choice) {
            case 1:
                processGetByPriceRequest();
                break;
            case 2:
                processGetByMakeModelRequest();
                break;
            case 3:
                processGetByYearRequest();
                break;
            case 4:
                processGetByColorRequest();
                break;
            case 5:
                processGetByMileageRequest();
                break;
            case 6:
                processGetByVehicleTypeRequest();
                break;
            case 7:
                processGetAllVehiclesRequest();
                break;
            case 8:
                processAddVehicleRequest();
                break;
            case 9:
                processRemoveVehicleRequest();
                break;
            case 00:
                System.out.println("Goodbye!");
                break;
            default:
                System.out.println("Invalid option, please try again.");
        }
    }

    private void processGetByPriceRequest() {
        System.out.println("Search by price range selected.");
    }

    private void processGetByMakeModelRequest() {
        System.out.println("Search by make/model selected.");
    }

    private void processGetByYearRequest() {
        System.out.println("Search by year range selected.");
    }

    private void processGetByColorRequest() {
        System.out.println("Search by color selected.");
    }

    private void processGetByMileageRequest() {
        System.out.println("Search by mileage range selected.");
    }

    private void processGetByVehicleTypeRequest() {
        System.out.println("Search by vehicle type selected.");
    }

    private void processGetAllVehiclesRequest() {List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest() {
        System.out.println("Add a vehicle selected.");
    }

    private void processRemoveVehicleRequest() {
        System.out.println("Remove a vehicle selected.");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }

        System.out.printf("%-10s %-6s %-15s %-15s %-10s %-10s %-10s %n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Price");
        System.out.println("--------------------------------------------------------------------------");
        for (Vehicle v : vehicles) {
            System.out.printf("%-10d %-6d %-15s %-15s %-10s %-10s $%-10.2f %n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getPrice());
        }
    }
}