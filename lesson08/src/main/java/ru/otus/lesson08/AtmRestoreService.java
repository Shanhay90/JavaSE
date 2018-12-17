package ru.otus.lesson08;

public class AtmRestoreService {
    private AtmBackup backup;

    public void saveBackup(AtmBackup backup){
        this.backup = backup;
    }

    public AtmBackup restoreFromBackup(){
        return backup;
    }
}
