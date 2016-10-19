/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruits;

import fruitsAndVegetables.Plant;

/**
 *
 * @author lenovo
 */
public abstract class Fruit extends Plant {

    private final double peelProcent = 0.02; //Процент очищености фрукта

    @Override
    public void peel() {
        weight = weight - weight * peelProcent;
        isPeeled = true; // Флаг очищенности 
    }

}
