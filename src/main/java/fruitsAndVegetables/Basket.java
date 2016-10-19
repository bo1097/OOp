/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitsAndVegetables;

import fruits.*;
import java.util.Arrays;
import vegetables.*;

/**
 *
 * @author lenovo
 */
public class Basket {

    private Plant[] arrayOfPlants; // Массив для хранения овощей и фруктов
    private final int DEFAULT_CAPACITY = 10; // Емкость корзины по умолчанию
    private double weightOfBasket = 0; // Счетчик веса в корзине
    private int countPlantsInBasket = 0; // Счетчик количества растений в корзине

    /*
    Конструктор для создания корзины. Корзина будет иметь десять пустых емкостей (по умолчанию) 
     */
    public Basket() {
        arrayOfPlants = new Plant[DEFAULT_CAPACITY];
    }

    /*
     Метод помещает овощь или фрукт в ячейку корзины
     */
    public void put(Plant plant) {
        int freeIndex = countPlantsInBasket;
        checkBasket(++countPlantsInBasket);
        arrayOfPlants[freeIndex] = plant;
        weightOfBasket += plant.weight;
    }
    
    public void put(Plant [] plants){
        checkBasket(countPlantsInBasket + plants.length);
        System.arraycopy(plants, 0 , arrayOfPlants, countPlantsInBasket, plants.length);
        countPlantsInBasket += plants.length;
        getActualWeightOfBasket();
        

    }

    /*
    Метод проверяет есть ли в корзине нужное минимальное число ячеек.
    Если нет, то метод увеличивает корзину на нужное количество ячеек
     */
    public void checkBasket(int checkedNumber) {
        if (arrayOfPlants.length < checkedNumber) {
            arrayOfPlants = Arrays.copyOf(arrayOfPlants, checkedNumber + 10);
        }
    }
    
    public void getActualWeightOfBasket(){
        double weight = 0;
        for(int i = 0; i < countPlantsInBasket; i ++){
            weight += arrayOfPlants[i].weight;
        }
        weightOfBasket = weight;
    }

    /*
    Метод перекладывает определенные овощи/фрукты в нужную корзину. 
     */
    public void put(Basket basket, Plant[] somearrayOfPlants) {
        System.arraycopy(basket.arrayOfPlants, countPlantsInBasket,
                somearrayOfPlants, 0, countPlantsInBasket + somearrayOfPlants.length);
    }

    /*
    Метод удаляет из корзины нужный фрукт/овощь и возвращает его
     */
    public Plant extract(int indexOfElement) {
        if (indexOfElement < 0 || indexOfElement > arrayOfPlants.length) {
            throw new IllegalArgumentException("Такого элемента в корзине нет");
        }
        Plant removedPlant = arrayOfPlants[indexOfElement];
        remove(indexOfElement);
        return removedPlant;

    }

    /*
    Данный метод удаляет элемент массива по индексу. Все элементы после него соответственно сдвигаются вправо
     */
    public void remove(int index) {
        Plant[] copyArray = new Plant[arrayOfPlants.length - 1];
        weightOfBasket -= arrayOfPlants[index].weight;
        System.arraycopy(arrayOfPlants, 0, copyArray, 0, index);
        System.arraycopy(arrayOfPlants, index + 1, copyArray, index, arrayOfPlants.length - index - 1);
        arrayOfPlants = copyArray;
        countPlantsInBasket--;

    }

    /*
    Данный метод удаляет из корзины все овощи/фрукты и возвращает их ввиде массива
     */
    public Plant[] extractAll() {
        if (countPlantsInBasket == 0) {
            throw new IllegalArgumentException("Корзина итак пуста");
        }
        Plant[] contentOfBasket = new Plant[countPlantsInBasket];
        System.arraycopy(arrayOfPlants, 0, contentOfBasket, 0, countPlantsInBasket);
        arrayOfPlants = new Plant[DEFAULT_CAPACITY];
        countPlantsInBasket = 0;
        weightOfBasket = 0;
        return contentOfBasket;
    }

    /*
    
     */
    public Plant[] extractAllVegetables() {
        Plant [] arrayOfExtractVegetables = new Plant[countPlantsInBasket];
        int counter = 0;
        if (countPlantsInBasket == 0) {
            throw new IllegalArgumentException("Корзина пуста");
        }
        if (!thereAreVegetablesInBasket()) {
            throw new IllegalArgumentException("В корзине нет овощей.");
        }
        while (thereAreVegetablesInBasket()) {
            for (int i = 0; i < countPlantsInBasket; i++) {
                if (arrayOfPlants[i] instanceof Vegetable) {
                    arrayOfExtractVegetables[counter++] = arrayOfPlants[i];
                    remove(i);
                }
            }
        }
        return Arrays.copyOf(arrayOfExtractVegetables, counter);

    }

    public Plant[] extractAllFruits() {
        
        Plant[] arrayOfExtractFruits = new Plant[arrayOfPlants.length];
        int counter = 0;
        if (countPlantsInBasket == 0) {
            throw new IllegalArgumentException("Корзина пуста");
        }
        if (!thereAreFruitsInBasket()) {
            throw new IllegalArgumentException("В корзине нет фруктов");
        }
        while (thereAreFruitsInBasket()) {
            for (int i = 0; i < countPlantsInBasket; i++) {
                if (arrayOfPlants[i] instanceof Fruit) {
                    arrayOfExtractFruits[counter++] = arrayOfPlants[i];
                    remove(i);
                }
            }
        }
        return Arrays.copyOf(arrayOfExtractFruits, counter);
    }

    public boolean thereAreVegetablesInBasket() {
        boolean basketHasVegetables = false;
        for (int i = 0; i < countPlantsInBasket; i++) {
            if (arrayOfPlants[i] instanceof Vegetable) {
                basketHasVegetables = true;
            }
        }
        return basketHasVegetables;
    }

    public boolean thereAreFruitsInBasket() {
        boolean basketHasFruits = false;
        for (int i = 0; i < countPlantsInBasket; i++) {
            if (arrayOfPlants[i] instanceof Fruit) {
                basketHasFruits = true;
            }
        }
        return basketHasFruits;
    }

    @Override
    public String toString() {
        return String.format("Вес корзины: %.2f Количество овощей и фруктов: %d ", weightOfBasket, countPlantsInBasket);

    }

    public Plant[] getArrayOfPlants() {
        return arrayOfPlants;
    }

    public int getCountPlantsInBasket() {
        return countPlantsInBasket;
    }

    public double getWeightOfBasket() {
        return weightOfBasket;
    }

}
