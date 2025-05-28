package utils;

import java.util.Random;

public class ArrayUtils {
	
	private ArrayUtils() {

	}
	
	public static int[] randomArray(int arraySize, int minValue, int maxValue) {
		
		if (arraySize <= 0) {
			throw new RuntimeException("El tamaño del array debe ser mayor que 0");
		}
		
		if (minValue > maxValue) {
			throw new RuntimeException("El valor minimo aleatorio debe ser menor que el valor maximo aleatorio");
		}
		
		int[] array = new int[arraySize];
		
		for (int i = 0; i < array.length; i++) {
			array[i] = randomNumber(minValue, maxValue);
		}
		
		return array;
	}
	
	public static void printArray(int[] array) {
		System.out.print("Array: { ");
		for (int i = 0; i < array.length; i++) {
			
			if (i == array.length - 1) {				
				System.out.print("%d }".formatted(array[i]));
			} else {
				System.out.print("%d, ".formatted(array[i]));
			}
		}
		
		
		System.out.println();
	}
	
	private static int randomNumber(int minValue, int maxValue) {
		return new Random().nextInt(maxValue - minValue + 1) + minValue;
	}
}
