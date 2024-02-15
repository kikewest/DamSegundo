package semaforo;

public class Semaforo {
	public static void main(String[] args) {
        int capacidadCadena = 5;
        Cadena cadena = new Cadena(capacidadCadena);

        int limiteColocacionC = 20; // Definir el límite de colocación para OperarioC
        OperarioC opc = new OperarioC(cadena, 0, limiteColocacionC);
        Operario op1 = new Operario(cadena, 1);
        Operario op2 = new Operario(cadena, 2);
        Operario op3 = new Operario(cadena, 3);

        opc.start();
        op1.start();
        op2.start();
        op3.start();
    }
}
