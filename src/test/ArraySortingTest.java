package test;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import model.MultiThreadedQuickSort;
import model.SequentialQuickSort;
import utils.ArrayUtils;

public class ArraySortingTest {

	public static void main(String[] args) {
		double inicio = 0, fin = 0;
		
		int[] array = ArrayUtils.randomArray(1000000, 1, 10000);
		int[] array2 = Arrays.copyOf(array, array.length);

		sequentialSorting(array, inicio, fin);
		multiThreadedSorting(array2, inicio, fin);
	}

	public static void sequentialSorting(int[] array, double inicio, double fin) {
		System.out.println("\n=== ORDENAMIENTO SECUENCIAL ===");

		inicio = System.nanoTime();
		SequentialQuickSort.quickSort(array, 0, array.length - 1);
		fin = System.nanoTime();
		
		System.out.println("Demora en microsegundos: %.3f us".formatted((fin - inicio) / 1000));
	}

	public static void multiThreadedSorting(int[] array, double inicio, double fin) {
		ForkJoinPool pool = new ForkJoinPool();
		MultiThreadedQuickSort sortTask = new MultiThreadedQuickSort(array, 0, array.length - 1);

		System.out.println("\n=== ORDENAMIENTO CONCURRENTE ===");

		inicio = System.nanoTime();
		pool.invoke(sortTask);
		pool.close();
		fin = System.nanoTime();
		
		System.out.println("Demora en microsegundos: %.3f us".formatted((fin - inicio) / 1000));
	}

}
