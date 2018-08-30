package com.sys4u.exam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generic List class with merge-sort implementation
 * 
 * @author Drew Braden
 * @version 7/2/2011
 * @param <T>
 *            type Copyright 2011 Drew Braden
 */

public class GenericMergeSort<T extends Comparable<? super T>> {
	static <T> void printList(List<T> list) {
		if (list == null) {
			return;
		}

		for (T t : list) {
			System.out.println(t.toString());
		}
	}

	public static void main(String[] args) {
		GenericMergeSort<Integer> mergeSort = new GenericMergeSort<>();
		List<Integer> list = Arrays.asList(9, 5, 3, 6, 7, 2);

		System.out.println("Before!!!");
		printList(list);

		System.out.println("After!!!");

		List<Integer> result = mergeSort.mergesort(list);
		printList(result);
	}

	public List<T> mergesort(List<T> ilist) {
		if (ilist.size() <= 1) {
			return ilist;
		} else {
			List<T> leftList = new ArrayList<>();
			List<T> rightList = new ArrayList<>();

			int middle = ilist.size() / 2; // int division
			for (int i = 0; i < middle; i++) {
				leftList.add(ilist.get(i));
			}
			for (int i = middle; i < ilist.size(); i++) {
				rightList.add(ilist.get(i));
			}

			return merge(mergesort(leftList), mergesort(rightList));
		}
	}

	// used by mergesort to do merging
	private List<T> merge(List<T> leftList, List<T> rightList) {
		List<T> mergedList = new ArrayList<>(); // return list
		int leftIndex = 0;
		int rightIndex = 0;

		while (leftIndex + 1 <= leftList.size() || rightIndex + 1 <= rightList.size()) {
			if (leftIndex + 1 <= leftList.size() && rightIndex + 1 <= rightList.size()) {
				if (leftList.get(leftIndex).compareTo(rightList.get(rightIndex)) <= 0.0) {
					mergedList.add(leftList.get(leftIndex));
					leftIndex++;
				} else {
					mergedList.add(rightList.get(rightIndex));
					rightIndex++;
				}
			} else if (leftIndex + 1 <= leftList.size()) {
				mergedList.add(leftList.get(leftIndex));
				leftIndex++;
			} else if (rightIndex + 1 <= rightList.size()) {
				mergedList.add(rightList.get(rightIndex));
				rightIndex++;
			}
		}

		return mergedList;
	}
}