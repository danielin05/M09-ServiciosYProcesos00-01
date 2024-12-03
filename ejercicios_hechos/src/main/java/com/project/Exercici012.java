package com.project;

import com.project.Objetos.Coche;
import com.project.Objetos.PlazaParking;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici012 {
    public static void main(String[] args) {
        // AQUI DEFINIMOS LA PLAZA DE PARKING CON UNA CAPACIDAD DE 3 COCHES
        PlazaParking plazaParking = new PlazaParking(3);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            // AQUI GENERAMOS COCHES HASTA UN MAXIMO DE 10 VECES Y LOS VAMOS EJECUTANDO CON EL EXECUTOR 
            String coche = "Cotxe " + i;
            executorService.submit(new Coche(plazaParking, coche));
        }
        executorService.shutdown();
    }
}
