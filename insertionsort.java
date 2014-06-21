import java.io.*;

public class insertionsort {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] a=new int[n];
		for (int i=0;i<n;i++) {
			a[i]=Integer.parseInt(br.readLine());
		}

		for (int i=1;i<n;i++) {
			for (int j=i;j>0;j--) {
				if(a[j]<a[j-1]) {
					int k=a[j];
					a[j]=a[j-1];
					a[j-1]=k;
				}
			}
		}

		for (int i=0;i<n;i++) {	System.out.println(a[i]);}
	}
}