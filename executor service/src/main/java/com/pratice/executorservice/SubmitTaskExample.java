package com.practice.executorservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SubmitTaskExample {
	public static void main(String[] args) {

		Runnable runnableTask = () -> {
			
			try {
				System.out.println("Current thread: "+Thread.currentThread());
				TimeUnit.MILLISECONDS.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		};

		Callable<String> callableTask = () -> {
			System.out.println("Current thread: "+Thread.currentThread());
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task's execution";
		};		


		ExecutorService executorService= Executors.newFixedThreadPool(10);

		Future<?> runnableFuture=executorService.submit(runnableTask);

		Future<String>  future= executorService.submit(callableTask);

		for(int i=0; i<3;i++){
			System.out.println("main loop iteration: current thread: "+Thread.currentThread());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			System.out.println("runnable "+ runnableFuture.get());
			System.out.println("callable "+ future.get());
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		
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
