package com.project.Objetos;

public class Coche implements Runnable {
    private final PlazaParking plazaParking;
    private final String coche;

    public Coche(PlazaParking plazaParking, String coche) {
        this.plazaParking = plazaParking;
        this.coche = coche;
    }

    @Override
    public void run() {
        // AQUI INDICAMOS QUE EL COCHE X ENTRA AL PARKING
        plazaParking.enter(coche);

        // AQUI HACEMOS QUE DURE UN TIEMPO ALEATORIO DENTRO DEL PARKING
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // AQUI LO SACAMOS DEL PARKING
        plazaParking.exit(coche);
    }
}
