package com.project;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.project.Objetos.Usuario;
import com.project.Objetos.WebPage;

public class Exercici013 {
        public static void main(String[] args) {
        // AQUI DEFINIMOS LA WEB CON UNA CAPACIDAD DE 5 USUARIOS
        WebPage webPage = new WebPage(5);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i <= 10; i++) {
            // AQUI GENERAMOS USUARIOS HASTA UN MAXIMO DE 10 VECES Y LOS VAMOS EJECUTANDO CON EL EXECUTOR 
            String usuario = "Usuario " + i;
            executorService.submit(new Usuario(webPage, usuario));
        }

        executorService.shutdown();
    }
}

