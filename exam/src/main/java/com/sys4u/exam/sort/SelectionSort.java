package com.sys4u.exam.sort;

import com.sys4u.exam.SortHelper;

public class SelectionSort {
	public static void main(String[] args) {
		int[] arr = { 6, 2, 8, 4, 6, 3, 7, 1, 9, 5 };

		sort(arr);
	}

	private static void sort(int[] arr) {
		if (arr == null) {
			throw new IllegalArgumentException();
		}
		
		System.out.println("Start");
		SortHelper.print(arr);
		
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = findMinValueIndex(arr, i);
			
			SortHelper.swap(arr, i, minIndex);
			
			SortHelper.print(arr);
		}
		
		System.out.println("End");
	}

	private static int findMinValueIndex(int[] arr, int startIndex) {
		int minIndex = startIndex;
		
		for (int j = startIndex+1; j < arr.length; j++) {
			if(arr[minIndex] > arr[j]) {
				minIndex = j;
			}
		}
		return minIndex;
	}
}
