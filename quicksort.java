// Pivot can be a value in the array or some other random value


public class quicksort {
	public static void main (String[] args) {
		//int[] a=new int[10]
		
		int[] a={4,2,3,8,9,41,9,0,-5,-10};
		/*
		quicksort(a, 0, a.length-1);
		for (int i=0;i<a.length;i++) System.out.println(a[i]);*/

		System.out.println(quickselect(a,0,a.length-1, 4));
	}

	/* This algorithm is O(n2) in worst case. To improve worst case performance, divide in n/5 groups
	and then compute median of each of these n/5 groups and then recursively a median of these medians, using median as pivot 
	to do quickselect */
	public static int quickselect(int[] a, int low, int high, int k) {//k is the rank we are loking for
		if (low==high) return a[low];
		int pindex=partition(a, low, high);

		int rankp=pindex-low+1;

		if (rankp==k) return a[pindex];
		if (rankp>k)
			return quickselect(a,low,pindex-1,k);
		else 
			return quickselect(a,pindex+1,high,k-rankp);
	}

	public static void quicksort(int[] a, int low, int high) {
		if (low>high) return;
		int pindex=partition(a, low, high);
		quicksort(a,low,pindex-1);
		quicksort(a,pindex+1,high);
	}

	public static int partition(int[] a, int low, int high) {
		int pivot=a[low];
		int i=low;
		for (int j=i+1;j<=high;j++) {
			if (a[j]<=pivot) {
				i++;
				swap(a,i,j);
			}
		}
		swap(a,low,i);
		return i;
	}
	public static void swap(int[] a, int i, int j) {
		int dum=a[i];
		a[i]=a[j];
		a[j]=dum;
	}


}