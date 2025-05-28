package model;

import java.io.Serial;
import java.util.concurrent.RecursiveAction;

public class MultiThreadedQuickSort extends RecursiveAction {
	@Serial
	private static final long serialVersionUID = -4684063731223980336L;

	private static final int MAX_THREADS = Runtime.getRuntime().availableProcessors();
	private final int[] array;
	private final int left;
	private final int right;

	public MultiThreadedQuickSort(int[] array, int left, int right) {
		this.array = array;
		this.left = left;
		this.right = right;
	}

	@Override
	protected void compute() {
		if (left >= right) return;

		int pivot = partition(array, left, right);

		MultiThreadedQuickSort leftTask = new MultiThreadedQuickSort(array, left, pivot);
		MultiThreadedQuickSort rightTask = new MultiThreadedQuickSort(array, pivot + 1, right);

		if (getSurplusQueuedTaskCount() < MAX_THREADS) {
			leftTask.fork();
			rightTask.fork();
		} else {
			leftTask.invoke();
			rightTask.invoke();
		}

		leftTask.join();
		rightTask.join();
	}

	// Método para particionar el array
	private int partition(int[] array, int left, int right) {
		int pivot = array[left + (right - left) / 2];
		int i = left;
		int j = right;

		while (true) {
			while (i <= j && array[i] < pivot) i++;
			while (i <= j && array[j] > pivot) j--;

			if (i >= j) return j;

			swap(array, i, j);
			i++;
			j--;
		}
	}

	// Método para intercambiar dos elementos en el array
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
