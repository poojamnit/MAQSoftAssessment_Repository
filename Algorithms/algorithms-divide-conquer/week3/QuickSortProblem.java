package week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSortProblem {
	private static int arr[] = new int[10000]; 
	private static long comparCnt = 0;
	
	public static int medianElmPivot(int s, int e) {
		int pIndex = -1;
		int mid = ((e - s) / 2) + s;
		int brr[] = new int[3];
		brr[0] = arr[s];brr[1] = arr[mid];brr[2] = arr[e];
		Arrays.sort(brr);
		if (brr[1] == arr[s]) pIndex = s;
		else if(brr[1] == arr[mid]) pIndex = mid;
		else pIndex = e;
		int temp = arr[s];
		arr[s] = arr[pIndex];
		arr[pIndex] = temp;
		return s;
	}
	
	public static int partition(int s, int e, int pIndex) {
		comparCnt += (long)(e - s);
		int i = s;
		int pivot = arr[pIndex];
		for (int j = s; j <= e; j++) {
			if (arr[j] < pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i];
		arr[i] = pivot;
		arr[pIndex] = temp;
		return i;
	}

    public static void quicksort(int start, int end) {
		if (end - start == 1) {
			if (arr[start] > arr[end]) {
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
			}
			comparCnt ++;
		}else if(end <= start) {
		}
		else {
			int pIndex = start;
		//	int pIndex = lastElmPivot(start, end);
		//	int pindex = medianElmPivot(start, end);
			int index = partition(start, end, pIndex);
			quicksort(start, index - 1);
			quicksort(index + 1, end);
		}
	}

  public static int lastElmPivot(int s, int e) {
		int temp = arr[s];
		arr[s] = arr[e];
		arr[e] = temp;
		return s;
	}
	
    
	public static void main(String[] args) {
		File file = new File("G:\\Eclipse Workspace\\java-eclipse-workspace\\algorithms\\src\\week3\\quicksort.txt");
		int cnt = 0;
		 Scanner sc = null;
		try {
			sc = new Scanner(file);
			 while (sc.hasNextLine()) {
				 arr[cnt] = Integer.parseInt(sc.nextLine());
				 cnt++;
		        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			sc.close();
		}

		quicksort(0, arr.length - 1);
		System.out.println("Total no. of comparsions: "+comparCnt);
	}
}
