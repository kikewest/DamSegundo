package semaforo;

class OperarioC extends Thread {
    private Cadena cadena;
    private int tipoOperario;
    private int limiteColocacion;

    public OperarioC(Cadena cadena, int tipoOperario, int limiteColocacion) {
        this.cadena = cadena;
        this.tipoOperario = tipoOperario;
        this.limiteColocacion = limiteColocacion;
    }

    @Override
    public void run() {
        try {
            int productosColocados = 0;
            while (productosColocados < limiteColocacion) {
                int tipoProducto = (int) (Math.random() * 3) + 1;
                cadena.colocarProducto(tipoProducto);
                productosColocados++;
                sleep(1000);
            }
            System.out.println("OperarioC ha alcanzado el límite de colocación.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
