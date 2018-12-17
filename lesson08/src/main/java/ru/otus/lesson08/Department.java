package ru.otus.lesson08;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private List<ATM> atms;

    public Department(int valueOfAtm){
        this.atms = new ArrayList<>(valueOfAtm);
        for (int i = 0; i < valueOfAtm; i++) {
            this.atms.add(new ATM());
        }
    }

    public void resetDefaultStatus(){
        atms.forEach(ATM::setDefaultStatus);
    }

    public void backupAllAtm(){
        atms.forEach(ATM::backup);
        System.out.println("Произведено сохранения резервных копий банкоматов");
    }

    public void restoreAllAtm(){
        atms.forEach(ATM::restore);
        System.out.println("Произведено восстановление банкоматов");
    }


    public long getBalance(){
        return atms
                .stream()
                .mapToLong(ATM::getBalance)
                .sum();
    }

    public List<ATM> getAtms() {
        return new ArrayList<>(atms);
    }

    public ATM getAtm(int index) {
        return atms.get(index);
    }
}
