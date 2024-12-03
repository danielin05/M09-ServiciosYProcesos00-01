package com.project;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Exercici011 {

    // AQUI DEFINIMOS LOS NUMEROS CON LOS QUE SE VAN A HACER LAS OPERACIONES
    private static int num1 = 40;
    private static int num2 = 55;
    private static int num3 = 8;
    private static int num4 = 19;
    private static int num5 = 25;

    // AQUI CREAMOS LAS VARIABLES DONDE VAMOS A GUARDAR LOS RESULTADOS
    private static int result0;
    private static int result1;
    private static double result2;

    // CREAMOS LA LISTA DONDE GUARDAREMOS LOS NUMEROS QUE HEMOS DEFINIDO ANTES
    private static List<Integer> llistaNums = new ArrayList<>();

    public static void main(String[] args) {

        // AÑADIMOS LOS NUMEROS A LA LISTA
        llistaNums.add(num1);
        llistaNums.add(num2);
        llistaNums.add(num3);
        llistaNums.add(num4);
        llistaNums.add(num5);

        // CON CYCLICBARRIER NOS ASEGURAMOS QUE ANTES DE EJECUTAR LO QUE TIENE DENTRO, SE HAN EJECUTADO LOS RUNNABLES QUE SOLICITA EL MISMO. EN ESTE CASO SOLICITA 3.
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
            System.out.println("Suma: " + result0 + " Mitjana: " + result1 + " Desviació: " + result2);
            }
        });

        // Executor per gestionar els fils
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Tasques que simulen els microserveis
        Runnable microservice1 = () -> {
            try {
                System.out.println("Calculant suma...");
                Thread.sleep(1000);
                System.out.println("Suma completada");
                result0 = suma();
                barrier.await(); 
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Tasques que simulen els microserveis
        Runnable microservice2 = () -> {
            try {
                System.out.println("Calculant mitjana...");
                Thread.sleep(1500);
                System.out.println("Mitjana completada");
                result1 = calcularMitjana();
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Tasques que simulen els microserveis
        Runnable microservice3 = () -> {
            try {
                System.out.println("Calculant Desviació...");
                Thread.sleep(2000);
                System.out.println("Desviació completada");
                result2 = desviacioEstandard();
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Executem les tasques
        executor.submit(microservice1);
        executor.submit(microservice2);
        executor.submit(microservice3);

        // Tanquem l'executor
        executor.shutdown();
    }

    // FUNCIONES PARA HACER LOS CALCULOS
    private static int calcularMitjana() {
        return suma() / llistaNums.size();
    }

    private static int suma() {
        int sumaTotal = 0;
        for (int i : llistaNums) {
            sumaTotal += i;
        }
        return sumaTotal;
    }

    private static double desviacioEstandard() {
        int media = calcularMitjana();
        double sumaDesviaciones = 0.0;
        for (int i : llistaNums) {
            sumaDesviaciones += Math.pow(i - media, 2);
        }
        double varianza = sumaDesviaciones / llistaNums.size();
        return Math.sqrt(varianza);
    }
}