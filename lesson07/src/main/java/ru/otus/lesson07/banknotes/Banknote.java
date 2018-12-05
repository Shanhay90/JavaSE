package ru.otus.lesson07.banknotes;

import java.util.Comparator;

public enum Banknote {
    FIVETHOUSAND(5000),
    ONETHOUSAND(1000),
    FIVEHUNDRED(500),
    ONEHUNDRED(100),
    FIFTY(50);

    private final int nominal;

    Banknote(int nominal) {
        this.nominal = nominal;
    }

    public int getNominal() {
        return nominal;
    }

    public static Comparator<Banknote> banknoteComparator = Comparator.comparingInt(Banknote::getNominal).reversed();
}
