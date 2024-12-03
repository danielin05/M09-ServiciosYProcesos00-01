package com.project;

import java.util.concurrent.*;

public class Exercici010 {

    public static void main(String[] args) {
        
        // CON EL STRINGBUILDER ALMACENAMOS LOS RESULTADOS
        StringBuilder result = new StringBuilder();

        // CON CYCLICBARRIER NOS ASEGURAMOS QUE ANTES DE EJECUTAR LO QUE TIENE DENTRO, SE HAN EJECUTADO LOS RUNNABLES QUE SOLICITA EL MISMO. EN ESTE CASO SOLICITA 3.
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                // Aquesta acció es realitza quan tots els fils arriben a la barrera
                System.out.println("Tots els microserveis han acabat. Combinant els resultats...");
                String resultatFinal = result.toString();
                System.out.println("Resultat final: " + resultatFinal);
            }
        });

        // Executor per gestionar els fils
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Tasques que simulen els microserveis
        Runnable microservice1 = () -> {
            try {
                System.out.println("Microservei 1 processant dades...");
                Thread.sleep(1000);
                result.append("Dades1 ");
                System.out.println("Microservei 1 completat.");
                // SI NO PONEMOS EL BARRIER.AWAIT() LA BARRERA SE ROMPE Y NO NOS PRINTARA LO QUE TENGA DENTRO AUNQUE ACABEN EJECUTANDOSE LOS 3 THREADS
                barrier.await(); // Esperem que els altres fils acabin
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Tasques que simulen els microserveis
        Runnable microservice2 = () -> {
            try {
                Thread.sleep(10);
                System.out.println("Microservei 2 processant dades...");
                Thread.sleep(1500);
                result.append("Dades2 ");
                System.out.println("Microservei 2 completat.");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        // Tasques que simulen els microserveis
        Runnable microservice3 = () -> {
            try {
                Thread.sleep(20);
                System.out.println("Microservei 3 processant dades...");
                Thread.sleep(2000);
                result.append("Dades3");
                System.out.println("Microservei 3 completat.");
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
}
