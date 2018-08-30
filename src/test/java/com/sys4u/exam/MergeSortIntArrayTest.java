package com.sys4u.exam;

import org.junit.Before;
import org.junit.Test;

public class MergeSortIntArrayTest {
	private MergeSortIntArray app;
	
	@Before
	public void setup () {
		app = new MergeSortIntArray ();
	}

	@Test
	public void testCollectionSort() throws Exception {
		app.collectionSort();
	}

	@Test
	public void testSortIntArray() throws Exception {
		int arr[] = { 12, 11, 13, 5, 6, 7 };

		System.out.println("Given Array");
		MergeSortIntArray.print(arr);

		MergeSortIntArray ob = new MergeSortIntArray();
		ob.sort(arr, 0, arr.length - 1);

		System.out.println("\nSorted array");
		MergeSortIntArray.print(arr);
	}
}
