package org.vp.prep.ds.arrays;

public class AllSorts {

	public static void main(String args[]){
		//int a[] = new int[]{7,9,5,1,2,3,8,4,6};
		int a[] = new int[]{16,4,10,14,7,9,3,2,8,1};
		AllSorts sort = new AllSorts();

//		sort.bubble(a);
//		sort.insertion(a);
//		sort.selection(a);
		sort.quick(a, 0, a.length-1);
//		sort.heapSort(a);
		sort.display(a);
	}
	
	public void bubble(int a[]){
		for(int i=0;i<a.length;i++){
			for(int j=0;j<a.length;j++){
				if(a[i]<a[j]){
					int temp = a[i];
					a[i]=a[j];
					a[j]=temp;
				}
			}
		}
	}
	
	public void insertion(int a[]){
		for(int i=1;i<a.length;i++){
			int key = a[i];
			int j = i-1;
			while(j>=0 && a[j]>key){
				a[j+1] = a[j];
				j--;
			}
			a[j+1]=key;
		}
	}
	
	public void selection(int[] a) {
	      int i, j, minIndex, tmp;
	      int n = a.length;
	      for (i = 0; i < n - 1; i++) {
	            minIndex = i;
	            for (j = i + 1; j < n; j++)
	                  if (a[j] < a[minIndex])
	                        minIndex = j;
	            if (minIndex != i) {
	                  tmp = a[i]; a[i] = a[minIndex]; a[minIndex] = tmp;
	            }
	      }
	}
	
	//---------- Quick Sort Start ----------------//
	void quick(int a[],int p,int r){
		if(p<r){
			int q = partition (a,p,r);
			quick(a,p,q-1);
			quick(a,q+1,r);
		}
	}
	
	public int partition(int[] arr,int p,int r){
		int x = arr[r];
		int i = p-1;
		for(int j=p;j<r;j++){
			if(arr[j]<=x){
				i++;
				swap(arr,i,j);
			}
		}
		swap(arr, i+1,r);
		return i+1;
	}
	
	private void swap(int[] a, int i, int j){
		int temp = a[i];
		a[i]=a[j];
		a[j]=temp;
	}
	//---------- Quick Sort End ----------------//
	
	
	//---------- Heap Sort Start ---------------//
	
	public void heapify(int a[],int i, int n){
		int left = 2*i;
		int right = (2*i)+1;
		int largest;
		if(left <= n && a[left] > a[i]){
			largest = left;
		}else{
			largest = i;
		}
		if(right <= n && a[right] > a[largest]){
			largest = right;
		}
		if(i!=largest){
			int tmp = a[i]; a[i]=a[largest]; a[largest]=tmp;
			heapify(a,largest,n);
		}
	}
	
	 public void buildMaxHeap(int a[], int n){
		 for(int i=n/2;i>=0;i--){
			 heapify(a, i, n);
		 }
	 }
	 
	 public void heapSort(int a[]){
		 buildMaxHeap(a,a.length-1);
		 for(int i=a.length-1;i>=0;i--){
			// printHeap(a);
			 int tmp = a[0]; a[0]=a[i];a[i]=tmp;
			 heapify(a,0,i-1);
		 }
		 System.out.println();
	 }
	 
	 public void printHeap(int a[]){
		 System.out.println("\nHeap--- ");
		 for(int i=0;i<=a.length-1;i++){
			 System.out.print(a[i]);
			 if(i==0 || i==2 || i==6)System.out.println();
		 }
		 System.out.println("\n-----\n");
	 }
	//---------- Heap Sort End   ---------------//
	
	
	public void display(int a[]){
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+",");
		}
	}
	
}
