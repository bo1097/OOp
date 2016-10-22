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
public class TaxiPark {
    
    private String name;
    private int quantityOfCars;
    private double price; 
    private int maxCarQuantity; // Максимальное количество машин, которые вмещаются в таксопарк
    private Car [] arrayOfCars;
    private int countOfCars;
    
    public TaxiPark(){
        
    }
    
    public TaxiPark(String name, int maxCarQuantity) {
        this.name = name;
        this.maxCarQuantity = maxCarQuantity;
        arrayOfCars = new Car[maxCarQuantity];
        System.out.println("Таксопарк успешно создан");
    }
    /*
    Метод добавляет определенную машину в таксопарк
    */
    public void addAuto(Car car){
        if(thereAreFreePlaces()){
            arrayOfCars[countOfCars++] = car;
        }
        else{
            throw new IllegalArgumentException("В таксопарке нет места");
        }
    }
    
    /*
    Метод удаляет опрделенную машину из таксопарка.
    Возвращает ее
    */
    public Car removeAuto(Car car){
       boolean carIsFound = false;
       int indexOfCar = 0;
       for(int i = 0 ; i < countOfCars; i ++){
           if(car.equals(arrayOfCars[i])){
               carIsFound = true;
               indexOfCar = i;
           }
       }
       if(carIsFound){
           Car [] temp = new Car[arrayOfCars.length -1];
           System.arraycopy(arrayOfCars, 0, temp, 0, indexOfCar);
           System.arraycopy(arrayOfCars, indexOfCar + 1, temp, indexOfCar, arrayOfCars.length - indexOfCar);
           arrayOfCars = temp;
       }
       return car;
    }
    /*
    Метод проверяет есть ли место в таксопарке,
    в зависимости от этого возвращает true или false
    */
    public boolean thereAreFreePlaces(){
        return countOfCars < arrayOfCars.length;
    }
    /*
    Метод выводит все авто в таксопарке.
    */
    public void showAllCars(){
        for(int i = 0; i < countOfCars; i ++){
            System.out.println((i + 1) + "." + arrayOfCars[i]);
        }
    }

    public int getMaxCarQuantity() {
        return maxCarQuantity;
    }

    public void setMaxCarQuantity(int maxCarQuantity) {
        this.maxCarQuantity = maxCarQuantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOfCars() {
        return quantityOfCars;
    }

    public void setQuantityOfCars(int quantityOfCars) {
        this.quantityOfCars = quantityOfCars;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Car[] getArrayOfCars() {
        return arrayOfCars;
    }

    public void setArrayOfCars(Car[] arrayOfCars) {
        this.arrayOfCars = arrayOfCars;
    }

    public int getCountOfCars() {
        return countOfCars;
    }

    public void setCountOfCars(int countOfCars) {
        this.countOfCars = countOfCars;
    }
    
    
}
