package org.vp.prep.mt;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Executor {

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(10);
		List<CallableTask> tasks = new ArrayList<>();
		List<Future<Integer>> results = new ArrayList<>();
		System.out.println(Instant.now() + " Start submitting");
		for (int i = 1; i <= 5; i++) {
			CallableTask task = new CallableTask(i);
			tasks.add(task);
			results.add(ex.submit(task));
		}
		System.out.println(" not waiting for execution");
		for (Future<Integer> result : results) {
			try {
				System.out.println(Instant.now() + " - " + result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n---------NOW using invoke all to submit all tasks at once ---------------\n");
		System.out.println(Instant.now() + " Start submitting");
		try {
			results = ex.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Waiting for execution");
		for (Future<Integer> result : results) {
			try {
				System.out.println(Instant.now() + " - " + result.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n-----------NOW using invoke any -------------\n");
		try {
			System.out.println(ex.invokeAny(tasks));
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		try {
			ex.shutdown();
			ex.awaitTermination(5, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (!ex.isTerminated()) {
				System.out.println("Killing all now");
				ex.shutdownNow();
			}
		}
	}

}
