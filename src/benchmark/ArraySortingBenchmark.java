package benchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

import model.MultiThreadedQuickSort;
import model.SequentialQuickSort;
import utils.ArrayUtils;

public class ArraySortingBenchmark {

	public static void main(String[] args) {
		int[] sizes = {1000, 5000, 10000, 50000, 100000, 500000, 1000000};
		StringBuilder csv = new StringBuilder("Tamanio;Secuencial (µs);Concurrente (µs)\n");

		for (int size : sizes) {
			int[] array = ArrayUtils.randomArray(size, 1, 10000);
			int[] arrayCopy = Arrays.copyOf(array, array.length);

			long tiempoSecuencial = medirSecuencial(array);
			long tiempoConcurrente = medirConcurrente(arrayCopy);

			csv.append("%d;%.2f;%.2f\n".formatted(
				size,
				tiempoSecuencial / 1000.0,
				tiempoConcurrente / 1000.0
			));
		}

		guardarCSV(csv.toString(), "resultados.csv");
		System.out.println("Resultados guardados en resultados.csv");
	}

	private static long medirSecuencial(int[] array) {
		long inicio = System.nanoTime();
		SequentialQuickSort.quickSort(array, 0, array.length - 1);
		long fin = System.nanoTime();
		return fin - inicio;
	}

	private static long medirConcurrente(int[] array) {
		ForkJoinPool pool = new ForkJoinPool();
		MultiThreadedQuickSort sortTask = new MultiThreadedQuickSort(array, 0, array.length - 1);
		long inicio = System.nanoTime();
		pool.invoke(sortTask);
		pool.close();
		long fin = System.nanoTime();
		return fin - inicio;
	}

	private static void guardarCSV(String contenido, String nombreArchivo) {
		try (FileWriter writer = new FileWriter(nombreArchivo)) {
			writer.write(contenido);
		} catch (IOException e) {
			System.err.println("Error al guardar el archivo CSV: " + e.getMessage());
		}
	}
}
