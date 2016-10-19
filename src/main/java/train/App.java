/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lenovo
 */
public class App {

    static Scanner sc = new Scanner(System.in);
    static Train[] arrayOfTrains = new Train[10];
    static int trainCount;
    static Random r = new Random();
    static int quantityOfCoupe; // Количество вагонов купе. 
    static int quantityOfPk; // Количество вагонов плац-карт. 

    /*
    Главное меню
     */
    public static void main(String[] args) {
        boolean finish = false;

        while (!finish) {
            try {
                switch (primaryMenuOperator()) {
                    case 1:
                        createTrain();
                        break;
                    case 2:
                        trainInfo();
                        break;
                    case 3:
                        showAllTrains();
                        break;
                    case 4:
                        finish = true;

                }
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Метод - главное меню. Возвращает выбор пользователя 
     */
    public static int primaryMenuOperator() {
        System.out.println("Главное меню");
        System.out.println("1. Создать поезд");
        System.out.println("2. Информация о поезде");
        System.out.println("3. Посмотреть все поезда");
        System.out.println("4. Выйти");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 1 || userChoice > 4) {
            throw new IllegalArgumentException("Введены неверные данные");
        }
        return userChoice;
    }

    /*
    Метод создает поезд
     */
    public static void createTrain() {
        switch (chooseTrainMenu()) {
            case 1:
                createExpressTrain();
                break;
            case 2:
                createPassengerTrain();
                break;
        }

    }

    /*
    Метод-меню. Возвращает выбор пользователя
     */
    public static int chooseTrainMenu() {
        while (true) {
            try {
                if (trainCount >= 20) {
                    throw new IllegalArgumentException("Максимальное количество поездов на станции - 20");
                }
                System.out.println("Выберите тип поезда");
                System.out.println("1. Экспресс");
                System.out.println("2. Пассажирский");
                int userChoice = Integer.parseInt(sc.next());
                if (userChoice < 1 || userChoice > 2) {
                    throw new IllegalArgumentException("Введены неверные данные");
                }
                return userChoice;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            }
        }
    }

    /*
    Метод создает экспресс поезд
     */
    public static void createExpressTrain() {
        Train trainExpress = new Train(getExpressCarriageQuantity());
        addTrain(trainExpress);
        for (int i = 0; i < trainExpress.getQuantityOfCarriages(); i++) {
            Carriage expressCarriage = new Carriage(Carriage.CarriageType.CARRIAGE_TYPE_EXPRESS,
                    i + 1, checkPassengerQuantity(r.nextInt(60)), 4);
            trainExpress.addCarriage(expressCarriage);
        }

    }

    /*
    Метод возвращает количество вагонов - Экспресс.
     */
    public static int getExpressCarriageQuantity() {
        System.out.println("Сколько вагонов экспресс вы хотите создать?"
                + " ВНИМАНИЕ! Количество вагонов можте быть минимум 5, максимум 15");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice < 5 && userChoice > 15) {
            throw new IllegalArgumentException("Неверное количество вагонов");
        }
        return userChoice;
    }

    /*
    Метод создает обьект поезд и обьекты вагонов купе и плацкарт. Помещает их в поезд.
     */
    public static void createPassengerTrain() {
        getQuantityOfPassengers();
        Train passengerTrain = new Train(quantityOfCoupe, quantityOfPk);
        addTrain(passengerTrain);
        for (int i = 0; i < quantityOfCoupe; i++) {
            Carriage carriageCoupe = new Carriage(Carriage.CarriageType.CARRIAGE_TYPE_COUPE,
                    i + 1, checkPassengerQuantity(r.nextInt(60)), 3);
            passengerTrain.addCarriage(carriageCoupe);
        }
        for (int i = 0; i < quantityOfPk; i++) {
            Carriage carriagePk = new Carriage(Carriage.CarriageType.CARRIAGE_TYPE_PK,
                    i + 1, checkPassengerQuantity(r.nextInt(60)), 1);
            passengerTrain.addCarriage(carriagePk);
        }

    }

    /*
    Метод запрашивает количество вагонов купе и вагонов Плац-Карт.
    В случае если выбор пользователся являеться некоректным. Метод выкидывает IllegalArgumentException.
     */
    public static void getQuantityOfPassengers() {
        try {
            int maxQuantity = 0;
            System.out.println("ВНИМАНИЕ! Максимальное количество вагонов в поезде - 15. Минимальное - 5");
            System.out.println("Введите количество вагонов купе");
            quantityOfCoupe = Integer.parseInt(sc.next());
            maxQuantity += quantityOfCoupe;
            System.out.println("Введите количество вагонов Плац-Карт");
            quantityOfPk = Integer.parseInt(sc.next());
            maxQuantity += quantityOfPk;
            if (maxQuantity < 5 || maxQuantity > 15) {
                throw new IllegalArgumentException("Неверное количество вагонов. Попробуйте еще раз");
            }
        } catch (NumberFormatException e) {
            System.out.println("Попробуйте еще раз");
        }

    }

    /*
    Метод проверяет есть ли место на станции(В массиве arrayOfTrains), если нет, 
     */
    public static void addTrain(Train train) {
        if (trainCount >= arrayOfTrains.length) {
            Arrays.copyOf(arrayOfTrains, trainCount + 10);
        }
        arrayOfTrains[trainCount++] = train;
    }

    /*
    Метод проверяет входное число пассажиров. Возвращает число пассажиров, которое не меньше 15 и не больше 60
     */
    public static int checkPassengerQuantity(int quantity) {
        while (quantity < 15) {
            quantity = r.nextInt(60);
        }
        return quantity;
    }

    /*
    В зависимости от возвращаемых данных, метод перенаправляет на нужный метод
     */
    public static void trainInfo() {
        if (trainCount == 0) {
            throw new IllegalArgumentException("Пока не создано ни одного поезда");
        }
        Train train = arrayOfTrains[chooseTrain()];
        switch (trainInfoMenu()) {
            case 1:
                train.showAllCarriages();
                break;
            case 2:
                showPassengersInRange(train);
                break;
            case 3:
                sortOnComfortLevel(train);
                break;
            case 4:
                showQuantityOfPassengers(train);
                break;
            case 5:
                showQuantityOfBaggage(train);
                break;
        }

    }

    /*
    Метод предоставляет меню для выбора действий над поездом. Возвращает число - пункт меню
     */
    public static int trainInfoMenu() {
        while (true) {
            try {
                System.out.println("1. Показать все вагоны поезда");
                System.out.println("2. Найти в поезде вагоны с заданным диапазоном пассажиров");
                System.out.println("3. Отсортировать вагоны на основе уровня комфортности");
                System.out.println("4. Посчитать общую численность пассажиров ");
                System.out.println("5. Подсчитать общее число багажа");
                int userChoice = Integer.parseInt(sc.next());
                if (userChoice < 1 || userChoice > 5) {
                    throw new IllegalArgumentException();
                }
                return userChoice;
            } catch (NumberFormatException e) {
                System.out.println("Попробуйте еще раз");
            } catch (IllegalArgumentException e) {
                System.out.println("Неверные данные");
            }

        }
    }

    /*
    Метод предоставляет пользователю выбор поездов. Возвращает число - элемент массива нужного поезда
     */
    public static int chooseTrain() {
        System.out.println("Выберите поезд");
        for (int i = 0; i < trainCount; i++) {
            System.out.println((i + 1) + ". " + arrayOfTrains[i]);
        }
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > trainCount) {
            throw new IllegalArgumentException("Такого вагона в поезде нет");
        }
        return userChoice - 1;
    }

