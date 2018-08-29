
package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Inversion {

	static int arr[] = new int[100000];
	
	public static long function2(int st, int ed, int leftst, int lefted, int rightst, int righted) {
		int arr1[] = new int[lefted - leftst + 1];
		int arr2[] = new int[righted - rightst + 1];
		
		int count = 0;
		for (int i = leftst; i <= lefted; i++) {
			arr1[count++] = arr[i];
		}
		count = 0;
		for (int i = rightst; i <= righted; i++) {
			arr2[count++] = arr[i];
		}
		
		int leftPtr = 0, rightPtr = 0;
		long result = 0;
		for (int i = st; i <= ed; i++) {
			if(leftPtr >= arr1.length) {
				arr[i] = arr2[rightPtr++];
				
			}else if(rightPtr >= arr2.length) {
				arr[i] = arr1[leftPtr++];
				
			}else if (arr1[leftPtr] <= arr2[rightPtr]) {
				arr[i] = arr1[leftPtr++];
				
			}else if(arr1[leftPtr] > arr2[rightPtr]) {
				arr[i] = arr2[rightPtr++];
				result = result + arr1.length - leftPtr;
			}
		}
		return result;
	}
	
	public static long function1(int st, int ed) {
		if(ed == st) {
			return 0;
		}else if(ed - st == 1) {
			if (arr[st] > arr[ed]) {
				int temp = arr[st];
				arr[st] = arr[ed];
				arr[ed] = temp;
				return 1;
			}else {
				return 0;
			}
		}else {
			int leftst = st,  righted = ed, mid = ((ed - st) / 2 ) + st;
			long leftInv = function1(leftst, mid);
			long rightInv = function1(mid+1, righted);
			long splitInv = function2(st, ed, leftst, mid, mid+1, righted);
			
			return leftInv + rightInv + splitInv;
		}
	}
	
	public static void main(String[] arrrgs){
		File file = new File("G:\\Eclipse Workspace\\java-eclipse-workspace\\algorithms\\src\\week2\\input.txt");
			int cnt = 0;
			 Scanner sc = null;
			try {
				sc = new Scanner(file);
				 while (sc.hasNextLine()) {
					 arr[cnt ++] = Integer.parseInt(sc.nextLine());
			        }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}finally {
				sc.close();
			}
	System.out.println(function1(0, arr.length-1));
	
	
	}
}