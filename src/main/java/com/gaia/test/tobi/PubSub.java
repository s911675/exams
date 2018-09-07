package com.gaia.test.tobi;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class PubSub {
	public static void subscriber() {
		Publisher p;
		Subscriber<Integer> s;
	}

	public static void main(String[] args) throws Exception {
		Iterable<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		ExecutorService es = Executors.newFixedThreadPool(10);

		Publisher p = new Publisher() {
			public void subscribe(Subscriber subscriber) {
				Iterator<Integer> it = list.iterator();

				subscriber.onSubscribe(new Subscription() {

					public void request(long n) {
						es.execute(() -> {
							int i = 0;
							try {
								while (i++ < n) {
									if (it.hasNext()) {
										subscriber.onNext(it.next());
									} else {
										subscriber.onComplete();
										break;
									}
								}
							} catch (RuntimeException ex) {
								subscriber.onError(ex);
							}
						});
					}

					public void cancel() {
						System.out.println("onSubscribe.cancel");
					}
				});
			}
		};

		Subscriber<Integer> s = new Subscriber<>() {
			Subscription subscription;

			public void onSubscribe(Subscription subscription) {
				System.out.println("onSubscribe");
				this.subscription = subscription;

				subscription.request(Long.MAX_VALUE);
			}

			public void onNext(Integer item) {
				System.out.println("onNext " + item);

				subscription.request(1);
			}

			public void onError(Throwable throwable) {
				System.out.println("onError " + throwable.getMessage());
			}

			public void onComplete() {
				System.out.println("onComplete");
			}
		};

		p.subscribe(s);
		es.shutdown();
	}
}
