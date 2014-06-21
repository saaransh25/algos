class dmatrix {
	public static void main(String[] args) {
		int n=4,m=3;
		int[][] a=new int[n][m];
		for (int i=0;i<n;i++) 
			for (int j=0;j<m;j++)
				a[i][j]=1;


		for (int i=0;i<m+n-1;i++) {
			if (i<Math.min(m,n)) {
				for (int j=0;j<=i;j++) {
					System.out.print(a[i-j][j]+" ");
				}
			}
			else {
				if (m>=n) {
				  for (int j=n-1;j>=0;j--) {
				  	if ((i-j)==m) break;
						System.out.print(a[j][i-j]+" ");
				  }
				}
				else {
				  for (int j=0;j<m;j++) {
				  		if ((i-j)>=n) continue;
						System.out.print(a[i-j][j]+" ");
				  }				
				}
			}
			System.out.print("\n");
		}
	}
}