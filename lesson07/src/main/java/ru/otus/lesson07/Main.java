package ru.otus.lesson07;

import ru.otus.lesson07.banknotes.Banknote;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ATM atm = new ATM();
        System.out.println(atm.getBalance());
        atm.setDefaultCapacity(100);
        atm = atm.setDefaultStatus();
        System.out.println(atm.getBalance());

        List<Banknote> money = atm.getMoney(6650);
        System.out.println(money.toString());
        System.out.println(atm.getBalance());
        atm.putMoneyInAtm(Banknote.FIVEHUNDRED);
        System.out.println(atm.getBalance());
        atm.putMoneyInAtm(Arrays.asList(Banknote.FIVEHUNDRED, Banknote.ONEHUNDRED));
        System.out.println(atm.getBalance());
        atm.putMoneyInAtm(Banknote.FIVEHUNDRED, Banknote.ONEHUNDRED);
        System.out.println(atm.getBalance());

        atm.setDefaultCapacity(1);
        atm = atm.setDefaultStatus();
        atm.putMoneyInAtm(Banknote.FIFTY);
        System.out.println(atm.getBalance());
        List<Banknote> check = atm.getMoney(6700);
        System.out.println(check);
    }
}
