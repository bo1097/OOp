/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitsAndVegetables;

import java.util.Random;

/**
 *
 * @author lenovo
 */
public abstract class Plant implements Peellable {

    protected String nameOfPlant;
    protected double weight;
    protected String color;
    protected int maturity; //Зрелость 
    protected boolean isPeeled; // Флаг очищенности 
    
    
    public boolean isPeeled() {
        return isPeeled;
    }
   
   
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

  
    
    
    
    @Override
    public String toString() {
        return String.format(" \r %s Вес- %4.2f; \r Цвет- %s; \r Свежесть- %d; \r Состояние - %s", this.nameOfPlant, weight, color, maturity, (isPeeled ? "Очищен" : "Не очищен"));

    }
}
