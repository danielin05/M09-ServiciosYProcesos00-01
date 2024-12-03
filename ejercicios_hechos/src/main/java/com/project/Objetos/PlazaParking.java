package com.project.Objetos;

import java.util.concurrent.Semaphore;

public class PlazaParking {

    private final Semaphore semaphore;

    // AQUI GENERAMOS NUESTRO SEMAFORO PASANDOLE LA CANTIDAD DISPONIBLE DE PLAZAS DE PARKING, ES DECIR LA CANTIDAD DE HILOS O PERMISOS DEL QUE DISPONE
    public PlazaParking(int capacidad) {
        this.semaphore = new Semaphore(capacidad);
    }

    public void enter(String coche) {
        try {
            // AQUI MIRAMOS SI HAY PERMISOS ABIERTOS Y SI NO HAY EL COCHE ESPERA
            if (semaphore.availablePermits() == 0) {
                System.out.println(coche + " està esperando porque el aparcamiento esta lleno.");
            }
            // AQUI COMO HAY PERMISOS DISPONIBLES OCUPAMOS UNO Y DECIMOS QUE EL COCHE ESTA ENTRANDO AL PARKING
            semaphore.acquire();
            System.out.println("El coche " + coche + " está entrando al parking");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    } 

    public void exit(String coche) {
        // AQUI DECIMOS QUE EL COCHE SALE DEL PARKING Y LIBERAMOS UNO DE LOS PERMISOS
        System.out.println("El coche " + coche + " está saliendo del parking");
        semaphore.release();
    }
}