/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodProcessor;

import fruitsAndVegetables.Plant;

/**
 *
 * @author lenovo
 */
public interface Peeler {
    /*
    Метод чистит какой - то определенный овощь/фрукт.
    Возвращает вес обработанного фрукта/овоща.
    */
    public double peelItem(Plant plant);
    /*
    Метод чистит сразу несколько овощей/ фруктов.
    * Возвращает вес обработанных фруктов/овощей
    */
    public double peelItems(Plant ... plant);
    
}
