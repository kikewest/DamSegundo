package semaforo;

import java.util.concurrent.Semaphore;

class Cadena {
    private Semaphore mutex;
    private int[] productos;
    private int capacidad;
    private int productosColocados;

    public Cadena(int capacidad) {
        this.mutex = new Semaphore(1);
        this.capacidad = capacidad;
        this.productos = new int[capacidad];
        this.productosColocados = 0;
    }

    public void colocarProducto(int tipoProducto) throws InterruptedException {
        mutex.acquire();
        try {
            while (estaLlena() || productosColocados >= 20) {
                mutex.release();
                Thread.sleep(100);
                mutex.acquire();
            }

            for (int i = 0; i < capacidad; i++) {
                if (productos[i] == 0) {
                    productos[i] = tipoProducto;
                    System.out.println("OperarioC colocó un producto de tipo " + tipoProducto);
                    productosColocados++;
                    break;
                }
            }
        } finally {
            mutex.release();
        }
    }

    public int recogerProducto(int tipoOperario) throws InterruptedException {
        mutex.acquire();
        try {
            int contador = 0;
            for (int i = 0; i < capacidad; i++) {
                if (productos[i] == tipoOperario) {
                    productos[i] = 0;
                    contador++;
                    break;
                }
            }

            System.out.println("Operario" + tipoOperario + " recogió " + contador + " producto de su tipo");
            return contador;
        } finally {
            mutex.release();
        }
    }

    private boolean estaLlena() {
        for (int producto : productos) {
            if (producto == 0) {
                return false;
            }
        }
        return true;
    }
}