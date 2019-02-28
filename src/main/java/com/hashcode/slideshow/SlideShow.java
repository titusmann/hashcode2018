package com.hashcode.slideshow;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class SlideShow {

    // LEER EL ARCHIVO

    public static String DIR_FICHERO = "C:\\Users\\fjbelaza\\Documents\\hashcode2018\\src\\main\\resources\\2019\\b_lovely_landscapes.txt";
    public static String DIR_FICHERO_SALIDA = "C:\\Users\\fjbelaza\\Documents\\hashcode2018\\src\\main\\resources\\2019\\b_result.txt";
    public static ArrayList<Photo> PHOTOS_HORIZONTALES;
    public static ArrayList<Photo> PHOTOS_VERTICALES;

    public static List<Slide> ALL_SLIDES_ORDERED = new ArrayList<>();
    public static List<Slide> ALL_SLIDES = new ArrayList<>();



    public static void main(String[] args) {
        ArrayList<Photo> photosArrayList;



        PHOTOS_HORIZONTALES = new ArrayList<>();
        PHOTOS_VERTICALES = new ArrayList<>();
        try {
            photosArrayList = ReadFile.getPhotos(DIR_FICHERO);

            separarPhotos(photosArrayList);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // GENERAR LOS SLIDES CON LAS FOTOS VERTICALES
        generarSlides();
        // GENERAR EL SLIDESHOW DE LA MANERA MAS EFICIENTE

        generarSlideShow();
        // DEVOLVER EL ARCHIVO

        try {
            WriteFile.imprimeFichero(ALL_SLIDES_ORDERED,DIR_FICHERO_SALIDA);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void separarPhotos(final ArrayList<Photo> photoArrayList)
    {
        System.out.println("Separando fotos");
        for (final Photo photo : photoArrayList)
        {
            if (photo.isHorizontal)
            {
                PHOTOS_HORIZONTALES.add(photo);
            }
            else
            {
                PHOTOS_VERTICALES.add(photo);
            }
        }

        //Collections.sort(PHOTOS_HORIZONTALES, (Comparator) (o1, o2) -> (((Photo) o2).tags.size() > ((Photo) o1).tags.size()) ? 1 : -1);

        Collections.sort(PHOTOS_VERTICALES, (Comparator) (o1, o2) -> (((Photo) o2).tags.size() > ((Photo) o1).tags.size()) ? 1 : -1);
    }


    private static void generarSlides(){

        System.out.println("Generando Slides horizontales");
        for (Photo photo : PHOTOS_HORIZONTALES) {
            Photo[] arrayPhoto = new Photo[1];
            arrayPhoto[0] = photo;
            ALL_SLIDES.add(new Slide(arrayPhoto));
        }

        // COGER LAS FOTOS VERTICALES. REVISAR!

        int numOfVerticalSlides = PHOTOS_VERTICALES.size()/2;

        System.out.println("Generando slides verticales");

        for (int i = 0; i < numOfVerticalSlides; i++) {
            Photo[] arrayPhoto = new Photo[2];
            arrayPhoto[0] = PHOTOS_VERTICALES.get(i);
            arrayPhoto[1] = PHOTOS_VERTICALES.get((PHOTOS_VERTICALES.size()-1)-i);
            ALL_SLIDES.add(new Slide(arrayPhoto));

            // Coger una foto del inicio. Coger el 10% de las ultimas finales. Con las que menos coincida, unir.
        }

    }

    private static void generarSlideShow(){
        System.out.println("Generando Slideshow");

        // Puntuacion maxima: Mitad (hacia abajo) del numero de tags.
        // Ordenamos por tamaño.

        Map<Integer,ArrayList<Slide>> PHOTOS_ORDERED_BY_SIZE;

        Map<Integer, List<Slide>> s1 = new HashMap<>();


        for (Slide slide : ALL_SLIDES) {
            if (s1.containsKey(slide.getNumberTagas())) {
                List<Slide> slideList =s1.get(slide.getNumberTagas());
                slideList.add(slide);
                s1.put(slide.getNumberTagas(), slideList);
            } else {
                List<Slide> slideList = new ArrayList<>();
                slideList.add(slide);
                s1.put(slide.getNumberTagas(), slideList);
            }
        }



        //Cojo la primera foto.
        // Busco la siguiente con mayor puntuación, y la junto. <- Bucle

        for (List<Slide> setOfSameSize : s1.values()) {

            System.out.println("Cogiendo siguiente set de Slides");
            List<Slide> sOrdered = new ArrayList<>();

            Slide firstSlide = setOfSameSize.get(0);
            setOfSameSize.remove(firstSlide);

            while(setOfSameSize.size() > 0) {
                int idealPunctuation = firstSlide.getTags().size() / 2;

                int bestPunctuation = -1;
                Slide bestSlide = null;
                Iterator sameSize = setOfSameSize.iterator();
                while (sameSize.hasNext()){
                    Slide testSlide = (Slide) sameSize.next();
                    int punctuation = firstSlide.getPunctuationFor(testSlide);
                    if (punctuation == idealPunctuation) {
                        bestSlide = testSlide;
                        break;
                    } else if (punctuation > bestPunctuation) {
                        bestPunctuation = punctuation;
                        bestSlide = testSlide;
                    }
                }

                sOrdered.add(firstSlide);
                firstSlide = bestSlide;
                setOfSameSize.remove(bestSlide);


            }
            if(setOfSameSize.size() == 0){
                sOrdered.add(firstSlide);
            }

            ALL_SLIDES_ORDERED.addAll(sOrdered);

        }




    }

}