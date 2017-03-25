package org.vp.prep.ds;

public class HeapSort {

	private static int N;

	public static void sort(int arr[]) {
		buildHeap(arr);
		for (int i = N; i > 0; i--) {
			swap(arr, 0, i);
			N = N - 1;
			heapify(arr, 0);
		}
	}

	public static void buildHeap(int arr[]) {
		N = arr.length - 1;
		for (int i = N / 2; i >= 0; i--)
			heapify(arr, i);
	}

	public static void heapify(int arr[], int i) {
		int left = 2 * i;
		int right = 2 * i + 1;
		int max = i;
		if (left <= N && arr[left] > arr[i]) {
			max = left;
		}

		if (right <= N && arr[right] > arr[max]) {
			max = right;
		}

		if (max != i) {
			swap(arr, i, max);
			heapify(arr, max);
		}
	}

	/* Function to swap two numbers in an array */
	public static void swap(int arr[], int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void main(String[] args) {
		int arr[] = { 5, 4, 6, 7, 9, 1, 3, 0 };
		sort(arr);
		System.out.println("\nElements after sorting ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

}