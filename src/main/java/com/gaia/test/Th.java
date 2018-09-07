package com.gaia.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Th {
	public static void threadLongRunning() {
		Runnable runnable = () -> {
			try {
				String name = Thread.currentThread().getName();
				System.out.println("Foo " + name);
				TimeUnit.SECONDS.sleep(1);
				System.out.println("Bar " + name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}

	public static void thread() {
		Runnable task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		};

		task.run();

		Thread thread = new Thread(task);
		thread.start();

		System.out.println("Done!");
	}

	public static void executorService() {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		});

		try {
			System.out.println("attempt to shutdown executor");
			executor.shutdown();
			executor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("tasks interrupted");
		} finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
			}
			executor.shutdownNow();
			System.out.println("shutdown finished");
		}

	}

	public static void callable() {
		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		Integer result;
		try {
			result = future.get();
			System.out.println("future done? " + future.isDone());
			System.out.println("result: " + result);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void executorServiceTimeOut() {
		ExecutorService executor = Executors.newFixedThreadPool(1);

		Future<Integer> future = executor.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		});

		try {
			future.get(1, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void invokeAll() {
		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(() -> "task1", () -> "task2", () -> "task3");

		try {
			executor.invokeAll(callables).stream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static Callable<String> callable(String result, long sleepSeconds) {
		return () -> {
			TimeUnit.SECONDS.sleep(sleepSeconds);
			return result;
		};
	}

	public static void invokeAny() {

		ExecutorService executor = Executors.newWorkStealingPool();

		List<Callable<String>> callables = Arrays.asList(callable("task1", 2), callable("task2", 1),
				callable("task3", 3));

		String result;

		try {
			result = executor.invokeAny(callables);
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// => task2
	}

	public static void scheduledExecutorService() throws InterruptedException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
		ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

		TimeUnit.MILLISECONDS.sleep(1337);

		long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
		System.out.printf("Remaining Delay: %sms", remainingDelay);
	}
	
	public static void scheduleAtFixedRate () {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

		int initialDelay = 0;
		int period = 1;
		executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
	}

	public static void scheduleWithFixedDelay () {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> {
		    try {
		        TimeUnit.SECONDS.sleep(2);
		        System.out.println("Scheduling: " + System.nanoTime());
		    }
		    catch (InterruptedException e) {
		        System.err.println("task interrupted");
		    }
		};

		executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Hello " + Thread.currentThread().getName() + " Started");

		// thread();
		// threadLongRunning();
		// executorService();
		// callable();
		// executorServiceTimeOut();
		// invokeAll();
//		invokeAny();
//		scheduledExecutorService();
//		scheduleAtFixedRate();
		scheduleWithFixedDelay();

		System.out.println("Hello " + Thread.currentThread().getName() + " Ended");
	}
}
