package model;

import java.io.Serial;
import java.util.concurrent.RecursiveAction;

/**
 * Clase que representa la implementación del algoritmo QuickSort en forma
 * concurrente, utilizando el framework Fork/Join de Java.
 * 
 * Cada instancia representa una tarea recursiva que ordena un subarray.
 * 
 * @author Leandro Blandi
 */
public class MultiThreadedQuickSort extends RecursiveAction {
	@Serial
	private static final long serialVersionUID = -4684063731223980336L;

	// Número máximo de hilos disponibles en el sistema
	private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();

	// Array a ordenar
	private final int[] array;

	// Índice izquierdo del subarray a ordenar
	private final int left;

	// Índice derecho del subarray a ordenar
	private final int right;

	/**
	 * Constructor que inicializa los atributos de la tarea
	 * 
	 * @param array El array a ordenar
	 * @param left  El índice inicial del subarray
	 * @param right El índice final del subarray
	 */
	public MultiThreadedQuickSort(int[] array, int left, int right) {
		this.array = array;
		this.left = left;
		this.right = right;
	}

	@Override
	protected void compute() {

		// Si el subarray tiene uno o ningún elemento, no se hace nada
		if (left >= right)
			return;

		// Particiono el array y obtengo el índice del pivote
		int pivot = partition(array, left, right);

		// Creo las sub-tareas para ordenar las mitades izquierda y derecha
		MultiThreadedQuickSort leftTask = new MultiThreadedQuickSort(array, left, pivot);
		MultiThreadedQuickSort rightTask = new MultiThreadedQuickSort(array, pivot + 1, right);

		// Si hay recursos disponibles, ejecuto las tareas en paralelo
		if (getSurplusQueuedTaskCount() < MAX_THREADS) {
			leftTask.fork();
			rightTask.fork();
		} else {
			// Si no, las ejecuto de forma secuencial
			leftTask.invoke();
			rightTask.invoke();
		}

		// Espero a que ambas tareas finalicen
		leftTask.join();
		rightTask.join();
	}

	/**
	 * Método que realiza la partición del subarray
	 * 
	 * @param array El array a ordenar
	 * @param left  Índice inicial del subarray
	 * @param right Índice final del subarray
	 * @return El índice final donde queda posicionado el pivote
	 */
	private int partition(int[] array, int left, int right) {

		// Selecciono el elemento del medio como pivote
		int pivot = array[left + (right - left) / 2];

		// Inicializo los punteros izquierdo y derecho
		int i = left;
		int j = right;

		while (true) {

			// Avanzo el puntero izquierdo hasta encontrar un valor mayor o igual al pivote
			while (i <= j && array[i] < pivot)
				i++;

			// Retrocedo el puntero derecho hasta encontrar un valor menor o igual al pivote
			while (i <= j && array[j] > pivot)
				j--;

			// Si los punteros se cruzan, termina la partición
			if (i >= j)
				return j;

			// Intercambio los elementos fuera de lugar
			swap(array, i, j);

			// Actualizo los punteros
			i++;
			j--;
		}
	}

	/**
	 * Método para intercambiar dos elementos en el array
	 * 
	 * @param array El array donde se realiza el intercambio
	 * @param i     Índice del primer elemento
	 * @param j     Índice del segundo elemento
	 */
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
