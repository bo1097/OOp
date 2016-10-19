/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.util.Arrays;

/**
 *
 * @author lenovo
 */
public class Train {

    private Carriage[] arrayOfCarriage; // Массив вагонов в поезде
    private int quantityOfCarriages; // Общее количество вагонов поезда
    private int quantityOfCoupe; // Количество вагонов-купе
    private int quantityOfPk; // Количество вагонов Плац-Карт
    private int quantityOfExpressCarriage; // Количество вагонов типа Экспресс
    private int countCarriage;
    private TrainType trainType; // Тип Поезда
   

    public Carriage[] getArrayOfCarriage() {
       return arrayOfCarriage;
    }

   
    
    

    public void setArrayOfCarriage(Carriage[] arrayOfCarriage) {
        this.arrayOfCarriage = arrayOfCarriage;
    }

    public int getQuantityOfCarriages() {
        return quantityOfCarriages;
    }

    
    public void setQuantityOfCarriages(int quantityOfCarriages) {
        this.quantityOfCarriages = quantityOfCarriages;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public int getQuantityOfCoupe() {
        return quantityOfCoupe;
    }

    public int getQuantityOfPk() {
        return quantityOfPk;
    }

    public int getCountCarriage() {
        return countCarriage;
    }
    
    

    public static enum TrainType {
        TRAIN_TYPE_PASSENGER("Пассажирский"), TRAIN_TYPE_EXPRESS("Экспресс");

        private final String name;

        private TrainType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
    

    /*
 Конструктор для создания пассажирского поезда
     */
    public Train(int quantityOfCoupe, int quantityOfPk) {
        this.trainType = TrainType.TRAIN_TYPE_PASSENGER;
        this.quantityOfCoupe = quantityOfCoupe;
        this.quantityOfPk = quantityOfPk;
        this.quantityOfCarriages = quantityOfCoupe + quantityOfPk;
        arrayOfCarriage = new Carriage[quantityOfCarriages];
    }

    /*
    Конструктор для создания экспресс поезда
     */
    public Train(int quantityOfExpressCarriage) {
        this.trainType = TrainType.TRAIN_TYPE_EXPRESS;
        this.quantityOfExpressCarriage = quantityOfExpressCarriage;
        this.quantityOfCarriages = quantityOfExpressCarriage;
        arrayOfCarriage = new Carriage[quantityOfCarriages];
    }

    /*
    Метод добавляет вагон в поезд
     */
    public void addCarriage(Carriage carriage) {
        if (allConditionsTrue(carriage)) {
            arrayOfCarriage[countCarriage++] = carriage;
        }
       
    }
    /*
    Метод проверяет есть ли место в поезде и совпадают ли типы вагонов с типом поезда.
    Если все условия верны, то метод возвращает true
     */
    public boolean allConditionsTrue(Carriage carriage) {
        boolean checked = false;
        if (countCarriage >= arrayOfCarriage.length) {
            throw new IllegalStateException();
        }
          if ((carriage.carriageType == Carriage.CarriageType.CARRIAGE_TYPE_COUPE //Проверка типа поезда
                || carriage.carriageType == Carriage.CarriageType.CARRIAGE_TYPE_PK) //  и типа вагона 
                && this.trainType == TrainType.TRAIN_TYPE_PASSENGER) {
            checked = true; 
        }else if (carriage.carriageType.equals(carriage.carriageType.CARRIAGE_TYPE_EXPRESS) //Проверка типа поезда
                && this.trainType.equals(this.trainType.TRAIN_TYPE_EXPRESS)) {                //  и типа вагона 
            checked = true; 
        }
          else {
            throw new IllegalStateException("Неверный тип вагона для данного поезда");
        }
         return checked;
    }

    
    /*
    Метод выводит все вагоны поезда
     */
    public void showAllCarriages() {
        if (quantityOfCarriages == 0) {
            throw new IllegalArgumentException("В поезде пока не создано ни одного вагона");
        }
        for (int i = 0; i < countCarriage; i++) {
            System.out.println(arrayOfCarriage[i]);
        }

    }

    @Override
    public String toString() {
        return " Тип:" + trainType.getName() + "; Количество вагонов: " + quantityOfCarriages;

    }

}
