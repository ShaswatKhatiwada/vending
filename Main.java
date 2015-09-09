@@ -0,0 +1,82 @@
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Date;

/**
 *
 * @author Shaswat
 */
public class Main {

    public static void main(String[] args) {
        Date input = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(input);
        LocalDate date;
        date = LocalDate.of(cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH) + 1,
                cal.get(Calendar.DAY_OF_MONTH));
        System.out.println(date);
        try {
            VendingMachine vending = new VendingMachine();
            System.out.println(vending);

            Random random = new Random();
            int maxUser = random.nextInt(20);
            Map<Integer, SalesInformation> salesInfos = new HashMap<>();
            while (true) {
                System.out.println("User comes in!");
                int dispenserChoice = random.nextInt(20) + 100;
                if (vending.ifDispenserExist(dispenserChoice)) {
                    Dispenser dispenser = vending.getDispenser(dispenserChoice);
                    SalesInformation salesInfo;
                    if (salesInfos.containsKey(dispenserChoice)) {
                        salesInfo = salesInfos.get(dispenserChoice);
                    } else {
                        salesInfo = new SalesInformation(dispenserChoice,
                                dispenser.getFoodInformation().getName());
                        salesInfos.put(dispenserChoice, salesInfo);
                    }
                    if (dispenser.reduceQuantity()) {
                        salesInfo.addQuantity();
                        salesInfo.addSales(dispenser.getFoodInformation().getPrice());
                        System.out.println();
                        System.out.println("Thank you for using our vending system. Here is your receipt:");
                        System.out.println("Item name: " + dispenser.getFoodInformation().getName()
                                + "\tQuantity: 1"
                                + "\tAmount: " + dispenser.getFoodInformation().getPrice());
                        System.out.println();
                    } else {
                        System.out.println("Selected dispenser is empty. Please choose another.");
                    }
                } else {
                    System.out.println("Selected dispenser is empty. Please choose another.");
                }
                if (--maxUser <= 0) {
                    break;
                }
            }
            SalesInformation.displaySalesInformation(salesInfos);
            SalesInformation.writeSalesToFile(salesInfos);

            vending.saveVendingInformation();

            System.out.println(" Vending machine is closed " + date);

        } catch (VendingException ve) {
            System.out.println(ve.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
