package test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import model.MultiThreadedQuickSort;
import model.SequentialQuickSort;
import utils.ArrayUtils;

public class ArraySortingTest {

	public static void main(String[] args) {
		
		// Declaramos variables para medir el tiempo de ejecución en micro-segundos
		double inicio = 0, fin = 0;
		
		// Genero un array con largo = 1.000.000 de elementos, y con números aleatorios del 1 al 10.000
		int[] array = ArrayUtils.randomArray(1000000, 1, 10000);
		
		// Genero una copia del mismo array
		int[] array2 = Arrays.copyOf(array, array.length);

		// Ejecuto el procedimiento del ordenamiento secuencial
		sequentialSorting(array, inicio, fin);
		
		// Ejecuto el procedimiento del ordenamiento concurrente
		multiThreadedSorting(array2, inicio, fin);
	}

	public static void sequentialSorting(int[] array, double inicio, double fin) {
		
		// Muestro un encabezado para identificar el procedimientto
		System.out.println("\n=== ORDENAMIENTO SECUENCIAL ===");

		// Capturo el tiempo exacto en micro-segundos de ejecución
		inicio = System.nanoTime();
		
		// Ejecuto el quicksort secuencial con el array
		SequentialQuickSort.quickSort(array, 0, array.length - 1);
		
		// Capturo el tiempo exacto en micro-segundos del fin de la ejecución
		fin = System.nanoTime();
		
		// Muestro la demora en micro-segundos del ordenamiento
		System.out.println("Demora en microsegundos: %.3f us".formatted((fin - inicio) / 1000));
	}

	public static void multiThreadedSorting(int[] array, double inicio, double fin) {
		// Instancio un ForkJoinPool para ejecutar el ordenamiento concurrente
		ForkJoinPool pool = new ForkJoinPool();
		
		// Instancio el quicksort concurrente (que es extiende de RecursiveAction)
		MultiThreadedQuickSort sortTask = new MultiThreadedQuickSort(array, 0, array.length - 1);

		// Muestro un encabezado para identificar el procedimiento
		System.out.println("\n=== ORDENAMIENTO CONCURRENTE ===");

		// Capturo el tiempo exacto en micro-segundos de ejecución
		inicio = System.nanoTime();
		
		// Mandamos al pool a ejecutar la tarea (ejecutamos el ordenamiento concurrente)
		pool.invoke(sortTask);
		
		// Cerramos el pool para no consumir mas recursos
		pool.close();

		// Capturo el tiempo exacto en micro-segundos del fin de la ejecución
		fin = System.nanoTime();
		
		// Muestro la demora en micro-segundos del ordenamiento
		System.out.println("Demora en microsegundos: %.3f us".formatted((fin - inicio) / 1000));
	}

}
