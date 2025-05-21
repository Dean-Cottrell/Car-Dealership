package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DealershipFileManager {
   private String fileName;

   public DealershipFileManager(String fileName) {
      this.fileName = fileName;
   }

   public Dealership getDealership() {
      Dealership dealership = null;
      try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
         String line = reader.readLine();
         if (line != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 3) {
               dealership = new Dealership(parts[0].trim(), parts[1].trim(), parts[2].trim());
            }
         }
         while ((line = reader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length >= 8) {
               int vin = Integer.parseInt(parts[0].trim());
               int year = Integer.parseInt(parts[1].trim());
               String make = parts[2].trim();
               String model = parts[3].trim();
               String type = parts[4].trim();
               String color = parts[5].trim();
               int odometer = Integer.parseInt(parts[6].trim());
               double price = Double.parseDouble(parts[7].trim());
               Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
               dealership.addVehicle(vehicle);
            }
         }
      } catch (IOException e) {
         System.err.println("Error reading dealership file: " + e.getMessage());
      }
      return dealership;
   }

   public void saveDealership(Dealership dealership) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
         writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
         writer.newLine();
         for (Vehicle v : dealership.getAllVehicles()) {
            writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                    v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
            writer.newLine();
         }
      } catch (IOException e) {
         System.err.println("Error saving dealership: " + e.getMessage());
      }
   }
}
