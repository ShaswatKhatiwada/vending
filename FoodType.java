@@ -0,0 +1,27 @@
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending;

/**
 *
 * @author Shaswat
 */
public enum FoodType {

    Drink,
    Snack;

    public static FoodType fromInteger(int i) {
        switch (i) {
            case 0:
                return FoodType.Drink;
            case 1:
                return FoodType.Snack;
            default:
                return null;
        }
    }
}
