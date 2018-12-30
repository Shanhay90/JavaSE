package ru.otus;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Wheel {
    private boolean isWinterType;
    private int radius;
}
