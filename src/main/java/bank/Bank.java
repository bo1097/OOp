/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author lenovo
 */
public class Bank {

    private String name;
    private double balanceOfBank;
    private final double maximumLendProcent;
    private final double minimumProcentOfCredit;
    private Account[] arrayOfAccounts;
    private int countOfAccounts;

    public Bank(String name, double balanceOfBank, double maximumLendProcent, double minimumProcentOfCredit) {
        this.name = name;
        this.balanceOfBank = balanceOfBank;
        this.maximumLendProcent = maximumLendProcent;
        this.minimumProcentOfCredit = minimumProcentOfCredit;
        arrayOfAccounts = new Account[1];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalanceOfBank() {
        return balanceOfBank;
    }

    public void setBalanceOfBank(double balanceOfBank) {
        this.balanceOfBank = balanceOfBank;
    }

    public double getMaximumLendProcent() {
        return maximumLendProcent;
    }

    public double getMinimumProcentOfCredit() {
        return minimumProcentOfCredit;
    }

    /*
    Метод создает аккаунт.
     */
    public void initializeAccount(Customer customer) {
        Account account = new Account(customer, generateAccountId());
        checkArrayOfAccounts(countOfAccounts);
        arrayOfAccounts[countOfAccounts++] = account;
    }

    /*
    Метод создает аккаунт и сразу добавляет в него кредитную карту
     */
    public void initializeAccountAndAddCard(Customer customer, double requestedMoneySumm, double requestedProcent) {
        Account account = new Account(customer, generateAccountId());
        checkArrayOfAccounts(countOfAccounts);
        arrayOfAccounts[countOfAccounts++] = account;
        account.initializeCreditCard(requestedMoneySumm, requestedProcent);
    }

    /*
    Метод генерирует уникальный id для аккаунта. 
    Возвращает числовое представление
     */
    public int generateAccountId() {
        Random r = new Random();
        int num;
        while (true) {
            if (countOfAccounts == 0) {
                return r.nextInt(999999999);
            } else {
                boolean isUnique;
                do {
                    num = r.nextInt(999999999);
                    isUnique = true;
                    for (int i = 0; i < countOfAccounts; i++) {
                        if (num == arrayOfAccounts[i].id) {
                            isUnique = false;
                        }
                    }
                } while (!isUnique);
            }
            return num;
        }
    }

    /*
    Метод генерирует уникальное 6-значное число для кредитной карты
     */
    public int generateCreditCardNum() {
        Random r = new Random();
        int num;
        while (true) {
            if (countAllCardsInBank() == 0) {
                num = r.nextInt(999999);
                return num;
            } else {
                boolean isUnique = true;
                do {
                    num = r.nextInt(999999);
                    for (int i = 0; i < countOfAccounts; i++) {
                        if (num == arrayOfAccounts[i].arrayOfCards[i].cardNum) {
                            isUnique = false;
                        }
                    }
                } while (!isUnique);
            }
            return num;
        }
    }

    /*
    Метод подсчитывает общее количество кредитных карт в банке
     */
    public int countAllCardsInBank() {
        int countOfCards = 0;
        for (int i = 0; i < countOfAccounts; i++) {
            countOfCards += arrayOfAccounts[i].getCountOfCards();
        }
        return countOfCards;
    }

    /*
    Метод проверяет и при необходимости расширяет массив аккаунтов банка
     */
    public void checkArrayOfAccounts(int checkedNum) {
        if (arrayOfAccounts.length <= checkedNum) {
            arrayOfAccounts = Arrays.copyOf(arrayOfAccounts, (3 * arrayOfAccounts.length / 2) + 1);
        }
    }

    public Account[] getArrayOfAccounts() {
        return arrayOfAccounts;
    }

    public void setArrayOfAccounts(Account[] arrayOfAccounts) {
        this.arrayOfAccounts = arrayOfAccounts;
    }

    public int getCountOfAccounts() {
        return countOfAccounts;
    }

    public void setCountOfAccounts(int countOfAccounts) {
        this.countOfAccounts = countOfAccounts;
    }

    class Account {

        private Customer customer;
        private CreditCard[] arrayOfCards;
        private int id;
        private int countOfCards = 0;

        public Account(Customer customer, int id) {
            this.customer = customer;
            this.id = id;
            this.arrayOfCards = new CreditCard[1];
            System.out.println("Аккаунт номер " + id + " успешно создан.");
        }

        @Override
        public String toString() {
            return "Аккаунт " + id;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
        }

        public CreditCard[] getArrayOfCards() {
            return arrayOfCards;
        }

        public void setArrayOfCards(CreditCard[] arrayOfCards) {
            this.arrayOfCards = arrayOfCards;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void showAllCards() {
            if (countOfCards == 0) {
                System.out.println("В данном аккаунте пока не создано ни одного кредитного счета");
            } else {
                for (int i = 0; i < countOfCards; i++) {
                    System.out.println(arrayOfCards[i]);
                }
            }
        }

        /*
        Метод создает кредитный счет.
         */
        public void initializeCreditCard(double requestedMoneySumm, double requestedProcent) {
            if (moneySummIsValid(requestedMoneySumm) && creditProcentIsValid(requestedProcent)) {
                CreditCard card = new CreditCard(this.customer.getName(), this.customer.getSurName(),
                        requestedMoneySumm, requestedProcent, generateCreditCardNum());
                checkArrayOfCards(countOfCards);
                arrayOfCards[countOfCards++] = card;
            }

        }

        /*
        Метод проверяет и при необходимости расширяет массив кредитных карточек
         */
        public void checkArrayOfCards(int checkedNum) {
            if (arrayOfCards.length <= checkedNum) {
                arrayOfCards = Arrays.copyOf(arrayOfCards, (3 * arrayOfCards.length / 2) + 1);
            }
        }

        /*
        Метод проверяет запращиваемую сумму денег клиента.
        Если она больше чем опредленный процент от баланса банка,
        то метод выбрасывает false. В ином случае - true
         */
        public boolean moneySummIsValid(double requestedSumm) {
            while (true) {
                if (requestedSumm <= balanceOfBank * maximumLendProcent) {
                    return true;
                } else if (requestedSumm > balanceOfBank * maximumLendProcent) {
                    throw new IllegalArgumentException("Требуемая сумма денег слишком велика");
                }
            }
        }

        /*
        Метод проверяет запращиваемый пользователем процент под кредит.
        Если он меньше чем минимальный процент под кредит банка,
        то метод выбрасывает false. В ином случае - true.
         */
        public boolean creditProcentIsValid(double requestedProcent) {
            while (true) {
                if (requestedProcent >= minimumProcentOfCredit) {
                    return true;
                } else if (requestedProcent < minimumProcentOfCredit) {
                    throw new IllegalArgumentException("Слишком малый процент под кредит");
                }
            }
        }

        public int getCountOfCards() {
            return countOfCards;
        }

        public void setCountOfCards(int countOfCards) {
            this.countOfCards = countOfCards;
        }

        class CreditCard {

            private String nameOfOwner;
            private String surNameOfOwner;
            private int cardNum;
            private double moneyInCredit;
            private double creditProcent;
            private double moneyBack;
            private double balanceOfCard;
            private boolean cardIsUnlocked;

            private CreditCard(String nameOfOwner, String surNameOfOwner, double moneyInCredit, double creditProcent, int cardNum) {
                this.nameOfOwner = nameOfOwner;
                this.surNameOfOwner = surNameOfOwner;
                this.moneyInCredit = moneyInCredit;
                this.creditProcent = creditProcent / 100;
                this.cardNum = cardNum;
                this.balanceOfCard = moneyBack - (moneyInCredit + (moneyInCredit * this.creditProcent));
                this.cardIsUnlocked = true;
                System.out.println("Кредитная карточка под номером " + cardNum + " успешно создана");

            }

            @Override
            public String toString() {
                return "Кредитная карта " + cardNum + ": Баланс карты: " + balanceOfCard;
            }

            public String getNameOfOwner() {
                return nameOfOwner;
            }

            public void setNameOfOwner(String nameOfOwner) {
                this.nameOfOwner = nameOfOwner;
            }

            public String getSurNameOfOwner() {
                return surNameOfOwner;
            }

            public void setSurNameOfOwner(String surNameOfOwner) {
                this.surNameOfOwner = surNameOfOwner;
            }

            public int getCardNum() {
                return cardNum;
            }

            public void setCardNum(int cardNum) {
                this.cardNum = cardNum;
            }

            public double getMoneyInCredit() {
                return moneyInCredit;
            }

            public void setMoneyInCredit(double moneyInCredit) {
                this.moneyInCredit = moneyInCredit;
            }

            public double getCreditProcent() {
                return creditProcent;
            }

            public void setCreditProcent(double creditProcent) {
                this.creditProcent = creditProcent;
            }

            public double getMoneyBack() {
                return moneyBack;
            }

            public void setMoneyBack(double moneyBack) {
                this.moneyBack = moneyBack;
                this.balanceOfCard += moneyBack;
            }

            public double getBalanceOfCard() {
                return balanceOfCard;
            }

            public void setBalanceOfCard(double balanceOfCard) {
                this.balanceOfCard = balanceOfCard;
            }

        }
    }

}
