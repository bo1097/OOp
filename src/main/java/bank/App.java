/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import bank.Bank.Account;
import bank.Bank.Account.CreditCard;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class App {

    static Scanner sc = new Scanner(System.in);
    static Bank bank = new Bank("Приват-Банк", 5000000, 0.01, 2);

    public static void main(String[] args) {
        boolean finish = false;
        while (!finish) {
            try {
                switch (primaryMenuOperator()) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        enterAccount();
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
    Метод предоставляет пользователю главное меню
    Возвращает выбор пользователя
     */
    public static int primaryMenuOperator() {
        System.out.println("1. Создать аккаунт");
        System.out.println("2. Войти в уже созданный аккаунт");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 2) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;
    }

    /*
    Метод создает аккаунт в банке
     */
    public static void createAccount() {
        System.out.println("1. Создать аккаунт и выйти в главное меню");
        System.out.println("2. Создать аккаунт и сразу добавить кредитную карту");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 2) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        if (userChoice == 1) {
            bank.initializeAccount(initializeCustomer());
        } else if (userChoice == 2) {
            Customer customer = initializeCustomer();
            System.out.println("Введите желаемую сумму денег в кредит");
            int userMoneySumm = Integer.parseInt(sc.next());
            System.out.println("Введите желаемый процент под кредит");
            int userProcent = Integer.parseInt(sc.next());
            bank.initializeAccountAndAddCard(customer, userMoneySumm, userProcent);
        }
    }

    /*
    Метод запрашивает у пользователя нужную информацию
    и возвращает обьект customer, основанный на данной информации.
     */
    public static Customer initializeCustomer() {
        System.out.println("Напишите свое имя");
        String userName = sc.next();
        System.out.println("Напишите свою фамилию");
        String userSurName = sc.next();
        System.out.println("Напишите свою дату рождения");
        String userDateOfBirth = sc.next();
        Customer customer = new Customer(userName, userSurName, userDateOfBirth);
        return customer;
    }

    /*
    Метод перенаправляет пользователя в нужный аккаунт
     */
    public static void enterAccount() {
        Account account = bank.getArrayOfAccounts()[getIndexOfAccount()];
        primaryAccountMenu(account);
    }

    /*
    Метод проверяет введенную пользователем информацию, если такое имя существует, 
    метод возвращает нужный индекс ячейки массива
     */
    public static int getIndexOfAccount() {
        System.out.println("Введите свое имя");
        String userName = sc.next();
        System.out.println("Введите свою фамилию");
        String userSurName = sc.next();
        boolean nameIsValid = false;
        int indexOfValidAccount = 0;
        for (int i = 0; i < bank.getCountOfAccounts(); i++) {
            if (userName.equals(bank.getArrayOfAccounts()[i].getCustomer().getName())
                    && userSurName.equals(bank.getArrayOfAccounts()[i].getCustomer().getSurName())) {
                nameIsValid = true;
                indexOfValidAccount = i;
            }
        }
        if (!nameIsValid) {
            throw new IllegalArgumentException("Клиента с таким именем не существует");
        }
        return indexOfValidAccount;
    }

    /*
    Меню входа на конкретный кредитный счет
     */
    public static void primaryAccountMenu(Account account) {
        System.out.println(account);
        if (account.getCountOfCards() == 0) {
            System.out.println("Создание кредитного счета");
            account.initializeCreditCard(requestedMoney(), requestedCreditProcent());
            chooseCardOrCreate(account);
        } else {
            chooseCardOrCreate(account);
        }

    }


    /*
    Метод запрашивает у пользователя сумму денег в кредит и возвращает ее
     */
    public static double requestedMoney() {
        System.out.println("Напишите желаемую сумму денег в кредит");
        double userMoney = Integer.parseInt(sc.next());
        if (userMoney <= 0) {
            throw new IllegalArgumentException("Сумма денег не может быть меньше или равной нулю");
        }
        return userMoney;
    }

    /*
    Метод запрашивает у пользователя желаемый процент под кредит
     */
    public static double requestedCreditProcent() {
        System.out.println("Напишите желаемый процент под кредит");
        double userProcent = Integer.parseInt(sc.next());
        if (userProcent <= 0) {
            throw new IllegalArgumentException("Процент не может быть меньше или равен нулю");
        }
        return userProcent;
    }

    public static void chooseCardOrCreate(Account account) {
        boolean accountMenu = true;
        while (accountMenu) {
            int createCard = account.getCountOfCards() + 1;
            int goToMainMenu = account.getCountOfCards() + 2;
            for (int i = 0; i < account.getCountOfCards(); i++) {
                System.out.println((i + 1) + ". " + account.getArrayOfCards()[i]);
            }
            System.out.println(createCard + ". Создать еще один кредитный счет");
            System.out.println(goToMainMenu + ". Выйти в главное меню");
            int userChoice = Integer.parseInt(sc.next());
            if (userChoice <= 0 || userChoice > goToMainMenu) {
                throw new IllegalArgumentException("Такого пункта меню нет");
            }
            if (userChoice >= 1 && userChoice < createCard) {
                menuOfCreditCard(account, userChoice - 1);
            } else if (userChoice == createCard) {
                account.initializeCreditCard(requestedMoney(), requestedCreditProcent());
            } else if (userChoice == goToMainMenu) {
               
            }

        }
    }

    public static void menuOfCreditCard(Account account, int indexOfCard) {
        try {
            boolean menuInCard = true;
            while (menuInCard) {
                CreditCard card = account.getArrayOfCards()[indexOfCard];
                switch (userChoiceOnCardMenu()) {
                    case 1:
                        System.out.println("Баланс вашей карты составляет:" + card.getBalanceOfCard());
                        break;
                    case 2:
                        addMoneyOnCard(card);
                        break;
                    case 3:
                        primaryAccountMenu(account);

                }

            }
        } catch (NumberFormatException e) {
            System.out.println("Поробуйте еще раз");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int userChoiceOnCardMenu() {
        System.out.println("1. Посмотреть свой баланс на счету");
        System.out.println("2. Добавить деньги на счет");
        System.out.println("3. Назад в меню Аккаунта");
        int userChoice = Integer.parseInt(sc.next());
        if (userChoice <= 0 || userChoice > 3) {
            throw new IllegalArgumentException("Неверный пункт меню");
        }
        return userChoice;

    }

    public static void addMoneyOnCard(CreditCard card) {
        System.out.println("Введите сумму денег, которую хотите добавить на карту");
        int userSumm = Integer.parseInt(sc.next());
        if (userSumm <= 0) {
            throw new IllegalArgumentException("Сумму денег не может быть меньше нуля");
        }
        card.setMoneyBack(userSumm);
    }

}
