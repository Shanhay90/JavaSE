package ru.otus;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Car car = new Car();
        car.setBrand("Ford");
        car.setModel("Mustang");
        car.setLiteral('a');
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
        String myJson  = json.toJSON(car).toString();
        System.out.println(myJson);
        Car car1 = new Gson().fromJson(myJson, Car.class);
        String gson  = new Gson().toJson(car1);

        System.out.println(gson);

        System.out.println(gson.equalsIgnoreCase(myJson));
        System.out.println(car.equals(car1));
        System.out.println(gson);
        System.out.println(new MyJSON().toJSON(123));
        System.out.println(new MyJSON().toJSON("ABC"));
        System.out.println(new MyJSON().toJSON(null));
        System.out.println(new MyJSON().toJSON(true));





    }




}
