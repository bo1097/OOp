/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airLine;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class App {

    static Scanner sc = new Scanner(System.in);
    static Random r = new Random();
    static AirLine airLine;
    static String[] namesOfPlanes = {
        "Boeing",
        "Zvezda",
        "Revell",
        "Aircraft",
        "Admiral"
    };

    public static void main(String[] args) {
        boolean finish = false;
        while (!finish) {
            try {
                switch (primaryMenuOperator()) {
                    case 1:
                        createAirLine();
                        break;
                    case 2:
                        primaryAirLineMenu();
                        break;

                }

            } catch (NumberFormatException e) {
                System.out.println("Поробуйте еще раз");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /*
    Меню предоставляет главное меню
    Проверяет и возвращает выбор пользователя
     */
    public static int primaryMenuOperator() {
        System.out.println("Главное меню --->");
        System.out.println("1. Создать авиалинию");
        System.out.println("2. Меню авиалинии");

        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 2) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    /*
    Метод создает обьект класса AirLine
     */
    public static void createAirLine() {
        System.out.println("Напишите имя для своей Авиалинии");
        String userName = sc.next();
        airLine = new AirLine(userName);
    }

    /*
    Главное меню авиакомпании
     */
    public static void primaryAirLineMenu() {
        switch (airLineMenuOperator()) {
            case 1:
                addPlanes();
                break;
            case 2:
                airLine.extractPlane(airLine.getArrayOfPlanes()[planeToRemove()]);
                break;
            case 3:
                airLine.showAllPlanes();
                break;
            case 4:

        }

    }

    /*
    Метод предоставляет меню выбора опций.
    Проверяет и возвращает выбор пользователя
     */
    public static int airLineMenuOperator() {
        System.out.println("Главное меню авиалинии --->");
        System.out.println("1. Добавить самолеты в авиалинию");
        System.out.println("2. Убрать самолеты с авиалинии");
        System.out.println("3. Просмотреть все самолеты");
        System.out.println("4. Подсчитать общую вместимость и грузоподьемность авиакомпании");
        System.out.println("5. Провести сортировку по дальности полета самолетов");
        System.out.println("6. Найти самолет с заданным диапазоном потребления горючего");

        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 6) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    public static void addPlanes() {
        switch (chosenPlane()) {
            case 1:
                passengerPlanes();
                break;
            case 2:
                cargoPlanes();
                break;

        }
    }

    /*
    Метод предоставляет выбор типа самолета 
    Возвращает выбор пользователя
     */
    public static int chosenPlane() {
        System.out.println("Выберите тип самолетов которые хотите добавить");
        System.out.println("1. Пассажирский");
        System.out.println("2. Грузовой");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 2) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    /*
    Метод создает нужное количество пассажирских самолетов
     */
    public static void passengerPlanes() {
        System.out.println("Сколько пассажирских самолетов вы хотите создать?");

        for (int i = 0; i < Integer.parseInt(sc.next()); i++) {
            AirPlane plane = new PlanePassenger(namesOfPlanes[r.nextInt(namesOfPlanes.length)], randomDistance(),
                    rFuelConsumption(), quantityOfSeats());
            airLine.addPlane(plane);
        }
    }

    /*
    Метод генерирует рандомную дистанцию, которую самолет 
    может пролететь на одной заправке
     */
    public static int randomDistance() {
        int dist = r.nextInt(15000);
        while (dist < 5000) {
            dist = r.nextInt(15000);
        }
        return dist;
    }

    /*
    Метод генерирует рандомный расход топлива 
    Расход измеряется в кг/час
     */
    public static double rFuelConsumption() {
        int cons = r.nextInt(150);
        while (cons < 25) {
            cons = r.nextInt(150);
        }
        return cons * r.nextDouble();
    }

    /*
    Метод запрашивает про вместимость самолета
    Проверяет и возвращает выбор пользователя
     */
    public static int quantityOfSeats() {
        int quantity = r.nextInt(300);
        while (quantity <= 50) {
            quantity = r.nextInt(300);
        }
        return quantity;
    }

    /*
    Метод создает нужное количество грузовых самолетов
     */
    public static void cargoPlanes() {
        System.out.println("Сколько груовых самолетов вы хотите создать?");
        for (int i = 0; i < Integer.parseInt(sc.next()); i++) {
            AirPlane plane = new PlaneCargo(namesOfPlanes[r.nextInt(namesOfPlanes.length)],
                    randomDistance(), rFuelConsumption(), rFuelConsumption());
            airLine.addPlane(plane);
        }

    }

    /*
    Метод генерирует рандомную грузоподьемность
    (в тоннах). Возвращает ее
     */
    public static double rCapacity() {
        int capacity = r.nextInt(250);
        while (capacity < 20) {
            capacity = r.nextInt(250);
        }
        return capacity * r.nextDouble();
    }

    /*
    Метод возвращает индекс самолета в массиве,
    который требуется удалить
     */
    public static int planeToRemove() {
        System.out.println("Выберите самолет, который хотите удалить");
        airLine.showAllPlanes();
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > airLine.getCountOfPlanes()) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice - 1;
    }

    /*
    Метод высчитывает и возвращает общую вместимость самолетов в АвиаКомпании
     */
    public static void countQuantityOfSeats() {
        int overallQuantity = 0;
        for (int i = 0; i < airLine.getCountOfPlanes(); i++) {
            if (airLine.getArrayOfPlanes()[i] instanceof PlanePassenger) {
               // overallQuantity += (PlanePassenger)airLine.getArrayOfPlanes()[i].getQuantityOfSeats;
            }
        }
    }

}
