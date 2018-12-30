package ru.otus.lesson08;

import ru.otus.lesson08.banknotes.Banknote;

import java.util.*;

public class ATM {

    private AtmRestoreService atmRestoreService = new AtmRestoreService();
    private Map<Banknote, Integer> banknotesStorage;
    private int defaultCapacity = 500;


    public ATM() {
        this.banknotesStorage = new HashMap<>();
        setDefaultStatus();
        backup();
    }

    public void setDefaultStatus() {
        for (Banknote banknote : Banknote.values()) {
            banknotesStorage.put(banknote, defaultCapacity);
        }
    }


    public void backup(){
        atmRestoreService.saveBackup(new AtmBackup(this.banknotesStorage));
    }

    public void restore(){
        this.banknotesStorage = atmRestoreService.restoreFromBackup().restore();
    }

    public void setDefaultCapacity(int defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
    }

    public long getBalance() {
        long balance = 0;
        for (Map.Entry<Banknote, Integer> banknoteEntry : banknotesStorage.entrySet()) {
            balance += banknoteEntry.getKey().getNominal() * banknoteEntry.getValue();
        }
        return balance;
    }

    private void checkRequestSum(int sum) throws IllegalArgumentException {
        boolean isRealSum = sum > 0;
        boolean isLowThanMax = sum <= getBalance();
        int minNominal = banknotesStorage
                .keySet()
                .stream()
                .mapToInt(Banknote::getNominal)
                .min()
                .getAsInt();
        boolean isMultipleMin = sum % minNominal == 0;
        if (!(isRealSum && isLowThanMax && isMultipleMin)) {
            throw new IllegalArgumentException("ОШИБКА: Банкоман не может выдать запрашиваемую сумму");
        }
    }

    private void checkIssuedSum(int sum, List<Banknote> money, Map<Banknote, Integer> storageBefore) throws IllegalArgumentException {
        int issuedSum = money.stream().mapToInt(Banknote::getNominal).sum();
        if (issuedSum != sum) {
            this.banknotesStorage = storageBefore;
            throw new IllegalStateException("ОШИБКА: Банкоман не может выдать запрашиваемую сумму");

        }
    }

    private int getCountBanknotesInStorage(Integer sum, Banknote banknote) {
        int count = sum / banknote.getNominal();
        int available = banknotesStorage.get(banknote);
        return count > available ? available : count;
    }


    private void fillMoneyList(List<Banknote> money, Integer sum, Banknote banknote, Integer available) {
        for (int i = 0; i < available; i++) {
            money.add(banknote);
            banknotesStorage.put(banknote, banknotesStorage.get(banknote) - 1);
        }
    }

    public List<Banknote> getMoney(int sum) throws IllegalArgumentException {
        checkRequestSum(sum);
        Map<Banknote, Integer> storageBefore = new HashMap<>(banknotesStorage);
        Integer tempSum = sum;
        List<Banknote> money = new ArrayList<>();
        Banknote[] availableBanknotes = Banknote.values();
        Arrays.sort(availableBanknotes, Banknote.banknoteComparator);
        for (Banknote banknote : availableBanknotes) {
            int issuedBanknotes = getCountBanknotesInStorage(tempSum, banknote);
            fillMoneyList(money, tempSum, banknote, issuedBanknotes);
            tempSum -= banknote.getNominal() * issuedBanknotes;
        }
        checkIssuedSum(sum, money, storageBefore);
        return money;
    }

    public void putMoneyInAtm(Banknote... banknotes) {
        for (Banknote banknote : banknotes) {
            putMoneyInAtm(banknote);
        }
    }

    public void putMoneyInAtm(Collection<Banknote> banknotes) {
        for (Banknote banknote : banknotes) {
            putMoneyInAtm(banknote);
        }
    }

    public void putMoneyInAtm(Banknote banknote) {
        int banknotesIn = banknotesStorage.get(banknote);
        if (banknotesIn < 1000) {
            banknotesStorage.put(banknote, banknotesIn + 1);
        } else {
            throw new IllegalStateException("ОШИБКА: Хранилище полностью заполненно");
        }
    }


}