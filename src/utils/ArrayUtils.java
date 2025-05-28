package utils;

import java.util.Random;

/**
 * Clase de utilería para arrays
 * 
 * @author Leandro Blandi
 */
public class ArrayUtils {

	private ArrayUtils() {

	}

	/**
	 * Método estático que genera un arreglo con un tamaño `arraySize`, y lo
	 * completa con valores aleatorios con extremos `minValue` y `maxValue`
	 * 
	 * @param arraySize El tamaño del array, mayor a 0
	 * @param minValue  El valor mínimo con el que completar el array
	 * @param maxValue  El valor máximo con el que completar el array
	 * @return El array con el tamaño indicado, y los valores
	 * 
	 */
	public static int[] randomArray(int arraySize, int minValue, int maxValue) {

		// Si el tamaño del array no es valido
		if (arraySize <= 0) {
			throw new RuntimeException("El tamaño del array debe ser mayor que 0");
		}

		// Si el valor minimo supera al valor máximo
		if (minValue > maxValue) {
			throw new RuntimeException("El valor minimo aleatorio debe ser menor que el valor maximo aleatorio");
		}

		// Creo un array con el valor indicado
		int[] array = new int[arraySize];

		// Recorro cada indice del array
		for (int i = 0; i < array.length; i++) {

			// Y lo relleno con un numero aleatorio
			array[i] = randomNumber(minValue, maxValue);
		}

		return array;
	}

	/**
	 * Imprime por pantalla un array
	 * 
	 * @param array El arreglo a imprimir por pantalla
	 */
	public static void printArray(int[] array) {

		// Muestro por pantalla un identificador para el array
		System.out.print("Array: { ");

		// Recorro cada indice del array
		for (int i = 0; i < array.length; i++) {

			// Si es la ultima iteración
			if (i == array.length - 1) {

				// Muestro el elemento con un '}' al final
				System.out.print("%d }".formatted(array[i]));

				// Si es cualquier otra iteración
			} else {

				// Muestro el elemento con una ',' al final
				System.out.print("%d, ".formatted(array[i]));
			}
		}

		// Imprimo un salto de linea
		System.out.println();
	}

	/**
	 * Método estático para generar un número entero aleatorio
	 * 
	 * @param minValue El valor mínimo
	 * @param maxValue El valor máximo
	 * @return Un número aleatorio (maxValue >= x >= minValue)
	 */
	private static int randomNumber(int minValue, int maxValue) {

		// Genero un número aleatorio x tal que (maxValue >= x >= minValue)
		return new Random().nextInt(maxValue - minValue + 1) + minValue;
	}
}
