import java.io.*;

public class mergesort {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] a=new int[n];
		for (int i=0;i<n;i++) {
			a[i]=Integer.parseInt(br.readLine());
		}

		int[] c=mergesort(a,0,n-1);
		for (int i=0;i<n;i++) System.out.println(c[i]);
	}

	public static int[] mergesort(int[] a, int startindex, int endindex) {

		if (startindex==endindex)  {int[] k=new int[1]; k[0]=a[startindex]; return k;}
		int mid=(startindex+endindex)/2;
		int[] a1=mergesort(a, startindex, mid);
		int[] a2=mergesort(a, mid+1, endindex);
		int p1=0,p2=0,ctr=0;
		int[] c=new int[a1.length+a2.length];

		while ((p1+p2)<c.length) {
			if (p1>=a1.length) {
				c[ctr]=a2[p2];
				p2++;
			}
			else if (p2>=a2.length) {
				c[ctr]=a1[p1];
				p1++;
			}
			else if (a1[p1]>a2[p2]) {
				c[ctr]=a2[p2];
				p2++;
			} else {
				c[ctr]=a1[p1];
				p1++;
			}
			ctr++;
		}
		return c;
	}

}