    /*
    Метод выводит в консоль все поезда на станции
     */
    public static void showAllTrains() {
        if (trainCount == 0) {
            throw new IllegalArgumentException("Пока не создано ни одного поезда");
        }
        for (int i = 0; i < trainCount; i++) {
            System.out.println(arrayOfTrains[i]);
        }
    }

    public static void showPassengersInRange(Train train) {
        boolean finish = false;
        while (!finish) {
            try {
                System.out.println("Напишите диапазон количества пассажиров в поезде");
                String userRange = sc.next();
                if (rangeIsValid(userRange)) {
                    String[] arrayOfRange = userRange.split("-");
                    int from = Integer.parseInt(arrayOfRange[0]);
                    int to = Integer.parseInt(arrayOfRange[1]);
                    boolean rangeIsActual = false;
                    for (int i = 0; i < train.getQuantityOfCarriages(); i++) {
                        if (train.getArrayOfCarriage()[i].getQuantityOfPassengers() >= from
                                && train.getArrayOfCarriage()[i].getQuantityOfPassengers() <= to) {
                            rangeIsActual = true;
                            System.out.println(train.getArrayOfCarriage()[i]);
                        }
                    }
                    if (!rangeIsActual) {
                        throw new IllegalArgumentException("Вагонов с количеством пассажиров в данном диапазоне нет");
                    }
                    finish = true;
                } else {
                    throw new IllegalArgumentException("Диапазон написан не верно. Попробуйте еще раз");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());

            }
        }
    }

    /*
        Метод проверяет написанный пользователем диапазон.
    Если все правильно, то возвращает true
     */
    public static boolean rangeIsValid(String range) {
        Pattern p = Pattern.compile("[0-6]?[0-9]{1}[-]{1}[0-6]?[0-9]{1}");
        Matcher m = p.matcher(range);
        return m.matches();
    }

    /*
    Метод сортирует вагоны по уровню комфорта
     */
    public static void sortOnComfortLevel(Train train) {
        Arrays.sort(train.getArrayOfCarriage());
        train.showAllCarriages();
    }

    /*
    Метод выводит общее количество пассажиров на поезде
     */
    public static void showQuantityOfPassengers(Train train) {
        int passengerSum = 0;
        for (int i = 0; i < train.getCountCarriage(); i++) {
            passengerSum += train.getArrayOfCarriage()[i].getQuantityOfPassengers();
        }
        System.out.println("Количество пассажиров на поезде: " + passengerSum);
    }

    public static void showQuantityOfBaggage(Train train) {
        int baggageSumm = 0;
        for (int i = 0; i < train.getCountCarriage(); i++) {
            baggageSumm += train.getArrayOfCarriage()[i].getQuantityOfBaggage();
        }
        System.out.println("Общее количество багажа на поезде: " + baggageSumm);
    }
}
