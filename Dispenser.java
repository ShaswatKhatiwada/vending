@@ -0,0 +1,59 @@
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending;

import java.util.Random;

/**
 *
 * @author Shaswat
 */
public class Dispenser {

    private final FoodInformation foodInformation;

    private int quantity;

    private final int dispenserNumber;

    public Dispenser(int dispenserNumber, FoodInformation foodInformation, int quantity) {
        this.foodInformation = foodInformation;
        this.quantity = quantity;
        this.dispenserNumber = dispenserNumber;
    }

    public FoodInformation getFoodInformation() {
        return this.foodInformation;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public Boolean reduceQuantity() {
        if ((this.quantity - 1) < 0) {
            System.out.print("There is not enough items in this dispenser. Dispenser name: "
                    + this.foodInformation.getName());
            return false;
        }
        this.quantity -= 1;
        return true;
    }

    public int getVendingNumber() {
        return this.dispenserNumber;
    }

    @Override
    public String toString() {
        return this.dispenserNumber + ", " + this.foodInformation + ", Quantity: " + this.quantity;
    }

    public String getFileCompatibleString() {
        return this.dispenserNumber + "," + this.foodInformation.getFileCompatibleString() + "," + this.quantity;
    }

}
