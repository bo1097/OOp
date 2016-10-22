/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taxiPark;

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
    static TaxiPark[] arrayOfParks = new TaxiPark[2];
    static Random r = new Random();
    static int countOfParks;
    static String[] namesOfCars = {
        "Bmw",
        "Audi",
        "Wolksvagen",
        "Opel",
        "Toyota",
        "Mazda",
        "Ford",
        "Deo",
        "Жигули",
        "Skoda",};
    
    
    public static void main(String[] args) {
        boolean finish = false;
        try {
            while (!finish) {
                switch (primaryMenuOperator()) {
                    case 1:
                        createTaxiPark();
                        break;
                    case 2:
                        manipulateTaxiPark();
                        break;
                }
            }

        } catch (NumberFormatException e) {
            System.out.println("Попробуйте еще раз");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    /*
    Метод предоставляет пользователю главное меню.
    Возвращает выбор пользователя
     */
    public static int primaryMenuOperator() {
        System.out.println("Главное меню--->");
        System.out.println("1. Создать таксопарк");
        System.out.println("2. Зайти в свой таксопарк");
        System.out.println("3. Посмотреть все таксопарки");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 3) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    public static void createTaxiPark() {
        TaxiPark taxiPark = new TaxiPark(getNameOfPark(), getQuantityOfPlaces());
        fillArrayWithTaxiPark(taxiPark);
    }

    /*
    Метод запрашивает, проверяет и возвращает максимальное количество мест в таксопарке
     */
    public static int getQuantityOfPlaces() {
        System.out.println("Введите максимальное количество мест в таксопарке");
        int userQuantity = Integer.parseInt(sc.next());
        if (userQuantity <= 0) {
            throw new IllegalArgumentException("Количество мест не может быть меньше или равным нулю");
        } else if (userQuantity > 300) {
            throw new IllegalArgumentException("Максимальное количество мест - 300");
        }
        return userQuantity;
    }

    /*
    Метод запрашивает и возвращает имя таксопарка придуманное пользователем
     */
    public static String getNameOfPark() {
        System.out.println("Введите имя для таксопарка");
        String userNameOfPark = sc.next();
        return userNameOfPark;
    }

    public static void fillArrayWithTaxiPark(TaxiPark park) {
        if (arrayOfParks.length <= countOfParks) {
            arrayOfParks = Arrays.copyOf(arrayOfParks, (3 * arrayOfParks.length / 2) + 1);
        }
        arrayOfParks[countOfParks++] = park;
    }

    /*
    Метод предоставляет пользователю различные опции,
    которые можно проделать надо таксопарком
     */
    public static void manipulateTaxiPark() {
        TaxiPark park = arrayOfParks[properTaxiPark()];
        switch (menuOnTaxiPark()) {
            case 1:
                addAuto(park);
                break;
            case 2:
                System.out.println("Вы удалили машину " + park.getArrayOfCars()[carToExtract(park)]);
                break;
            case 3:
                park.showAllCars();
                break;
            case 4:
                sortOnFuel(park);
                break;
            case 5:
                carsInSpeedRange(park);
                break;
            case 6:
                

        }
    }

    /*
    Если количество таксопарков равно 1, то метод возвращает его индекс в массиве.
    В ином случае метод запрашивает у пользователя какой именно таксопарк и возвращает выбор.
     */
    public static int properTaxiPark() {
        while (true) {
            if (countOfParks == 0) {
                return 0;
            } else {
                System.out.println("Выберите таксопарк --->");
                for (int i = 0; i < arrayOfParks.length; i++) {
                    System.out.println((i + 1) + ". " + arrayOfParks[i]);
                }
                int userChoice = Integer.parseInt(sc.next());
                if (userChoice <= 0 || userChoice > countOfParks) {
                    throw new IllegalArgumentException("Не найден таксопарк под таким номером");
                }
                return userChoice - 1;
            }
        }
    }

    /*
    Метод предоставляет и возвращает выбор пользователя
     */
    public static int menuOnTaxiPark() {
        System.out.println("Меню таксопарка --->");
        System.out.println("1. Добавить авто в таксопарк");
        System.out.println("2. Убрать авто из таксопарка");
        System.out.println("3. Посмотреть все авто в таксопарке");
        System.out.println("4. Отсортировать авто исходя от уровня расхода топлива");
        System.out.println("5. Вывести все авто определенного диапазона");
        System.out.println("6. Вернуться в главное меню");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 6) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    /*
    Метод добавляет авто в таксопарк
     */
    public static void addAuto(TaxiPark park) {
        switch (chosenAutoType()) {
            case 1:
                createAndAddSedan(park);
                break;
            case 2:
                createAndAddVan(park);
                break;
        }
    }

    /*
    Метод предоставляет меню выбора типа авто
    Возвращает выбор пользователя
     */
    public static int chosenAutoType() {
        System.out.println("Выбор авто --->");
        System.out.println("Выберите тип автомобиля которых хотите добавить");
        System.out.println("1. Седан");
        System.out.println("2. Грузовой");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 2) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    /*
    Метод создает и добавляет в таксопарк нужное
    количество седан машин
     */
    public static void createAndAddSedan(TaxiPark park) {
        System.out.println("Напишите количество седанов, которое хотите добавить в таксопарк?");
        int userQuantity = Integer.parseInt(sc.next());
        for (int i = 0; i < userQuantity; i++) {
            Sedan sedan = new Sedan(namesOfCars[r.nextInt(namesOfCars.length)], countPrice(),
                    countFuelConsumption(), countMaxSpeed(), r.nextInt(4) + 1);
            park.addAuto(sedan);
        }
    }

    /*
    Метод создает и добавляет в таксопарк нужное количество
    грузовых машин
     */
    public static void createAndAddVan(TaxiPark park) {
        System.out.println("Сколько грузовых машин вы хотите добавить в таксопарк?");
        int userQuantity = Integer.parseInt(sc.next());
        for (int i = 0; i < userQuantity; i++) {
            Van van = new Van(namesOfCars[r.nextInt(namesOfCars.length)], countPrice(),
                    countFuelConsumption(), countMaxSpeed(), countCapacity());
            park.addAuto(van);
        }
    }

    /*
    Метод возвращает рандомную цену автомобиля в диапазоне от 5000 до 500000
     */
    public static int countPrice() {
        int price = r.nextInt(500000);
        while (price <= 5000) {
            price = r.nextInt(500000);
        }
        return price;
    }

    /*
    Метод возвращает рандомный расход топлива
    в диапазоне от 8 до 30
     */
    public static double countFuelConsumption() {
        double consumption = r.nextDouble();
        while (consumption < 8 || consumption > 30) {
            consumption = r.nextDouble();
        }
        return consumption;
    }

    /*
    Метод возвращает рандомную максимальную скорость 
    в диапазоне от 70 до 250
     */
    public static int countMaxSpeed() {
        int maxSpeed = r.nextInt(250);
        while (maxSpeed < 70) {
            maxSpeed = r.nextInt(250);
        }
        return maxSpeed;
    }

    /*
    Метод возвращает рандомную грузоподьменость автомобиля(в тоннах)
     */
    public static double countCapacity() {
        double capacity = r.nextDouble();
        while (capacity < 1 || capacity > 4) {
            capacity = r.nextDouble();
        }
        return capacity;
    }
    /*
    Метод предоставляет выбор машины,
    которую требуеться удалить.
    Возвращает выбор пользователя
    */
    public static int carToExtract(TaxiPark park){
        System.out.println("Выберите авто, которое хотите удалить");
        park.showAllCars();
        int userChoice = Integer.parseInt(sc.next());
        if(userChoice <= 0 || userChoice > park.getArrayOfCars().length){
            throw new IllegalArgumentException("Нет машины под таким номером");
        }
        return userChoice -1;
    }
    /*
    Метод сортирует машины в таксопарке
    */
    public static void sortOnFuel(TaxiPark park){
        Arrays.sort(park.getArrayOfCars());
        park.showAllCars();
    }
    
    public static void carsInSpeedRange(TaxiPark park){
        System.out.println("Через дефис напишите диапазон скорости машины");
        String userRange = sc.next();
        String [] range  = userRange.split("-");
        int from = Integer.parseInt(range[0]);
        int to = Integer.parseInt(range[1]);
        boolean carIsFound = false;
        for(int i = 0; i < park.getCountOfCars(); i ++){ 
            if(park.getArrayOfCars()[i].getMaxSpeed()>=from &&
                    park.getArrayOfCars()[i].getMaxSpeed()<=to){
                carIsFound = true;
                System.out.println(park.getArrayOfCars()[i]);
            }
        }
        if(!carIsFound){
            throw new IllegalArgumentException("Машин с таким диапазоном скорости не найдено");
        }
        
    }
    
//    /*
//        Метод проверяет написанный пользователем диапазон.
//    Если все правильно, то возвращает true
//     */
//    public static boolean rangeIsValid(String range) {
//        Pattern p = Pattern.compile("[0-6]?[0-9]{1}[-]{1}[0-6]?[0-9]{1}");
//        Matcher m = p.matcher(range);
//        return m.matches();
//    }
}
