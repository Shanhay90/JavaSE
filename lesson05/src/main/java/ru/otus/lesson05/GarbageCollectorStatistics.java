package ru.otus.lesson05;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GarbageCollectorStatistics {

    private Long time;
    private Long count;

}
