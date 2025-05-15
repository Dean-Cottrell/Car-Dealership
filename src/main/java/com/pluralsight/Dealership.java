package com.pluralsight;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }


    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    public void removeVehicle(int id) {
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle vehicle = inventory.get(i);
            if (vehicle.getVin() == id) {
                inventory.remove(i);
                break;
            }

        }
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }
    public List<Vehicle> getVehiclesByYear(int minYear, int maxYear) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= minYear && vehicle.getYear() <= maxYear) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }
    public List<Vehicle> getVehiclesByMileage(int minMiles, int maxMiles) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= minMiles && vehicle.getOdometer() <= maxMiles) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> filteredVehicles = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                filteredVehicles.add(vehicle);
            }
        }

        return filteredVehicles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
