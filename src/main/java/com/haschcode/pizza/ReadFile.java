package com.haschcode.pizza;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadFile {


    public static City muestraContenido(String file) throws FileNotFoundException, IOException {
        String cadena;

        int maxColumn = 0;
        int maxRows = 0;
        int numRides = 0;
        int numCars = 0;
        int bonus = 0;
        int maxTime = 0;
        City city = null;
        List<Car> cars = new ArrayList<>();
        AtomicInteger row = new AtomicInteger(0);

        int cont = -1;

        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while ((cadena = b.readLine()) != null) {
            //System.out.println(cadena);
            if (cont == -1) {
                maxRows = Integer.parseInt(cadena.split(" ")[0]);
                maxColumn = Integer.parseInt(cadena.split(" ")[1]);
                numCars = Integer.parseInt(cadena.split(" ")[2]);
                numRides = Integer.parseInt(cadena.split(" ")[3]);
                bonus = Integer.parseInt(cadena.split(" ")[4]);
                maxTime = Integer.parseInt(cadena.split(" ")[5]);
                new ArrayList<>(numCars);
                city = new City(maxColumn, maxRows, cars, bonus, maxTime);
            } else {
                city.addRide(new Ride(Integer.parseInt(cadena.split(" ")[1]),
                        Integer.parseInt(cadena.split(" ")[0]),
                        Integer.parseInt(cadena.split(" ")[2]),
                        Integer.parseInt(cadena.split(" ")[3]),
                        Integer.parseInt(cadena.split(" ")[4]),
                        Integer.parseInt(cadena.split(" ")[5]),
                        cont
                ));

            }
            cont++;
        }

        for (int i = 0; i < numCars; i++) {
            cars.add(new Car());
        }


        b.close();
        System.out.print(city.toString());
        return city;
    }


}
