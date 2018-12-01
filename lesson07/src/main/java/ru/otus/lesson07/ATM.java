package ru.otus.lesson07;

import ru.otus.lesson07.banknotes.Banknote;
import ru.otus.lesson07.banknotes.ListName;
import ru.otus.lesson07.banknotes.StoredNominal;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ATM {

    @StoredNominal(storedNominal = 5000)
    private List<Banknote> fiveThousandList;
    @StoredNominal(storedNominal = 1000)
    private List<Banknote> oneThousandList;
    @StoredNominal(storedNominal = 500)
    private List<Banknote> fiveHundredList;
    @StoredNominal(storedNominal = 100)
    private List<Banknote> oneHundredList;
    @StoredNominal(storedNominal = 50)
    private List<Banknote> fiftyList;

    public ATM() {
        this.fiveThousandList = new ArrayList<>();
        this.oneThousandList = new ArrayList<>();
        this.fiveHundredList = new ArrayList<>();
        this.oneHundredList = new ArrayList<>();
        this.fiftyList = new ArrayList<>();
    }

    @ListName(listName = "fiveThousandList")
    private void putInFiveThousandList(Banknote banknote) {
        fiveThousandList.add(banknote);
    }

    @ListName(listName = "oneThousandList")
    private void putInOneThousandList(Banknote banknote) {
        oneThousandList.add(banknote);
    }

    @ListName(listName = "fiveHundredList")
    private void putInFiveHundredListList(Banknote banknote) {
        fiveHundredList.add(banknote);
    }

    @ListName(listName = "oneHundredList")
    private void putInOneHundredListList(Banknote banknote) {
        oneHundredList.add(banknote);
    }

    @ListName(listName = "fiftyList")
    private void putInFiftyListList(Banknote banknote) {
        fiftyList.add(banknote);
    }


    public ATM setAtmBalance(int quantityOf5000, int quantityOf1000, int quantityOf500, int quantityOf100, int quantityOf50) {
        for (int i = 0; i < quantityOf5000; i++) {
            this.fiveThousandList.add(Banknote.FIVETHOUSAND);
        }

        for (int i = 0; i < quantityOf1000; i++) {
            this.oneThousandList.add(Banknote.ONETHOUSAND);
        }
        for (int i = 0; i < quantityOf500; i++) {
            this.fiveHundredList.add(Banknote.FIVEHUNDRED);
        }

        for (int i = 0; i < quantityOf100; i++) {
            this.oneHundredList.add(Banknote.ONEHUNDRED);
        }
        for (int i = 0; i < quantityOf50; i++) {
            this.fiftyList.add(Banknote.FIFTY);
        }
        return this;
    }

    public long getAtmBalance() {
        long balance = fiveThousandList.stream().mapToInt(Banknote::getNominal).sum();
        balance += oneThousandList.stream().mapToInt(Banknote::getNominal).sum();
        balance += fiveHundredList.stream().mapToInt(Banknote::getNominal).sum();
        balance += oneHundredList.stream().mapToInt(Banknote::getNominal).sum();
        balance += fiftyList.stream().mapToInt(Banknote::getNominal).sum();
        return balance;
    }

    private void checkRequestSum(int sum) throws IllegalArgumentException {
        boolean isRealSum = sum > 0;
        boolean isLowThanMax = sum <= getAtmBalance();
        boolean isMultipleFifty = sum % Banknote.FIFTY.getNominal() == 0;
        if (!(isRealSum && isLowThanMax && isMultipleFifty)) {
            throw new IllegalArgumentException("Запрашиваямая сумма некорректна");
        }
    }


    private int getBanknotesFromList(Integer sum, List<Banknote> list, Banknote banknote) {
        int count = sum / banknote.getNominal();
        int available;
        if (list.size() - count <= 0) {
            available = count + (list.size() - count);
        } else {
            available = count;
        }
        return available > 0 ? available : 0;
    }


    private void fillMoneyList(List<Banknote> money, List<Banknote> banknotesList, Integer sum, Banknote banknote) {
        Integer available = getBanknotesFromList(sum, banknotesList, banknote);
        for (int i = 0; i < available; i++) {
            money.add(banknotesList.get(i));
            banknotesList.remove(i);
        }
    }

    public List<Banknote> getMoney(int sum) throws IllegalArgumentException {
        checkRequestSum(sum);
        List<Banknote> money = new ArrayList<>();
        fillMoneyList(money, fiveThousandList, sum, Banknote.FIVETHOUSAND);
        sum = sum - getBanknotesFromList(sum, fiveThousandList, Banknote.FIVETHOUSAND) * Banknote.FIVETHOUSAND.getNominal();
        fillMoneyList(money, oneThousandList, sum, Banknote.ONETHOUSAND);
        sum = sum - getBanknotesFromList(sum, oneThousandList, Banknote.ONETHOUSAND) * Banknote.ONETHOUSAND.getNominal();
        fillMoneyList(money, fiveHundredList, sum, Banknote.FIVEHUNDRED);
        sum = sum - getBanknotesFromList(sum, fiveHundredList, Banknote.FIVEHUNDRED) * Banknote.FIVEHUNDRED.getNominal();
        fillMoneyList(money, oneHundredList, sum, Banknote.ONEHUNDRED);
        sum = sum - getBanknotesFromList(sum, oneHundredList, Banknote.ONEHUNDRED) * Banknote.ONEHUNDRED.getNominal();
        fillMoneyList(money, fiftyList, sum, Banknote.FIFTY);
        return money;
    }


    public void putMoneyInAtm(Collection<Banknote> banknotes)throws InvocationTargetException, IllegalAccessException{
        for (Banknote banknote : banknotes) {
            putMoneyInAtm(banknote);
        }

    }

    public void putMoneyInAtm(Banknote banknote) throws InvocationTargetException, IllegalAccessException {
            Class thisAtm = this.getClass();
        Field[] fields = thisAtm.getDeclaredFields();
        for (Field field : fields) {
            int a = field.getAnnotation(StoredNominal.class).storedNominal();
            int b = banknote.getNominal();
            if (a == b) {
                boolean isAccessible = field.isAccessible();
                field.setAccessible(true);
                storeMoney(thisAtm, field, banknote);
                field.setAccessible(isAccessible);
            }
        }

    }


    private void storeMoney(Class atn, Field field, Banknote banknote) throws InvocationTargetException, IllegalAccessException {
        for (Method method : atn.getDeclaredMethods()) {
            if (method.isAnnotationPresent(ListName.class) && method.getAnnotation(ListName.class).listName().equals(field.getName())) {
                boolean isAccessible = method.isAccessible();
                try {
                        method.setAccessible(true);
                        Class[] d = method.getParameterTypes();
                        method.invoke(this, banknote);

                } finally {
                    method.setAccessible(isAccessible);
                }
            }
        }
    }
}
