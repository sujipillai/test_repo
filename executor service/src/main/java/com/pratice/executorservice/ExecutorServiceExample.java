package com.practice.executorservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceExample {

	public static void main(String[] args) {
		//creating a thread pool of 10 threads
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new Runnable() {
			public void run() {				
				for(int i=0; i<3;i++){
					System.out.println("executor service iteration: current thread: "+Thread.currentThread());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		for(int i=0; i<3;i++){
			System.out.println("main loop iteration: current thread: "+Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		executorService.shutdown();
	}
}
