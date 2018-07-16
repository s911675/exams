package com.gaia.test.hanoi;

import java.util.Stack;

public class HanoiTest {
	
	public static void recursiveHanoi () {
		
	}
	
	public static void main(String[] args) {
		
	}
}

class Pillar {
	Stack<Integer> goldenDiskList;
	
	Pillar (int size) {
		goldenDiskList = new Stack<> ();
		
		for (int i=0;i<size; i++) {
			goldenDiskList.add(i);
		}
	}
	
	Boolean isInsertable (int i) {
		if(i > goldenDiskList.pop()) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}
}
