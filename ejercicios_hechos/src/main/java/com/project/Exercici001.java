package com.project;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercici001 {

    public static void main(String[] args) {

        //AQUI CREO EL HASHMAP Y EL EXECUTOR
        ConcurrentHashMap<String, Integer> sharedData = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);


        //AQUI CREO LA PRIMERA TASCA RUNNABLE DONDE LE INTRODUZCO LAS DADAS AL HASHMAP
        Runnable introDadas = () -> {
            sharedData.put("Dades1", 3000);
            sharedData.put("Dades2", 100);
            sharedData.put("Dades3", 10);
            //System.out.println("Dades = " + sharedData);
        };


        //AQUI CREO LA SEGUNDA TASCA RUNNABLE DONDE MODIFICO LAS DADAS DEL HASHMAP
        Runnable modificaDadas = () -> {
            sharedData.put("Dades1", sharedData.get("Dades1") + sharedData.get("Dades3"));
            sharedData.put("Dades2", sharedData.get("Dades2") + sharedData.get("Dades3"));
            sharedData.put("Dades3", sharedData.get("Dades3") + sharedData.get("Dades3"));

            //System.out.println("Dades noves = " + sharedData);
        };


        //AQUI CREO LA ULTIMA TASCA CALLABLE DONDE RETORNO LAS DADAS QUE ME HAN RESUELTO AL FINAL DE LAS DOS RUNNABLES
        Callable<String> mostraDadas = () -> {
            String resultat = "DADES FINALS: Dades 1 = " + sharedData.get("Dades1") + ", Dades 2 = " + sharedData.get("Dades2") + ", Dades 3 = " + sharedData.get("Dades3");
            
            return resultat; 
        };

        try {

            //AQUI EJECUTO LAS TASCAS Y RECOJO LA INFORMACION QUE TIENEN Y MODIFICAN CON EL .GET
            executor.submit(introDadas).get();
            executor.submit(modificaDadas).get();
            Future<String> result = executor.submit(mostraDadas);

            //AQUI PRINTO LOS RESULTADOS FINALES
            System.out.println(result.get());

        
        } catch (InterruptedException | ExecutionException e){
            System.err.println("Error durant l'execució de les tasques: " + e.getMessage());
        } finally{

            //AQUI CIERRO EL EXECUTOR PARA AHORRAR RECURSOS
            executor.shutdown();
        }
}
}