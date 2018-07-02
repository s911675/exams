package com.sys4u.exam.sort;

public class QuickSort {
	public static void main(String[] args) {
		int[] arr = { 6, 2, 8, 4, 6, 3, 7, 1, 9, 5 };

		print(arr, 0, arr.length - 1);
		quickSort(arr, 0, arr.length - 1);
		print(arr, 0, arr.length - 1);
	}

	public static void quickSort(int[] arr, int start, int end) {
		// TODO Auto-generated method stub
		if (start > end) {
			return;
		}

		int pivot = partition(arr, start, end);

		quickSort(arr, start, pivot - 1);
		quickSort(arr, pivot + 1, end);
	}

	private static int partition(int[] arr, int start, int end) {

		int pivot = end;
		int left = start;
		int right = end;

		while (left < right) {
			while ((left < right) && (arr[left] < arr[pivot])) {
				left++;
			}

			while ((left < right) && (arr[right] >= arr[pivot])) {
				right--;
			}

			if (left < right) {
				print(arr, start, end);

				int temp = arr[right];
				arr[right] = arr[left];
				arr[left] = temp;

				print(arr, start, end);
			}
		}

		return right;
	}

	private static void print(int[] arr, int start, int end) {
		System.out.println("\n");

		for (int i = start; i < end; i++) {
			System.out.print(arr[i] + ", ");
		}

		System.out.println("\n");
	}
}
