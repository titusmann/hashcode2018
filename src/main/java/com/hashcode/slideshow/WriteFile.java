package com.hashcode.slideshow;

import com.haschcode.pizza.Car;
import com.haschcode.pizza.Ride;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteFile {


//    public static void imprimePesos(Pizza pizza) throws FileNotFoundException, IOException {
//        String file = "C:\\Users\\fjbelaza\\Documents\\Pizza-Hashcode-Example-2018-develop\\Pizza-Hashcode-Example-2018\\src\\main\\java\\resources\\output.out";
//
//        FileWriter f = new FileWriter(file);
//        BufferedWriter b = new BufferedWriter(f);
//
//        int columnas = pizza.getMaxColumn();
//        HashMap<Integer, Integer> pesos = pizza.getWeightOfCells();
//
//        int i = 0;
//        for (Integer peso : pesos.values()) {
//            b.write(peso + "\t");
//            i++;
//            if (i >= columnas) {
//                b.newLine();
//                i = 0;
//            }
//        }
//        b.close();
//    }

    public static void imprimeFichero(List<Slide> slideshow, String file) throws FileNotFoundException, IOException {
        FileWriter f = new FileWriter(file, false);
        BufferedWriter b = new BufferedWriter(f);

        // Primera linea: Numero de slides
        int size = slideshow.size();

        System.out.println("Tamaño: " + size);
        b.write(size);
        b.newLine();

        for(Slide slide : slideshow){
            b.write(slide.toString());
            b.newLine();
        }

        b.close();
    }

    public static void imprimeCortes(List<Car> cars, String file) throws FileNotFoundException, IOException {

        FileWriter f = new FileWriter(file, false);
        BufferedWriter b = new BufferedWriter(f);


        for (Car car : cars) {
            if (car.getRides() != null && !car.getRides().isEmpty()) {
                b.write(car.getRides().size() + "\t");
                if (car.getRides() != null && !car.getRides().isEmpty()) {
                    for (Ride rides : car.getRides()) {
                        b.write(rides.getId() + "\t");
                    }
                }
            } else {
                // SIn rides
                b.write("0");

            }
            b.newLine();
        }
        b.close();
    }

}
