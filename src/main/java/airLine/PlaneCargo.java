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
public class PlaneCargo extends AirPlane{
    
    double maxCapacity;

    public PlaneCargo(String name, int maxFlightDistance, double fuelConsumption, double maxCapacity) {
        super(name, maxFlightDistance, fuelConsumption);
        this.maxCapacity = maxCapacity;
    }
    
    
  
}
