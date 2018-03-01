package com.haschcode.pizza;

import com.haschcode.pizza.model.Pizza;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadFile {


    public static Pizza muestraContenido(String file) throws FileNotFoundException, IOException {
        String cadena;
        HashMap<Integer, Boolean> ingredients = new HashMap<>();
        int maxColumn = 0;
        int maxRows  = 0;
        int maxCellsSlice  = 0;
        int minIngredientSlice  = 0;
        AtomicInteger row = new AtomicInteger(0);

        int cont = -1;

        FileReader f = new FileReader(file);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            //System.out.println(cadena);
            if (cont == -1) {
                maxRows = Integer.parseInt(cadena.split(" ")[0]);
                maxColumn = Integer.parseInt(cadena.split(" ")[1]);
                minIngredientSlice = Integer.parseInt(cadena.split(" ")[2]);
                maxCellsSlice = Integer.parseInt(cadena.split(" ")[3]);
            } else {
                Arrays.stream(cadena.split("")).forEach(i -> ingredients.put(row.getAndIncrement(), i.equals("T")));
            }
            cont++;
        }
        

        b.close();
        return  new Pizza(maxColumn, maxRows, maxCellsSlice, minIngredientSlice, ingredients);
    }

}
