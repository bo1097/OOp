/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;



/**
 *
 * @author lenovo
 */
public class Carriage implements Comparable<Carriage> {
    
    private int index; // Индекс вагона в поезде
    private int quantityOfPassengers; // Количество пассажиров в вагоне
    private int quantityOfBaggage; // Количество багажа в вагоне
    private int comfortLevel; // Уровень комфорта в вагоне 
    
    public final CarriageType carriageType;

    public int getQuantityOfBaggage() {
        return quantityOfBaggage;
    }

    @Override
    public int compareTo(Carriage o) {
       return this.comfortLevel - o.comfortLevel;
    }
        
    
    public static enum CarriageType {
        CARRIAGE_TYPE_COUPE("Купе"), CARRIAGE_TYPE_PK("Плац-карт"), CARRIAGE_TYPE_EXPRESS("Экспресс");
        
        private final String name;

        private CarriageType(String name) {
            this.name = name;
        }
        
        public String getName(){
            return name;
        }
        

    }
   
    /*
    Конструктор который опредляет тип вагона и его параметры
    */
    public Carriage(CarriageType carriageType, int index, int quantityOfPassengers, int comfortLevel){
        this.carriageType = carriageType;
        this.index = index;
        this.quantityOfPassengers = quantityOfPassengers; 
        this.comfortLevel = comfortLevel;
        quantityOfBaggage = quantityOfPassengers / 3;
    }
    
    
    
    
    public int getQuantityOfPassengers() {
        return quantityOfPassengers;
    }

    @Override
    public String toString() {
        return "Вагон " + index + ". " + "Тип: " + carriageType.getName()+
                "; Количество пассажиров: " + quantityOfPassengers +
                "; Количество багажа: " + quantityOfBaggage + "; Уровень комфорта: " + comfortLevel;
     }
    
    public CarriageType getType(){
        return carriageType;
    }
    
    
}
