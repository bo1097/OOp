/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airLine;

/**
 *
 * @author lenovo
 */
public class AirPlane {
    private String name;
    private int maxFlightDistance;
    private double fuelConsumption;

    public AirPlane(String name, int maxFlightDistance, double fuelConsumption) {
        this.name = name;
        this.maxFlightDistance = maxFlightDistance;
        this.fuelConsumption = fuelConsumption;
    }

    public AirPlane() {
    }
    
    

    public int getMaxFlightDistance() {
        return maxFlightDistance;
    }

    public void setMaxFlightDistance(int maxFlightDistance) {
        this.maxFlightDistance = maxFlightDistance;
    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }
    
    
    
}
