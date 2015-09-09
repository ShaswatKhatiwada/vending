@@ -0,0 +1,114 @@
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Shaswat
 */
public class VendingMachine {

    public static final String DISPENSER_INFO_FILE_NAME = "dispenser.txt";

    private ArrayList<Dispenser> dispensers;

    private ArrayList<Integer> dispenserNumbers;

    public VendingMachine() throws VendingException {
        this.dispensers = new ArrayList<>();
        this.dispenserNumbers = new ArrayList<>();
        LoadDispensers();
    }

    public void addDispenser(Dispenser d) {
        this.dispensers.add(d);
    }

    public Dispenser getDispenser(int number) {
        for (Dispenser d : this.dispensers) {
            if (d.getVendingNumber() == number) {
                return d;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        for (Dispenser d : this.dispensers) {
            result += d + System.lineSeparator();
        }
        return result;
    }

    public Boolean ifDispenserExist(int dispenserNumber) {
        return this.dispenserNumbers.contains(dispenserNumber);
    }

    private void LoadDispensers() throws VendingException {
        if (!new File(DISPENSER_INFO_FILE_NAME).exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(DISPENSER_INFO_FILE_NAME))) {
                String food = "100,Kitkat,Sugar:20g,2.00,1,15";
                bw.write(food);
                bw.newLine();
                food = "101,Coke,Sugar:39g,1.50,0,20";
                bw.write(food);
                bw.newLine();
                bw.close();
            } catch (Exception e) {
                throw new VendingException("Problem writing file: " + DISPENSER_INFO_FILE_NAME);
            }
        }
        loadDispensersFromFile();
    }

    private void loadDispensersFromFile() throws VendingException {
        try (BufferedReader br = new BufferedReader(new FileReader(DISPENSER_INFO_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] disInfos = line.split(",");
                if (disInfos.length < 6) {
                    throw new VendingException("Problem loading file. Too few information: "
                            + DISPENSER_INFO_FILE_NAME);
                }
                int dispenserNumber = Integer.parseInt(disInfos[0]);
                String foodName = disInfos[1];
                String nutritions = disInfos[2];
                Double foodPrice = Double.parseDouble(disInfos[3]);
                FoodType fType = FoodType.fromInteger(Integer.parseInt(disInfos[4]));
                int quantity = Integer.parseInt(disInfos[5]);

                FoodInformation foodInfo = new FoodInformation(foodName, foodPrice, nutritions, fType);
                this.dispensers.add(new Dispenser(dispenserNumber, foodInfo, quantity));
                this.dispenserNumbers.add(dispenserNumber);
            }
            br.close();
        } catch (Exception e) {
            throw new VendingException("Problem loading file: " + DISPENSER_INFO_FILE_NAME);
        }
    }

    public void saveVendingInformation() throws VendingException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(DISPENSER_INFO_FILE_NAME, false))) {
            for (Dispenser d : this.dispensers) {
                bw.write(d.getFileCompatibleString());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            throw new VendingException("Problem writing file: " + DISPENSER_INFO_FILE_NAME);
        }
        loadDispensersFromFile();
    }
}
