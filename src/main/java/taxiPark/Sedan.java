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
public class Sedan extends Car{
    
    int countOfSeats;
    
    public Sedan(){
        
    }
    
    public Sedan(String name, int price, double fuelConsumption, int maxSpeed, int countOfSeats) {
        super(name, price, fuelConsumption, maxSpeed);
        this.countOfSeats = countOfSeats;
    }
    
    
}
