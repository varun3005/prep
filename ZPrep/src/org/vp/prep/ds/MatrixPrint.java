package org.vp.prep.ds;

public class MatrixPrint {
	
	boolean search(int a[][],int key,int row,int col){
		int i=0,j=col-1;
		do{
			if(a[i][j]==key){
				System.out.println("Element found at row:"+i+" and col:"+j);
				return true;
			}else if(a[i][j]<key){
				i++;
			}else{
				j--;
			}
		}while(i<row && j>=0);
		
		return false;
	}
	
	public void printHelically(int a[][],int row,int col){
		int imax = row-1,jmax=col-1,imin=0,jmin=0,i=0,j=0;
		if(jmax==0){
			for(int z=0;z<=imax;z++)
				System.out.print(a[z][0]);
			return;
		}
		if(imax==0){
			for(int z=0;z<=jmax;z++)
				System.out.print(a[0][z]);
			return;
		}
		do{
			if(i==imin && j<=jmax){
				System.out.print(a[i][j++]+",");
			}
			if(j==jmax && i <= imax){
				System.out.print(a[i++][j]+",");
			}
			if(i==imax && j>=jmin){
				System.out.print(a[i][j--]+",");
			}
			if(j==jmin && i>imin){
				System.out.print(a[i--][j]+",");
			}
			if(i==imin && j==jmin){
				imin++;
				jmin++;
				imax--;
				jmax--;
				i++;j++;
			}
		}while(i<=imax && j<=jmax && i>=imin && j>=jmin);
	}
	
	public static void main(String args[]){
		int row = 3, col = 4;
		int a[][] = new int[][]{{2, 4, 5, 7},
								{10,13,15,20},
								{12,14,18,22}};
								
		MatrixPrint ms = new MatrixPrint();
		//System.out.println("Element Present:"+ms.search(a,17,row,col));
		ms.printHelically(a, row, col);
	}
}

// T(n) = O(m+n) where m is number of rows and n is number of columns
