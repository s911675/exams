package com.gaia.test.junior;

import java.util.LinkedList;
import java.util.Vector;

public class B01 {
/*
(1)	java.util.Vector 클래스와 java.util.ArrayList 클래스, 그리고 java.util.LinkedList의 차이에 대해 기술하라.
그리고 Java5 이후로 java.util.Vector를 사용하지 말아야 하는 이유에 대해서도 설명하라.

Vector 는 객체들을 Thread safe 하게 관리하는 컨테이너 클래스 이며 배열의 성격을 가지고 있어서 인덱스에 의한 접근이 용이한 반면에..
중간에 데이터를 삽입하는 경우 삽입 위치에 따라서 성능 저하가 일어 날 수 있다. 인덱스를 통한 데이터 접근 및 데이터 삭제,추가가 빈번하지 않은 데이터 저장에 유리하다.
반면에 LinkedList 는 특성 엘리번트 접근을 처음이나 끝에서 찾아야 하는 단점이 있으나 삽입은 용이하다.
데이터 삭제,추가가 빈번한 데이터 저장에 유리하다.

*/
	public static void main(String[] args) {
		Vector<String> vc = new Vector<> ();
		LinkedList<String> ll = new LinkedList<> ();
		
		String e = "asdf";
		vc.add(e);
		vc.addElement(e);
		vc.add(0, e);
	}
	
}
