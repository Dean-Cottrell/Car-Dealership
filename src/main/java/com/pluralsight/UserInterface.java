package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager dealershipFileManager;
    private ContractFileManager contractFileManager;

    public UserInterface() {
        dealershipFileManager = new DealershipFileManager("inventory.csv");
        contractFileManager = new ContractFileManager();
        init();
    }

    private void init() {
        dealership = dealershipFileManager.getDealership();
        if (dealership == null) {
            System.out.println("No dealership data found; creating default dealership.");
            dealership = new Dealership("Default Dealership", "Unknown Address", "000-000-0000");
        } else {
            System.out.println("Loaded Dealership: " + dealership.getName());
        }
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            displayMenu();
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = -1;
            }
            processChoice(choice, scanner);
        } while (choice != 99);
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n--- Car Dealership Menu ---");
        System.out.println("1  - Find vehicles within a price range");
        System.out.println("2  - Find vehicles by make/model");
        System.out.println("3  - Find vehicles by year range");
        System.out.println("4  - Find vehicles by color");
        System.out.println("5  - Find vehicles by mileage range");
        System.out.println("6  - Find vehicles by type");
        System.out.println("7  - List ALL vehicles");
        System.out.println("8  - Add a vehicle");
        System.out.println("9  - Remove a vehicle");
        System.out.println("10 - Sell/Lease a Vehicle");
        System.out.println("99 - Quit");
        System.out.print("Enter your choice: ");
    }

    private void processChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                System.out.println("Search functionality not implemented yet.");
                break;
            case 7:
                listAllVehicles();
                break;
            case 8:
                addVehicle(scanner);
                break;
            case 9:
                removeVehicle(scanner);
                break;
            case 10:
                processSellLeaseRequest(scanner);
                break;
            case 99:
                System.out.println("Goodbye.");
                break;
            default:
                System.out.println("Invalid option; try again.");
        }
    }

    private void listAllVehicles() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in inventory.");
        } else {
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    private void addVehicle(Scanner scanner) {
        try {
            System.out.print("Enter VIN: ");
            int vin = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Year: ");
            int year = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Make: ");
            String make = scanner.nextLine();
            System.out.print("Enter Model: ");
            String model = scanner.nextLine();
            System.out.print("Enter Vehicle Type: ");
            String type = scanner.nextLine();
            System.out.print("Enter Color: ");
            String color = scanner.nextLine();
            System.out.print("Enter Odometer reading: ");
            int odometer = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Price: ");
            double price = Double.parseDouble(scanner.nextLine());

            Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
            dealership.addVehicle(vehicle);
            dealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle added.");
        } catch (Exception e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
    }

    private void removeVehicle(Scanner scanner) {
        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        boolean removed = dealership.removeVehicle(vin);
        if (removed) {
            dealershipFileManager.saveDealership(dealership);
            System.out.println("Vehicle removed.");
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    private void processSellLeaseRequest(Scanner scanner) {
        System.out.print("Enter VIN of the vehicle for sale/lease: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle selectedVehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                selectedVehicle = v;
                break;
            }
        }

        if (selectedVehicle == null) {
            System.out.println("Vehicle with VIN " + vin + " not found.");
            return;
        }

        System.out.print("Enter contract date (YYYYMMDD): ");
        String date = scanner.nextLine();
        System.out.print("Enter customer's name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter customer's email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Enter contract type (SALE/LEASE): ");
        String contractType = scanner.nextLine().trim();

        Contract contract = null;
        if (contractType.equalsIgnoreCase("SALE")) {
            System.out.print("Do they want to finance the purchase? (YES/NO): ");
            String financeResponse = scanner.nextLine().trim();
            boolean financed = financeResponse.equalsIgnoreCase("YES");
            contract = new SalesContract(date, customerName, customerEmail, selectedVehicle, financed);
        } else if (contractType.equalsIgnoreCase("LEASE")) {
            int currentYear = java.time.Year.now().getValue();
            if ((currentYear - selectedVehicle.getYear()) > 3) {
                System.out.println("Cannot lease a vehicle that is more than 3 years old.");
                return;
            }
            contract = new LeaseContract(date, customerName, customerEmail, selectedVehicle);
        } else {
            System.out.println("Invalid contract type entered.");
            return;
        }

        contractFileManager.saveContract(contract);
        dealership.removeVehicle(selectedVehicle.getVin());
        dealershipFileManager.saveDealership(dealership);
        System.out.println("Contract processed and vehicle removed from inventory.");
    }
}
