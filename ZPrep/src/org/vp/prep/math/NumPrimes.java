package org.vp.prep.math;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author vpathak
 *
 */

public class NumPrimes {
	static int getNumberOfPrimes(int N) {
		int primes=0;
		for(int i=1;i<=N;i+=2){
			if(testPrime(i)){
				primes++;
			}
		}
		return primes;
	}


	private static boolean testPrime(int i) {
		for(int j = 3;j<= Math.sqrt(i);j+=2){
			if(i%j==0){
				return false;
			}
		}
		return true;
	}

	

	public static void main(String[] args) {
		System.out.println(getNumberOfPrimes(100));
		List<Integer> primes = new ArrayList<>();
		primes.add(2);
		System.out.println(getNumberOfPrimes2(100, primes));
	}


	private static List<Integer> getNumberOfPrimes2(int n, List<Integer> primes) {
		for(int i=3;i<=n;i+=2){
			boolean prime = true;
			for(int j:primes){
				if(i%j==0){
					prime = false;
					break;
				}
			}
			if(prime){
				primes.add(i);
			}
		}
		return primes;
	}
}
