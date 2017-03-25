package org.vp.prep.dp.arrays;

public class RotatedArraya {

	public static void main(String[] args) {
		int a[] = new int[] {8,9,10,1,2,3,4,5,6,7};
		RotatedArraya ra = new RotatedArraya();
		int numRotated = ra.findRot(a, 0, a.length - 1);
		System.out.println("Rotated by:" + (numRotated+1));
	}

	private int findRot(int[] a, int start, int end) {
		int mid = (start + end) / 2;
		System.out.println("Start:"+start+" mid:"+mid+ " end:"+end);
		if(mid+1<=end && a[mid]>a[mid+1]){
			return mid;
		}
		if(mid-1>=start && a[mid-1]>a[mid]){
			return mid-1;
		}
		if(a[start]>a[mid]){
			mid = findRot(a,start,mid-1);
		}else if(a[mid]>a[end]){
			mid = findRot(a,mid+1,end);
		}
		return mid;
	}
}

