package com.example.demo.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Selection sort
 * 
 * O(n^2)
 * 
 * @author s911675
 *
 */
public class SelectionSort {
	public static Integer current = 0;

	public static void print(int[] input) {
		System.out.println("\n+++++++++++ current=" + current++ + " +++++++++++");

		for (int i = 0, k = input.length; i < k; i++) {
			System.out.print(input[i]);
			System.out.print(" ");
		}
	}

	public static void sortIntArray(int[] array) {
		print(array);

		int inputLength = array.length;

		int count = 0;

		for (int i = 0; i < inputLength - 1; i++) {

			int min = i;

			// find the first, second, third, fourth... smallest value
			for (int j = i + 1; j < inputLength; j++) {
				if (array[j] < array[min]) {
					min = j;
				}

				count++;
			}

			// swaps the smallest value with the position 'i'
			int temp = array[i];
			array[i] = array[min];
			array[min] = temp;

			print(array);
			// next pls
		}

		System.out.println("count=" + count);
	}

	public static void sortIntArray2(int[] array) {
		if (array == null) {
			throw new IllegalArgumentException();
		}

		int size = array.length;

		System.out.println("+++++++++++ START +++++++++++");
		print(array);

		int count = 0;
		for (int replacePosition = 0; replacePosition < size - 1; replacePosition++) {
			int minPos = replacePosition;

			for (int comparePosition = replacePosition + 1; comparePosition < size; comparePosition++) {
				if (array[replacePosition] > array[comparePosition]) {
					minPos = comparePosition;
				}

				count++;
			}

			int temp = array[replacePosition];
			array[replacePosition] = array[minPos];

			array[minPos] = temp;

			print(array);

		}

		System.out.println("count=" + count);

		System.out.println("\n+++++++++++ END +++++++++++");
	}

	private <T extends Comparable<T>> void sort(T[] values, int first, int second) {
		if ((null == values) || (values.length < 2)) {
			return;
		}

		int length = values.length;
		int count = 0;
		int indexSwapElement;

		for (int i = 0; i < length - 1; i++) {
			indexSwapElement = i;

			for (int j = i + 1; j < length; j++) {
				count++;

				if (values[indexSwapElement + first].compareTo(values[j - second]) > 0) {
					// It gets the index of the smallest/biggest element.
					indexSwapElement = j;
				}
			}
			if (indexSwapElement != i) {
				// Put the smallest/biggest element at the index i, but only if the values (i
				// and
				// indexSmallestElement) have changed.
				// swap(values, i, indexSwapElement);
			}

		}
		System.out.printf("%2s: ", count);
	}
	
	public static void main(String[] args) {
		double[] array = {+18, 300, -150, 100};
		
		List<Double> list = Arrays.stream(array).boxed().collect(Collectors.toList());
		
		System.out.println(list);
		
		int k = 0;
		
		Double result  = 0D;
		
		
		
		if(1==1) {
			return;
		}
		
		while (k<1000000 || result == 0.4) {
			Collections.shuffle(list);

			int index = 0;
			
			String formula = "";
			
			result = list.get(index++);
			
			formula = result.toString();
			
			result = calculate(formula, list, result, index);
			result = calculate(formula, list, result, index);
			result = calculate(formula, list, result, index);

			System.out.println("Result:"+result + ", formula:" + formula );
			System.out.println("Result:"+result);
				
			k++;
		}
		
		if(result == 0.04) {
			System.out.println("Success");
			System.out.println(result);
		}
	}

	private static Double calculate(String formula, List<Double> list, Double result, int index) {
		int random = (int )(Math.random() * 50 + 1);
		
		int op = Math.abs(random%4);
		
		System.out.println("op=" + op);
		
		switch (op) {
		case 0:
			formula += "+" + list.get(index);
			result += list.get(index++);
			
			
			break;
		case 1:
			formula += "-" + list.get(index);
			result -= list.get(index++);
			
			break;
		case 2:
			formula += "/" + list.get(index);
			result /= list.get(index++);
			
			break;
		default:
			formula += "*" + list.get(index);
			result *= list.get(index++);
			
			break;
		}
		return result;
	}


}
