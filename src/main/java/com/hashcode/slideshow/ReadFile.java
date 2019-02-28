package com.hashcode.slideshow;

import com.haschcode.pizza.Car;
import com.haschcode.pizza.City;
import com.haschcode.pizza.Ride;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadFile {


    public static ArrayList<Photo> getPhotos(String file) throws FileNotFoundException, IOException {

        System.out.println("Leyendo fichero");

        String cadena;
        ArrayList<Photo> photos = new ArrayList<>();

        int cont = -1;
        int total;

        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);

        while ((cadena = b.readLine()) != null) {
            if(cont == -1) {
                total = Integer.parseInt(cadena);
            } else {
                String[] contFila = cadena.split(" ");
                char Orientacion = contFila[0].charAt(0);
                int numTags = Integer.parseInt(contFila[1]);

                ArrayList<String> listTags = new ArrayList<>();
                for(int i=0; i < numTags; i++){
                    listTags.add(contFila[2+i]);
                }
                photos.add(new Photo(Orientacion == 'H', listTags, cont));
            }
            cont++;
        }

        return photos;
    }


}
