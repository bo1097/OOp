/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package foodProcessor;

import fruits.Fruit;
import fruitsAndVegetables.Plant;
import vegetables.Vegetable;

/**
 *
 * @author lenovo
 */
public class FoodProcessor implements Peeler, Cutter, Slicer {
    //implements Peeler, Cutter, Slicer {

    private final String name; // Фирма производитель кухонного комбайна

    /*
    Конструктор для создания кухонного комбайна
     */
    public FoodProcessor(String name) {
        this.name = name;
    }

    @Override
    public double peelItem(Plant plant) {
        if (plant.isPeeled()) {
            if (plant instanceof Fruit) {
                throw new IllegalStateException("Данный фрукт уже очищен");
            } else if (plant instanceof Vegetable) {
                throw new IllegalStateException("Данный овощь уже очищен");
            }
        }
        if (!plant.isPeeled()) {
            plant.peel();
        }
        return plant.getWeight();
    }

    @Override
    public double peelItems(Plant[] plants) {
        double weightOfPlants = 0.0;
        for (Plant plant : plants) {
            if (!plant.isPeeled()) {
                peelItem(plant);
                weightOfPlants += plant.getWeight();
            }

        }
        return weightOfPlants;
    }

    @Override
    public double cut(Plant plant) {
        while (true) {
            if (!plant.isPeeled()) {
                if (plant instanceof Fruit) {
                    throw new IllegalStateException("Попал не очищеный фрукт");
                } else if (plant instanceof Vegetable) {
                    throw new IllegalStateException("Попал не очищенный овощь");
                }
            }
            double weightAfterCut = plant.getWeight() * CUT_PROCENT;
            plant.setWeight(weightAfterCut);
            return plant.getWeight();

        }

    }

    @Override
    public double cutAll(Plant[] plants) {
        double weightsAfterCut = 0;
        for (Plant plant : plants) {
            weightsAfterCut += cut(plant);
        }
        return weightsAfterCut;
    }

    @Override
    public double slice(Plant plant) {
        if (!plant.isPeeled()) {
            if (plant instanceof Fruit) {
                throw new IllegalStateException("Попал не очищенный фрукт");
            } else if (plant instanceof Vegetable) {
                throw new IllegalStateException("Попал не очищенный овощь");
            }
        }
        if (plant.isPeeled()) {
            double weightAfterSlice = plant.getWeight() * SLICE_PROCENT;
            plant.setWeight(weightAfterSlice);
        }
        return plant.getWeight();
    }

    @Override
    public double sliceAll(Plant[] plants) {
        double weightsAfterSlice = 0;
        for (Plant plant : plants) {
            weightsAfterSlice += slice(plant);
        }
        return weightsAfterSlice;
    }

}
