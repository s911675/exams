package com.gaia.test.stream.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vector<Integer> vec = new Vector<> ();
		
		long start = System.currentTimeMillis();
		for(int i=0,k=100000;i<k;i++) {
			vec.add(i);
		}
		
		vec.add(10, 10);
		
		long end = System.currentTimeMillis();
		System.out.println((end - start)/(double)1000);

		long start2 = System.currentTimeMillis();
		ArrayList<Integer> list = new ArrayList<> ();
		
		for(int i=0,k=100000;i<k;i++) {
			list.add(i);
		}
		long end2 = System.currentTimeMillis();
		System.out.println((end2 - start2)/(double)1000);

		list.add(10, 10);
		
		long start3 = System.currentTimeMillis();
		LinkedList<Integer> list2 = new LinkedList<> ();
		
		for(int i=0,k=100000;i<k;i++) {
			list2.add(i);
		}
		long end3 = System.currentTimeMillis();
		System.out.println((end3 - start3)/(double)1000);

		list2.add(10, 10);
	}

}
