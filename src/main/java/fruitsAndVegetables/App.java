/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fruitsAndVegetables;

import foodProcessor.FoodProcessor;
import fruits.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import vegetables.Carrot;
import vegetables.Cellery;
import vegetables.Onion;
import vegetables.Potato;

/**
 *
 * @author lenovo
 */
public class App {

    static Scanner sc = new Scanner(System.in);
    static Basket[] baskets = new Basket[10];
    static int countBasket;
    static final HashMap<Class<? extends Plant>, String> map = new HashMap<>();
    static FoodProcessor foodProc = new FoodProcessor("Phillips");
    static Random r = new Random();

    static {
        map.put(Apple.class, "Зеленое");
        map.put(Banana.class, "Желтый");
        map.put(Orange.class, "Оранжевый");
        map.put(Pear.class, "Желтая");
        map.put(Carrot.class, "Оражевая");
        map.put(Cellery.class, "Зеленый");
        map.put(Onion.class, "Фиолетовый");
        map.put(Potato.class, "Желтая");
    }

    public static void main(String[] args) {
        boolean finish = false;

        while (!finish) {
            try {
                switch (primaryMenuOperator()) {
                    case 1:
                        createBasket();
                        break;
                    case 2:
                        manipulateBasket();
                        break;
                    case 3:
                        showAllBaskets();
                        break;

                }
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Главное меню. Возвращает выбор пользователя
     */
    public static int primaryMenuOperator() {
        System.out.println("Главное меню");
        System.out.println("1. Создать корзину");
        System.out.println("2. Управлять корзиной");
        System.out.println("3. Просмотреть все корзины");
        System.out.println("4. Выход");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 0 || userChoice > 4) {
            throw new IllegalArgumentException("Такого пункта меню нет. Попробуйте еще раз");
        }
        return userChoice;
    }

    /*
    Метод создает корзину. Добавляет ее в массив и заполняет ее фруктами.
     */
    public static void createBasket() {
        Basket basket = new Basket();
        fillArrayWithBasket(basket);
        addPlants(basket);
    }

    /*
    Метод проверяет есть ли место в массиве корзин. Если нет, то увеличивает длину массива на 10 ячеек.
    Добавляет в массив корзину.
     */
    public static void fillArrayWithBasket(Basket basket) {
        if (baskets.length < countBasket) {
            Arrays.copyOf(baskets, countBasket + 10);
        }
        baskets[countBasket++] = basket;
    }

    /*
    Метод заполняет корзину фруктами.
     */
    public static void addPlants(Basket basket) {
        boolean plantsAreChosen = false;
        while (!plantsAreChosen) {
            initializePlant(chosenPlantOnMenu(), basket);
            switch (addMore(basket)) {
                case 1:
                    continue;
                case 2:
                    plantsAreChosen = true;
            }

        }

    }

    /*
    Метод предоставляет пользователю меню - Выбор растения. 
    Возвращает выбор пользователя
     */
    public static int chosenPlantOnMenu() {
        System.out.println("Добавить фрукты и овощи в корзину");
        System.out.println("1. Яблоко");
        System.out.println("2. Банан");
        System.out.println("3. Апельсин");
        System.out.println("4. Груша");
        System.out.println("5. Морковь");
        System.out.println("6. Сельдерей");
        System.out.println("7. Лук");
        System.out.println("8. Картошка");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 0 || userChoice > 8) {
            throw new IllegalArgumentException("Такого пункта меню нет. Попробуйте еще раз");
        }
        return userChoice;
    }

    /*
    В зависимости от выбор пользователя, метод создает нужный овощь/фрукт и ложит его в корзину
     */
    public static void initializePlant(int chosenPlant, Basket basket) {
        int plantQuantity;
        switch (chosenPlant) {
            case 1:
                System.out.println("Сколько яблок вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Apple apple = new Apple(map.get(Apple.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(apple);
                }
                break;
            case 2:
                System.out.println("Сколько бананов вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Banana banana = new Banana(map.get(Banana.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(banana);
                }
                break;
            case 3:
                System.out.println("Сколько апельсин вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Orange orange = new Orange(map.get(Orange.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(orange);
                }
                break;
            case 4:
                System.out.println("Сколько груш вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Pear pear = new Pear(map.get(Pear.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(pear);
                }
                break;
            case 5:
                System.out.println("Сколько моркови(в штуках) вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Carrot carrot = new Carrot(map.get(Carrot.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(carrot);
                }
                break;
            case 6:
                System.out.println("Сколько сельдерея(в штуках) вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Cellery cellery = new Cellery(map.get(Cellery.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(cellery);
                }
                break;
            case 7:
                System.out.println("Сколько головок лука вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Onion onion = new Onion(map.get(Onion.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(onion);
                }
                break;
            case 8:
                System.out.println("Сколько картошки(в штуках) вы хотите поместить в вашу корзину?");
                plantQuantity = Integer.parseInt(sc.next());
                for (int i = 0; i < plantQuantity; i++) {
                    Potato potato = new Potato(map.get(Potato.class), r.nextDouble() * 250, r.nextInt(9) + 1);
                    basket.put(potato);
                }
                break;
        }

    }

    /*
    Метод спрашивает у пользователя добавить ли еще фруктов\овощей.
    Возвращает число - выбор пользователя.  
     */
    public static int addMore(Basket basket) {
        System.out.format("Вес вашей корзины составляет %.2f грамм", basket.getWeightOfBasket());
        System.out.println("");
        System.out.println("1. Добавить еще фрукт/овощь");
        System.out.println("2. Достаточно");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 1 || userChoice > 2) {
            throw new IllegalArgumentException("Такого пункта меню нет. Попробуйте еще раз");
        }
        return userChoice;
    }

