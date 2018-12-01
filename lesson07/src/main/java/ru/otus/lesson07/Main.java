package ru.otus.lesson07;

import ru.otus.lesson07.banknotes.Banknote;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ATM atm  = new ATM().setAtmBalance(100, 10000, 10000, 10000, 10000);
        System.out.println(atm.getAtmBalance());
        List<Banknote> money = atm.getMoney(1750);
        System.out.println(money.toString());
        System.out.println(atm.getAtmBalance());
        atm.putMoneyInAtm(Banknote.FIFTY);
        atm.putMoneyInAtm(Arrays.asList(Banknote.FIFTY, Banknote.ONEHUNDRED));
        System.out.println(atm.getAtmBalance());
    }
}
