package ru.otus.lesson08;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private List<ATM> atms;

    private Department(){
    }

    public Department(int valueOfAtm){
        this.atms = new ArrayList<>(valueOfAtm);
        for (int i = 0; i < valueOfAtm; i++) {
            this.atms.add(new ATM());
        }
    }

    public void setDepartmentDefaultStatus(){
        atms.forEach(ATM::setDefaultStatus);
    }


    public long getBalance(){
        return atms
                .stream()
                .mapToLong(ATM::getBalance)
                .sum();
    }

    public List<ATM> getAtms() {
        return atms;
    }
}
