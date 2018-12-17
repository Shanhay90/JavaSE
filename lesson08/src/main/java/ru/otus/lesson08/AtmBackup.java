package ru.otus.lesson08;

import ru.otus.lesson08.banknotes.Banknote;

import java.util.HashMap;
import java.util.Map;

public class AtmBackup {
  private final Map<Banknote, Integer> storageBackup;

  public AtmBackup(Map<Banknote, Integer> stateToSave){
      this.storageBackup = new HashMap<>(stateToSave);
  }

  public Map<Banknote, Integer> restore(){
      return storageBackup;
  }
}
