package model;

/**
 * Clase para realizar el ordenamiento Quicksort de manera secuencial
 * @author el3be
 *
 */
public class SequentialQuickSort {

	private SequentialQuickSort() {

	}
	
	/**
	 * Método estático para ordenar arrays por QuickSort
	 * 
	 * @param arr  El array con los elementos ya cargados
	 * @param low  El
	 * @param high
	 */
	public static void quickSort(int[] arr, int low, int high) {

		// Si el subarray tiene al menos dos elementos, continuo con la partición
		if (low < high) {

			// Llamo al método partition para dividir el array y obtengo el índice donde se
			// posiciona el pivote
			int pi = partition(arr, low, high);

			// Ordeno recursivamente la parte izquierda del pivote (elementos menores)
			quickSort(arr, low, pi - 1);

			// Ordeno recursivamente la parte derecha del pivote (elementos mayores)
			quickSort(arr, pi + 1, high);
		}
	}

	/**
	 * Método auxiliar del algoritmo QuickSort que realiza la partición del array.
	 * 
	 * @param arr  El array de enteros que se desea ordenar
	 * @param low  El índice de inicio del subarray sobre el cual se aplica la
	 *             partición
	 * @param high El índice final del subarray (donde se encuentra el pivote)
	 * @return El índice final donde queda ubicado el pivote, con todos los
	 *         elementos menores a su izquierda y mayores a su derecha
	 */
	private static int partition(int[] arr, int low, int high) {

		// Selecciono como pivote el último elemento del subarray
		int pivot = arr[high];

		// Inicializo un índice i que irá marcando la posición final del último elemento
		// menor al pivote
		int i = low - 1;

		// Recorro el subarray desde low hasta high - 1 (excluyo al pivote)
		for (int j = low; j < high; j++) {

			// Si el elemento actual es menor al pivote
			if (arr[j] < pivot) {
				i++; // Incremento i para avanzar a la siguiente posición de los elementos menores

				// Intercambio los elementos arr[i] y arr[j] para mover el menor a su lugar
				// correspondiente
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// Intercambio el pivote con el primer elemento mayor para posicionarlo
		// correctamente
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		return i + 1; // Devuelvo el índice final del pivote, ya ordenado respecto al subarray
	}
}
