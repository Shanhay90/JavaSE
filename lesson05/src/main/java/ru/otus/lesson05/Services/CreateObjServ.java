package ru.otus.lesson05.Services;

import ru.otus.lesson05.Benchmark;

import java.util.ArrayList;
import java.util.List;

public class CreateObjServ {

        private int counter = 0;

        public void createObj(long size, Benchmark benchmark) {
            List<Object> list = new ArrayList<>();

            benchmark.startTime();
            benchmark.startMonitoring();

            while (true) {

                for (int i = 0; i < size; i++) {
                    list.add(new Object[1024]);
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < size / 2; j++) {
                    list.remove(j);
                }
                this.counter++;

            }


        }

    }

