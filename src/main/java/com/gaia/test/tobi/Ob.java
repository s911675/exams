package com.gaia.test.tobi;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ob {
	/*
	 * 1. Complete ????
	 * 2. Error : 복구가능한 오류 WIFI 가 끊김.
	 * 
	 * Observable 패턴을 확장한 것의 한 축이 Reactive 이다.
	 * 
	 * ReactiveX, Reactive-Stream
	 */
	
	
	static class IntObservable extends Observable implements Runnable {
		public void run() {
			for (int i = 0 ;i<10;i++) {
				setChanged();
				notifyObservers(i);
			}
		}
	}
	
	public static void main(String[] args) {
		Observer ob = new Observer () {
			public void update(Observable o, Object arg) {
				System.out.println(Thread.currentThread().getName() + " || " + arg);
			}
		};

		IntObservable io = new IntObservable();
		io.addObserver(ob);
		
		ExecutorService es = Executors.newSingleThreadExecutor();
		es.execute(io);
		
		System.out.println(Thread.currentThread().getName() + "  Exit");
		es.shutdown();
	}
	
}
