package ru.otus.lesson07.banknotes;

import java.lang.annotation.*;

@Retention(value= RetentionPolicy.RUNTIME)
@Target(value=ElementType.FIELD)
public @interface StoredNominal {
    int storedNominal();
}
