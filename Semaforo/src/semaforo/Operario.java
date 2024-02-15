package semaforo;

class Operario extends Thread {
    private Cadena cadena;
    private int tipoOperario;

    public Operario(Cadena cadena, int tipoOperario) {
        this.cadena = cadena;
        this.tipoOperario = tipoOperario;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int productosRecogidos = cadena.recogerProducto(tipoOperario);
                sleep(1500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