    /*
    Метод выполняет различный дейтсвия над корзиной.
    Выводит, удаляет, добавляет овощи/фрукты 
     */
    public static void manipulateBasket() {
        if (countBasket == 0) {
            throw new IllegalArgumentException("Пока не создано ни одной корзины");
        }
        Basket basket = baskets[chooseBasket()];
        switch (manipulateBasketMenu()) {
            case 1:
                showAllPlants(basket);
                break;
            case 2:
                addPlants(basket);
                break;
            case 3:
                System.out.println("Вы удалили: " + basket.extract(chooseToExtract(basket)));
                break;
            case 4:
                basket.extractAllVegetables();
                break;
            case 5:
                basket.extractAllFruits();
                break;
            case 6:
                System.out.println("Вы удалили все содержимое корзины" + Arrays.toString(basket.extractAll()));
                break;
            case 7:
                foodProcessorOn(basket);
                break;

        }

    }

    /*
    Метод предоставляет пользователю выбор среди уже созданных корзин.
    Возвращает выбор пользователя 
     */
    public static int chooseBasket() {
        System.out.println("Выберите корзину");
        showAllBaskets();
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 1 || userChoice > countBasket) {
            throw new IllegalArgumentException("Такой корзины нет");
        }
        return userChoice - 1;

    }

    /*
    Метод предоставляет пользователю выбор действий над корзиной.
    Возвращает выбор пользователя 
     */
    public static int manipulateBasketMenu() {
        System.out.println("1. Просмотреть все овощи и/или фрукты в корзине");
        System.out.println("2. Добавить овощь или фрукт в корзину");
        System.out.println("3. Изьять определенный овощ или фрукт из корзины");
        System.out.println("4. Изьять из корзины все овощи");
        System.out.println("5. Изьять из корзины все фрукты");
        System.out.println("6. Изьять все содержимое корзины");
        System.out.println("7. Использовать комбайн.");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 1 || userChoice > 7) {
            throw new IllegalArgumentException("Такого пункта меню нет. Попробуйте еще раз");
        }
        return userChoice;
    }

    /*
    Метод выводит все овощи/фрукты, который есть в корзине
     */
    public static void showAllPlants(Basket basket) {
        System.out.println();
        for (int i = 0; i < basket.getCountPlantsInBasket(); i++) {
            System.out.println((i + 1) + " " + basket.getArrayOfPlants()[i]);
        }
    }

    /*
    Метод выводит все корзины созданные пользователем
     */
    public static void showAllBaskets() {
        for (int i = 0; i < countBasket; i++) {
            System.out.println((i + 1) + ". " + baskets[i]);
        }
    }

    /*
    Метод предоставляет пользователю выбор удаления корзины.
    Возвращает выбор пользователя
     */
    public static int chooseToExtract(Basket basket) {
        System.out.println("Выберите овощи/фрукт который хотите изьять");
        showAllPlants(basket);
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > basket.getCountPlantsInBasket()) {
            throw new IllegalArgumentException("В корзине не содержиться фрукта/овоща под таким номером");
        }
        return userChoice - 1;
    }

    /*
    Метод совершает нужные операции с кухонным комбайном.
    Чистит, шинкует и режит ломтиками овощи/фрукты.
     */
    public static void foodProcessorOn(Basket basket) {
        boolean goToMainMenu = false;
        while (!goToMainMenu) {
            try {
                switch (chooseProcessorOperation()) {
                    case 1:
                        peelPlant(basket);
                        break;
                    case 2:
                        cutPlants(basket);
                        break;
                    case 3:
                        slicePlants(basket);
                        break;
                    case 4:
                        goToMainMenu = true;
                        break;
                }

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Метод предоставляет выбор в кухонном комбайне.
    Возвращает выбор пользователя.
     */
    public static int chooseProcessorOperation() {
        System.out.println("Функции комбайна(Внимание: Перед проведением основных операций, овощи/фрукты должны быть почищены)");
        System.out.println("1. Почистить овощи/фрукты");
        System.out.println("2. Шинковать овощи/фрукты");
        System.out.println("3. Нарезать овощи/фрукты ломтиками");
        System.out.println("4. Вернуться в главное меню");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 1 && userChoice > 3) {
            throw new IllegalArgumentException("Такого пункта меню нет");
        }
        return userChoice;
    }

    /*
    В зависимости от выбора пользователя,
    метод возвращает  либо массив овощей, либо фруктов, либо всего содержимого корзины
     */
    public static Plant[] plantsToProcess(Basket basket) {
        while (true) {
            try {
                switch (choosePlantsToProcess()) {
                    case 1:
                        return basket.extractAllFruits();
                    case 2:
                        return basket.extractAllVegetables();
                    case 3:
                        return basket.extractAll();

                }

            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /*
    Метод предоставляет пользователю выбор над какими овощами/фруктами
    проводить операции в кухонном комбайне
     */
    public static int choosePlantsToProcess() {
        System.out.println("Выберите овощи/фрукты для проведения операции");
        System.out.println("1. Только фрукты");
        System.out.println("2. Только овощи");
        System.out.println("3. Вся корзина");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 3) {
            throw new IllegalArgumentException("Такого пункта меню нет");
        }
        return userChoice;
    }

    /*
    Метод очищяет фрукты/овощи. 
    Выводит вес фруктов\овощей перед и после отчистки.
    Так же выводит разницу в весе после отчистки.
     */
    public static void peelPlant(Basket basket) {
        Plant[] plantsToPeel = plantsToProcess(basket);
        double weightBeforePeel = 0;
        for (int i = 0; i < plantsToPeel.length; i++) {
            weightBeforePeel += plantsToPeel[i].weight;
        }
        double weightafterPeel = foodProc.peelItems(plantsToPeel);
        basket.put(plantsToPeel);
        if (weightafterPeel > 0) {
            System.out.format("Вес перед очисткой: %.2f грамм \n", weightBeforePeel);
            System.out.format("Вес после очисткой: %.2f грамм \n", weightafterPeel);
            System.out.format("Потеря в весе составила: %.2f грамм \n", weightBeforePeel - weightafterPeel);
        }
        if (weightafterPeel == 0) {
            throw new IllegalStateException("Данные фрукты/овощи уже почищены");
        }
    }

    /*
    Метод шинкует фрукты/овощи
    Выводит вес фруктов/овощей перед и после шинковки
    Так же выводит разницу в весе после шинковки
     */
    public static void cutPlants(Basket basket) {
        Plant[] plantsToCut = plantsToProcess(basket);
        double weightBeforeCut = 0;
        for (int i = 0; i < plantsToCut.length; i++) {
            weightBeforeCut += plantsToCut[i].weight;
        }
        basket.put(plantsToCut);
        double weightAfterCut = foodProc.cutAll(plantsToCut);
        System.out.format("Вес перед шинковкой: %.2f грамм \n", weightBeforeCut);
        System.out.format("Вес после шинковки: %.2f грамм \n", weightAfterCut);
        System.out.format("Потеря в весе составила: %.2f грамм \n", weightBeforeCut - weightAfterCut);
    }

    public static void slicePlants(Basket basket) {
        Plant[] plantsToCut = plantsToProcess(basket);
        double weightBeforeSlice = 0;
        for (int i = 0; i < plantsToCut.length; i++) {
            weightBeforeSlice += plantsToCut[i].weight;
        }
        basket.put(plantsToCut);
        double weightAfterSlice = foodProc.sliceAll(plantsToCut);
        System.out.format("Вес перед нарезкой: %.2f грамм \n", weightBeforeSlice);
        System.out.format("Вес после нарезки: %.2f грамм \n", weightAfterSlice);
        System.out.format("Потеря в весе составила: %.2f грамм \n ", weightBeforeSlice - weightAfterSlice);
    }

}
