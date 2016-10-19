/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vegetables;

import fruitsAndVegetables.Plant;
import fruitsAndVegetables.Plant;

/**
 *
 * @author lenovo
 */
public class Vegetable extends Plant {

    private final double peelProcent = 0.05;

    @Override
    public void peel() {
        weight = weight * peelProcent;
        isPeeled = true;

    }

}
