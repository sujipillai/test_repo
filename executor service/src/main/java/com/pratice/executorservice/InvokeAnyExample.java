package com.practice.executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class InvokeAnyExample {
	public static void main(String[] args) {
		Callable<String> callableTask = () -> {
			System.out.println("Current thread: "+Thread.currentThread());
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task's execution";
		};
		
		Callable<String> callableTask1 = () -> {
			System.out.println("Current thread: "+Thread.currentThread());
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task 1 execution";
		};
		
		Callable<String> callableTask2 = () -> {
			System.out.println("Current thread: "+Thread.currentThread());
			TimeUnit.MILLISECONDS.sleep(300);
			return "Task 2 execution";
		};
		
		List<Callable<String>> callableList=new ArrayList<>();
		callableList.add(callableTask);
		callableList.add(callableTask1);
		callableList.add(callableTask2);
		
		ExecutorService executorService=Executors.newFixedThreadPool(5);
		
		try {
			String  result= executorService.invokeAny(callableList);
			System.out.println(result);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		executorService.shutdown();
	}

}
