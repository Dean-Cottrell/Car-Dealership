package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {
   private String fileName = "dealership.csv";

   public Dealership getDealership() {
      Dealership dealership = null;
      List<Vehicle> inventory = new ArrayList<>();

      try (BufferedReader bufReader = new BufferedReader(new FileReader(fileName))) {
         String line = bufReader.readLine();
         if (line != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 3) {
               dealership = new Dealership(parts[0], parts[1], parts[2]);
            }
         }

         while ((line = bufReader.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 8) {
               try {
                  Vehicle vehicle = new Vehicle(
                          Integer.parseInt(parts[0]),
                          Integer.parseInt(parts[1]),
                          parts[2], parts[3], parts[4], parts[5],
                          Integer.parseInt(parts[6]),
                          Double.parseDouble(parts[7]));

                  inventory.add(vehicle);
               } catch (NumberFormatException e) {
                  System.err.println("Skipping invalid vehicle entry: " + line);
               }
            }
         }

         if (dealership != null) dealership.getAllVehicles();
      } catch (FileNotFoundException e) {
         System.err.println("Error: File not found - " + e.getMessage());
      } catch (IOException e) {
         System.err.println("Error processing dealership file - " + e.getMessage());
      }

      return dealership;
   }
}