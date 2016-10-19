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
public interface Cutter {
     final double CUT_PROCENT = 0.02;
    
    /*
    Метод для шинковки овоща/фрукта.
    Возвращает вес овоща/фрукта после шинковки
    */
    public double cut(Plant plant);  
    
    /*
    Метод для шинковки сразу нескольких овощей/фруктов
    Возвращает вес овощей/фруктов после шинковки
    */
    public double cutAll(Plant ... plant);
}
