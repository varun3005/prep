package org.vp.prep.dp.command;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author vpathak
 * Not truly a command pattern as SumJob and MultiplyJob do not use composition to encapsulate client libraries
 * Serves as an example of useful application of command pattern
 *
 */

public class TestCommandPattern {

	private Queue<Job> jobs = new LinkedBlockingDeque<>();
	
	public void createJobs(){
		for(int i = 0;i<100;i++){
			if(i%2==0){
				jobs.add(new SumJob(i, i+5));
			}else{
				jobs.add(new MultiplyJob(i, 10));
			}
		}	
	}
	
	public void executeJobs(){
		ExecutorService ex = Executors.newFixedThreadPool(10);
		while(!jobs.isEmpty()){
			Job job = jobs.poll();
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					if(job!=null){
						job.execute();
					}
				}
			};
			ex.execute(runnable);
		}
		
		try{
			ex.shutdown();
			ex.awaitTermination(1, TimeUnit.SECONDS);
		}catch(InterruptedException e){
			
		}finally{
			if (!ex.isTerminated()) {
				System.out.println();
				ex.shutdownNow();
			}
		}
	}
	
	public static void main(String[] args) {
		TestCommandPattern tcp = new TestCommandPattern();
		tcp.createJobs();
		System.out.println("Created jobs:"+ tcp.jobs.size());
		tcp.executeJobs();
	}
}
