package elPeorCaso;

public class Elpeorcaso {
	public static boolean buscarElemento(int[] array, int elementoBuscado) {
        for (int elemento : array) {
            if (elemento == elementoBuscado) {
                return true; // Se encontró el elemento
            }
        }
        return false; // No se encontró el elemento
    }
	public static void main(String[] args) {
		int[] numeros = {1,2,3,4,5,6,7,8,9,10};
		buscarElemento(numeros,6);

	}

}
