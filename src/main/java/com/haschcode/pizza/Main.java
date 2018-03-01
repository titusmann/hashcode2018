package com.haschcode.pizza;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import static java.lang.Math.*;

public class Main {

//    public static List<String> comb;

    public static City city;


//    public static List<Slice> slices;


//    public static AtomicInteger contador = new AtomicInteger(0);

    public static void main(String[] args) {
        city = null;
        List<String> arrayFile = new ArrayList<>();
        arrayFile.add("a_example");
        arrayFile.add("b_should_be_easy");
        arrayFile.add("c_no_hurry");
        arrayFile.add("d_metropolis");
        arrayFile.add("e_high_bonus");
        for (String file : arrayFile) {
            final Comparator<Car> comp = (c1, c2) -> Integer.compare(c1.getTime(), c2.getTime());

            HashMap<Integer, Integer> weightCell = new HashMap<>();
            try {
                city = ReadFile.muestraContenido(args[0] + "\\" + file + ".in");

                // Seleccionar el vehículo disponible antes.
                boolean viajeRealizado = false;
                do {
                    viajeRealizado = false;
                    int contador = 0;
                    Car car = city.getCars().stream().min(comp).get();

                    if (car.getTime() < city.getMaxTime()) {
                        viajeRealizado = true;
                        List<Ride> ridesMin = new ArrayList<>();
                        int tiempoRidesMin = 99999999;

                        double baremoMax = 0.0;
                        Ride rideFinal = null;
                        for(Ride ride : city.getRides()){
                            double distanciaTotal = sqrt(pow(distancia(ride,car),2) + pow(tiempoDeRide(ride),2));
                            int puntuacion = getPuntuacion(ride,llegaATiempoParaBonus(ride,car));

                            double baremo = (double) puntuacion / (distanciaTotal);

                            if(baremo > baremoMax ){
                                baremoMax = baremo;
                                rideFinal = ride;
                            } else if (baremo == baremoMax){
                                if (getPuntuacion(ride,llegaATiempoParaBonus(ride,car)) > getPuntuacion(rideFinal,llegaATiempoParaBonus(ride,car))){
                                    rideFinal = ride;
                                }
                            }
                        }


                        //Ride rideFinal = ridePuntuacion.size()>0?ridePuntuacion.get(0):null;
                        if (rideFinal != null) {
                            city.removeRide(rideFinal);
                            car.addRide(rideFinal);
                            car.setColumnPos(rideFinal.getColumnEnd());
                            car.setRowPos(rideFinal.getRowEnd());

                            car.setTime(car.getTime() + distancia(rideFinal, car) + tiempoDeRide(rideFinal));
                        } else {
                            car.setTime(city.getMaxTime() + 1);
                        }

                        // Una vez seleccionado, calcular la pos y las coordenadas del car.

                    }
                } while (viajeRealizado);


            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                WriteFile.imprimeCortes(city.getCars(), args[0] + "\\" + file + ".out");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static int distancia(Ride ride, Car car) {
        int tiempoMinimo = ride.getTimeInitial() - car.getTime();
        int tiempoDistancia = abs(car.getColumnPos() - ride.getColumnInitial()) + abs(car.getRowPos() - ride.getRowInitial());

        return max(tiempoDistancia, tiempoMinimo);
    }

    public static int tiempoDeRide(Ride ride) {
        return abs(ride.getRowEnd() - ride.getRowInitial()) + abs(ride.getColumnEnd() - ride.getColumnInitial());
    }

    public static int getPuntuacion(Ride ride, boolean conBonus) {
        int puntuacion = abs(ride.getColumnEnd() - ride.getColumnInitial()) + abs(ride.getRowEnd() - ride.getRowInitial());
        return conBonus ? puntuacion + city.getBonus() : puntuacion;
    }

    public static boolean llegaATiempoParaBonus(Ride ride, Car car) {
        int tiempoEnLlegar = abs(car.getColumnPos() - ride.getColumnInitial()) + abs(car.getRowPos() - ride.getRowInitial());
        return !(car.getTime() + tiempoEnLlegar > ride.getTimeInitial());
    }

    public static boolean checkFinal(Ride ride, Car car, int tiempo) {
        return ride.getTimeEnd() >= car.getTime() + tiempo + getPuntuacion(ride, false);
    }

}

//System.out.println(pizza.toString());
//        HashMap<Integer, Integer> weightCells = new HashMap<>();
//
//        System.out.println("Leido el mapa. Calculando pesos.");
//        for (Integer i : pizza.getMapIngredient().keySet()) {
//            Integer weight = weightOfCell(i);
//            if(weight > 0) {
//                weightCells.put(i, weight);
//            }
//            //System.out.println(weight);
//            if(i%1000==0){
//                System.out.println("Calculado " + i + " pesos de " + pizza.getMapIngredient().size());
//            }
//        }
//        System.out.println("Calculado pesos. Ordenamos el Array...");
//
//        //HAY QUE DEFINIR EL SORTEDWEIGTHCELLS PARA UE SIEMPRE ESTE ORDENADO, INCLUSO AL ACTUALIZAR VALOR
//        Map<Integer, Integer> sortedWeigthCells = weightCells.entrySet().stream().sorted(Map.Entry.comparingByValue())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//        pizza.setWeigthOfCellsSorted(sortedWeigthCells);
//
//        System.out.println("Array ordenado. Cortando.");
//
//        int numCasillasTotales = pizza.getMaxColumn()*pizza.getMaxRows();
//        do {
//            boolean cortado = false;
//            //Realizamos el corte del primer trozo en el array (supuestamente, el mas necesitado)
//            for (String combi : comb) {
//                if(!cortado) {
//                    Integer xmax = Integer.parseInt(combi.split("-")[0]);
//                    Integer ymax = Integer.parseInt(combi.split("-")[1]);
//
//                    if (calcularCortes(xmax, ymax, pizza.getXpos((Integer) sortedWeigthCells.keySet().toArray()[0]), pizza.getYpos((Integer) sortedWeigthCells.keySet().toArray()[0])) > 0) {
//                        //Esta combinatoria tiene cortes posibles. Se realiza el primero.
//                        realizarPrimerCorte(xmax, ymax, pizza.getXpos((Integer) sortedWeigthCells.keySet().toArray()[0]), pizza.getYpos((Integer) sortedWeigthCells.keySet().toArray()[0]));
//                        cortado = true;
//                    }
//                    //En el Corte, actualizará también los valores de las casillas de alrededor.
//                }
//            }
//            sortedWeigthCells = pizza.getWeigthOfCellsSorted();
//            if(contador.get()%100==0) {
//                System.out.println("Quedan " + sortedWeigthCells.size() + " casillas de " + numCasillasTotales +". Corte num:" + contador.get());
//            }
//
//        } while (sortedWeigthCells.size() > 0);
//
//        // TODO: Cambiar el Hashmap a un Sortmap ordenado por values.
//        // TODO: Recalcular el peso solo de las casillas a pizza.getMaxCellsSlice() de distancia de la zona recortada.
//    public static List<String> combinaciones(){
//        List<String> result = new ArrayList<>();
//        Integer max = pizza.getMaxCellsSlice();
//        Integer min = pizza.getMinIngredientSlice()*2;
//
//        int x;
//        int y;
//
//        for(x = 1; x <= max ; x++) {
//            for (y = 1; y <= max; y++) {
//                if(x*y <= max && x*y >= min){
//                    result.add(x+"-"+y);
//                    //System.out.println(x+"-"+y);
//                }
//            }
//        }
//
//        Collections.sort(result, Comparator.comparingInt(c -> Integer.parseInt(c.split("-")[0]) * Integer.parseInt(c.split("-")[1])));
//        Collections.reverse(result);
//
//        return result;
//    }
//
//    public static Integer weightOfCell(Integer i){
//        Integer result = 0;
//
//        Integer coorx = pizza.getXpos(i);
//        Integer coory = pizza.getYpos(i);
//
//        for(String combinatoria : comb){
//            Integer xmax = Integer.parseInt(combinatoria.split("-")[0]);
//            Integer ymax = Integer.parseInt(combinatoria.split("-")[1]);
//
//            result += calcularCortes(xmax,ymax,coorx,coory);
//        }
//        return result;
//    }
//
//
//
//    /**
//     * Devuelve el peso con respecto a esa combinatoria.
//     * @param avancex
//     * @param avancey
//     * @param coorx
//     * @param coory
//     * @return
//     */
//    public static Integer calcularCortes(Integer avancex, Integer avancey, Integer coorx, Integer coory){
//        Integer resultado = 0;
//        Slice slice;
//        for(int x=0; x<avancex; x++) {
//            for(int y=0; y<avancey; y++) {
//                slice = new Slice(coorx-x, coory-y, coorx-x+(avancex-1), coory-y+(avancey-1));
//                if ( slice.validateSlice(pizza) ) {
//                    resultado++;
//                }
//            }
//        }
//        return resultado;
//    }
//
//    public static void realizarPrimerCorte(Integer avancex, Integer avancey, Integer coorx, Integer coory){
//        Integer resultado = 0;
//        Slice slice;
//        for(int x=0; x<avancex; x++) {
//            for(int y=0; y<avancey; y++) {
//                slice = new Slice(coorx-x, coory-y, coorx-x+(avancex-1), coory-y+(avancey-1));
//                if ( slice.validateSlice(pizza) ) {
//                    //Realizamos el corte.
//                    slices.add(slice);
//                    contador.incrementAndGet();
//                    //System.out.println("Cortecillo num: " + contador.getAndIncrement());
//
//                    //Anulamos las casillas.
//                    for(int x2 = coorx-x; x2 <= coorx-x+(avancex-1); x2++ ){
//                        for(int y2 = coory-y; y2 <= coory-y+(avancey-1); y2++ ){
//                            pizza.cutPizza(y2,x2);
//
//                        }
//                    }
//
//                    //Y recalculamos los pesos para todas las casillas alrededor de esta.
//                    Map<Integer, Integer> sortedMap = pizza.getWeigthOfCellsSorted();
//                    for(int x3 = slice.getX1()-pizza.getMaxCellsSlice(); x3 <= slice.getX2()+pizza.getMaxCellsSlice(); x3++){
//                        for(int y3 = slice.getY1()-pizza.getMaxCellsSlice(); y3 <= slice.getY2()+pizza.getMaxCellsSlice(); y3++){
//                            int pesoDeCasilla = weightOfCell(y3*pizza.getMaxColumn()+x3);
//                            if (pesoDeCasilla >= 1) {
//                                sortedMap.put(y3*pizza.getMaxColumn()+x3,pesoDeCasilla);
//                            } else {
//                                sortedMap.remove(y3*pizza.getMaxColumn()+x3);
//                            }
//                        }
//                    }
//                    sortedMap = sortByValue(sortedMap);
//                    pizza.setWeigthOfCellsSorted(sortedMap);
//
//                    if(contador.get()%50==0) {
//                        try {
//                            WriteFile.imprimeCortes(slices);
//                            slices.clear();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
//        return map.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (e1, e2) -> e1,
//                        LinkedHashMap::new
//                ));
//    }

