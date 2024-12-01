package com.project;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Exercici002 {
    
    public static void main(String[] args) {

        //AQUI DEFINO LA PRIMERA COMPLETABLEFUTURE Y DEVUELVO UN NUMERO
        CompletableFuture<Integer> validacio = CompletableFuture.supplyAsync(() -> {
            System.out.println("Validant les dades de la solicitud");
            return 150;
        });

        //AQUI DEFINO LA SEGUNDA COMPLETABLEFUTURE, RECOJO EL RESULTADO DE VALIDACIO Y DEVUELVO EL RESULTADO DIVIDIDO ENTRE 10 
        CompletableFuture<Integer> modificacio = validacio.thenApply(result -> {
            System.out.println("Modificant les dades");
            return result / 10;
        });

        //AQUI RECOJO LAS MODIFICACIONES Y PRINTO EL RESULTADO FINAL
        try {
            Integer finalResult = modificacio.get();
            System.out.println("Resultat final = " + finalResult);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

}
