package com.sys4u.exam;

public class SortHelper {
	public static int arr[] = { 4, 8, 3, 6, 9, 1, 5, 0, 2, 7 };

	public static void print(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}

		System.out.println("");
	}
	
	public static void swap(int[] arr, int indexFirst, int indexSecond) {
		int temp = arr[indexFirst];
		arr[indexFirst] = arr[indexSecond];
		
		arr[indexSecond] = temp;
	}
}
