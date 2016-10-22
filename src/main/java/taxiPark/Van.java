/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxiPark;

/**
 *
 * @author lenovo
 */
public class Van extends Car {
    
    double cargoCapacity;
    
    public Van(){
        
    }
    
    public Van(String name, int price, double fuelConsumption, int maxSpeed, double cargoCapacity) {
        super(name, price, fuelConsumption, maxSpeed);
        this.cargoCapacity = cargoCapacity;
    }
}
