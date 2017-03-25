package org.vp.prep.dp.arrays;


public class ArrayBased {
	
	public static void main(String args[]){
		ArrayBased ab = new ArrayBased();
		
//		int a[] = new int[]{1,1,1,2,3,3,4,5,5,5,6,7,8,8};
//		int n = ab.removeDuplicates(a);
//		ab.display(a,n);
		
//		System.out.println(ab.isDivBy3(327));
		
//		System.out.println(ab.isPowerOfTwo(1024));
		
		char[]a = new char[]{'a','b','c'};
//		System.out.println("\n Permutations / Anagrams");
		ab.permute(a,3);
//		System.out.println("\n Combinations");
//		ab.genCombinations(a);
		
//		int a[] = new int[]{4,3,1,7,10,8,14,1,6,8};
//		System.out.println("\nMaxSubsum="+ab.maxAlternateSubArray(a));
		
	}
	
	public void maxSubArray(int a[]){
		int start =0,end=a.length-1,maxSum=0,sum=0;
		for(int i=0;i<a.length;i++){
			sum+=a[i];
			if(sum<0){
				if(!(i+1==a.length)){ // i is not the last element
					start = i+1;
				}
				sum=0;
			}
			if(sum>maxSum){
				maxSum =sum;
				end = i;
			}
		}
		System.out.println("Initial index:"+start+" End Index:"+end+" MaxSum:"+maxSum);
	}
	
	public int maxAlternateSubArray(int a[]){
		int tmp[] = new int[a.length];
		int max = a[0];
		tmp[0]= a[0];
		for(int i=1;i<a.length;i++){
			if(i==1){
				if(a[i]>max){
					max = a[i];
				}
			}else {
				if(tmp[i-2]+a[i]>max){
					max = tmp[i-2]+a[i];
				}
			}
			tmp[i]=max;
		}
		for(int i=0;i<a.length;i++){
			System.out.print(tmp[i]+", ");
		}
		
		return tmp[tmp.length-1];
	}
	
	public int removeDuplicates(int a[]){ //Removes duplicates from a sorted array
		int j=0;
		for(int i=0;i<a.length-1;i++){
			if(a[i]!=a[j]){
				a[++j]=a[i];
			}
		}
		return j+1;
	}
	
	public boolean isDivBy3(int a){ // To test if a number is divisible by 3 or not
		int sum = 0;
		while(a>0){
			sum+=a%10;
			a/=10;
		}
		if(sum>9){
			isDivBy3(sum);
		}
		if(sum==3||sum==6||sum==9){
			return true;
		}
		return false;
	}

	
	public boolean isPowerOfTwo(int a){ // To count set bits and determine weather number is a power of 2
		//return (a&(~a+1))==a;
		int setBits = 0;
		while(a>0){
			setBits+=a&1;
			a=a>>1;
		}
		System.out.println("Set Bits:"+setBits);
		if(setBits==1){
			return true;
		}
		return false;
	}

	
	//------------------To generate all combinations of char array------------------------
	public void genCombinations(char[] a){
		int n = a.length;
		for(int i=0;i<Math.pow(2, n)-1;i++){
			System.out.println(getStringEq(getBinary(i,a.length),a));
		}
	}
	
	private String getStringEq(String binary, char[]a) {
		String res="";
		for(int i=0;i<binary.length();i++){
			if(new StringBuffer(binary).charAt(i)=='1'){
				res+=a[i];
			}
		}
		return res;
	}

	public String getBinary(int a,int n){
		String result="";
		while(a>0){
			result =result+a%2;
			a=a/2;
		}
		for(int i=result.length();i<n;i++){
			result+="0";
		}
		//result = result.
		return new StringBuffer(result).reverse().toString();
	}
	
	
	//------------------------------------------
	
	public void permute(char a[], int m){
		if(m==0){
			display(a,a.length);
			System.out.println();
		}else{
			for(int i=0;i<m;i++){
				char temp = a[i];
				a[i] = a[m-1];
				a[m-1] = temp;
				permute(a,m-1);
				temp = a[i];
				a[i] = a[m-1];
				a[m-1] = temp;
			}
		}
	}
	
	
	
	public void display(int a[],int n){
		for(int i=0;i<n;i++){
			System.out.print(a[i]);
		}
	}
	

	public void display(char a[],int n){
		for(int i=0;i<n;i++){
			System.out.print(a[i]);
		}
	}
}
