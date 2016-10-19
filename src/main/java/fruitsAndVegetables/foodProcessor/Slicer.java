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
public interface Slicer {
    
    
     final double SLICE_PROCENT = 0.02;
    /*
    Метод нарезает овощь/фрукт ломтиками
    Возвращает вес нарезанного овоща/фрукта
    */
    public double slice(Plant plant);
    
    /*
    Метод нарезает ломтиками сразу несколько овощей/фруктов.
    Возвращает вес нарезанных овощей/фруктов.
    */
    public double sliceAll(Plant ... plant);
    
}
