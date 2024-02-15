package semaforo;

import java.util.concurrent.Semaphore;

public class pruebaChat {
	class Cadena {
	    private Semaphore mutex;
	    private Semaphore espacioDisponible;
	    private int[] productos;
	    private int capacidad;
	    private int productosColocados;
	    private boolean hayProductosPorColocar;

	    public Cadena(int capacidad) {
	        this.mutex = new Semaphore(1);
	        this.espacioDisponible = new Semaphore(capacidad);
	        this.capacidad = capacidad;
	        this.productos = new int[capacidad];
	        this.productosColocados = 0;
	        this.hayProductosPorColocar = true;
	    }

	    public boolean estaVacia() {
	        for (int producto : productos) {
	            if (producto != 0) {
	                return false;
	            }
	        }
	        return true;
	    }

	    public boolean hayProductosPorColocar() {
	        return hayProductosPorColocar;
	    }

	    public boolean hayProductosDisponibles(int tipoOperario) {
	        for (int producto : productos) {
	            if (producto == tipoOperario) {
	                return true;
	            }
	        }
	        return false;
	    }

	    public void colocarProducto(int tipoProducto) throws InterruptedException {
	        espacioDisponible.acquire();
	        mutex.acquire();
	        try {
	            while (hayProductosPorColocar && (estaLlena() || productosColocados >= 20)) {
	                mutex.release();
	                Thread.sleep(100);
	                mutex.acquire();
	            }

	            for (int i = 0; i < capacidad; i++) {
	                if (productos[i] == 0) {
	                    productos[i] = tipoProducto;
	                    System.out.println("OperarioC colocó un producto de tipo " + tipoProducto);
	                    productosColocados++;
	                    espacioDisponible.release();
	                    if (productosColocados >= 20) {
	                        hayProductosPorColocar = false;
	                        System.out.println("OperarioC ha alcanzado el límite de colocación.");
	                    }
	                    break;
	                }
	            }
	        } finally {
	            mutex.release();
	        }
	    }

	    public int recogerProducto(int tipoOperario) throws InterruptedException {
	        espacioDisponible.acquire();
	        mutex.acquire();
	        try {
	            int contador = 0;
	            for (int i = 0; i < capacidad; i++) {
	                if (productos[i] == tipoOperario) {
	                    productos[i] = 0;
	                    contador++;
	                    espacioDisponible.release();
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

	            // Esperar a que todos los productos sean recogidos por los operarios antes de terminar
	            while (productosColocados > 0) {
	                Thread.sleep(100);
	            }
	            System.out.println("OperarioC ha dejado de colocar productos.");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

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
	            while (cadena.hayProductosPorColocar() || cadena.hayProductosDisponibles(tipoOperario) || !cadena.estaVacia()) {
	                cadena.recogerProducto(tipoOperario);
	                sleep(1500);
	            }
	            System.out.println("Operario" + tipoOperario + " ha dejado de ir a la cinta.");
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}


	    public static void main(String[] args) {
	        pruebaChat instanciaPruebaChat = new pruebaChat();
	        int capacidadCadena = 5;
	        Cadena cadena = instanciaPruebaChat.new Cadena(capacidadCadena);

	        int limiteColocacionC = 20;
	        OperarioC opc = instanciaPruebaChat.new OperarioC(cadena, 0, limiteColocacionC);
	        Operario op1 = instanciaPruebaChat.new Operario(cadena, 1);
	        Operario op2 = instanciaPruebaChat.new Operario(cadena, 2);
	        Operario op3 = instanciaPruebaChat.new Operario(cadena, 3);

	        opc.start();
	        op1.start();
	        op2.start();
	        op3.start();
	    }
	}
