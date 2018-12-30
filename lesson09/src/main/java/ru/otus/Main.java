package ru.otus;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Car car = new Car();
        car.setBrand("Ford");
        car.setModel("Mustang");
        Engine engine = new Engine(5.0f, 250);
        car.setEngine(engine);
        List<Wheel> wheels = new ArrayList<>(4);
        wheels.add(new Wheel(false, 18));
        wheels.add(new Wheel(false, 18));
        wheels.add(new Wheel(false, 18));
        wheels.add(null);
        wheels.add(new Wheel(false, 18));
        car.setWheels(wheels);
        MyJSON json = new MyJSON();
        System.out.println(json.toJSON(car));

        System.out.println(new Gson().toJson(car));
    }




}
