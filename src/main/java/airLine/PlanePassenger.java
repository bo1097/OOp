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
public class PlanePassenger extends AirPlane{
    
    private int quantityOfSeats;

    public PlanePassenger(String name, int maxFlightDistance, double fuelConsumption, int quantityOfSeats) {
        super(name, maxFlightDistance, fuelConsumption);
        this.quantityOfSeats = quantityOfSeats;
    }

    public PlanePassenger() {
    }

    public int getQuantityOfSeats() {
        return quantityOfSeats;
    }

    public void setQuantityOfSeats(int quantityOfSeats) {
        this.quantityOfSeats = quantityOfSeats;
    }
    
    
    
}
