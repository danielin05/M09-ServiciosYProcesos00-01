package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici000 {

    public static void main(String[] args) {

        //AQUI CREO EL EXECUTOR
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        //AQUI CREO LA PRIMERA TASCA RUNNABLE
        Runnable registreEsdeveniments = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Registre d'esdeveniments del sistema: esdeveniment " + (i + 1));
                pausa(1000);
            }
        };

        //AQUI CREO LA SEGUNDA TASCA RUNNABLE
        Runnable comprobarXarxa = () -> {
        for (int i = 0; i < 5; i++) {
            System.out.println("Comprovant la xarxa: intent: " + (i + 1));
            pausa(1100);
        }
        };

        // 3. Enviar les tasques a l'ExecutorService
        executor.submit(registreEsdeveniments);
        executor.submit(comprobarXarxa);

        // 4. Tancar l'executor
        executor.shutdown();

    }        
    // Mètode auxiliar per simular una pausa
    private static void pausa(int milisegons) {
        try {
            Thread.sleep(milisegons);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
