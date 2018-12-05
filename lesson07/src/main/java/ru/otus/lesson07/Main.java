package ru.otus.lesson07;

import ru.otus.lesson07.banknotes.Banknote;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ATM atm  = new ATM();
        System.out.println(atm.getAtmBalance());
        atm.setDefaultCapacity(100);
        atm = atm.setDefaultStatus();
        System.out.println(atm.getAtmBalance());
        List<Banknote> money = atm.getMoney(6650);
        System.out.println(money.toString());
        System.out.println(atm.getAtmBalance());
        atm.putMoneyInAtm(Banknote.FIVEHUNDRED);
        System.out.println(atm.getAtmBalance());
        atm.putMoneyInAtm(Arrays.asList(Banknote.FIVEHUNDRED, Banknote.ONEHUNDRED));
        System.out.println(atm.getAtmBalance());
        atm.putMoneyInAtm(Banknote.FIVEHUNDRED, Banknote.ONEHUNDRED);
        System.out.println(atm.getAtmBalance());
    }
}
