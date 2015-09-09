@@ -0,0 +1,17 @@
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
public class VendingException extends Exception {

    public VendingException(String message) {
        super("Vending Machine: " + message);
    }
}
