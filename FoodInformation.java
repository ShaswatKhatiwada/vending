@@ -0,0 +1,54 @@
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
public class FoodInformation {

    private final FoodType foodType;

    private final String name;

    private final Double price;

    private final String nutritions;

    public FoodInformation(String name, Double price, String nutritions, FoodType foodType) {
        this.name = name;
        this.price = price;
        this.nutritions = nutritions;
        this.foodType = foodType;
    }

    public FoodType getFoodType() {
        return this.foodType;
    }

    public String getName() {
        return this.name;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getNutritions() {
        return this.nutritions;
    }

    @Override
    public String toString() {
        return this.name + ", Nutritions: " + this.nutritions + ", Price: $" + this.price;
    }

    public String getFileCompatibleString() {
        return this.name + "," + this.nutritions + "," + this.price + "," + this.foodType.ordinal();
    }

}
