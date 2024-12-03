package com.project.Objetos;

import java.util.concurrent.Semaphore;

public class WebPage {

    private final Semaphore semaphore;

    public WebPage(int capacidad) {
        this.semaphore = new Semaphore(capacidad);
    }

    public void enter(String usuario) {
        try {
            // AQUI MIRAMOS SI HAY PERMISOS ABIERTOS Y SI NO HAY EL USUARIO ESPERA
            if (semaphore.availablePermits() == 0) {
                System.out.println(usuario + " està esperando porque la web esta llena.");
            }
            // AQUI COMO HAY PERMISOS DISPONIBLES OCUPAMOS UNO Y DECIMOS QUE EL USUARIO ESTA ENTRANDO A LA WEB
            semaphore.acquire();
            System.out.println("El usuario " + usuario + " ha entrado en la web");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } 

    public void exit(String usuario) {
        // AQUI DECIMOS QUE EL USUARIO SALE DE LA WEB Y LIBERAMOS UNO DE LOS PERMISOS
        System.out.println("El usuario " + usuario + " ha salido de la web");
        semaphore.release();
    }
}
