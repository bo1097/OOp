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
public class Car implements Comparable<Car>{
    
    private String name; // Имя производителя машины
    private int price;
    private double fuelConsumption; //Расход топлива 
    private int maxSpeed; 
    
    
    public Car(){
        
    }

    public Car(String name, int price, double fuelConsumption, int maxSpeed) {
        this.name = name;
        this.price = price;
        this.fuelConsumption = fuelConsumption;
        this.maxSpeed = maxSpeed;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int compareTo(Car o) {
        return Double.compare(this.fuelConsumption, o.fuelConsumption);
    }
    
    
}
