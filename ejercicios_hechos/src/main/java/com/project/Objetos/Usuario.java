package com.project.Objetos;

public class Usuario implements Runnable {
    private final WebPage webPage;
    private final String usuario;

    public Usuario(WebPage webPage, String usuario) {
        this.webPage = webPage;
        this.usuario = usuario;
    }

    @Override
    public void run() {
        // AQUI INDICAMOS QUE EL USUARIO X ENTRA A LA WEB
        webPage.enter(usuario);

        // AQUI HACEMOS QUE DURE UN TIEMPO ALEATORIO DENTRO DE LA WEB
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // AQUI LO SACAMOS DE LA WEB
        webPage.exit(usuario);
    }
}
