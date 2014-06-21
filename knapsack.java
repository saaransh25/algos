import java.io.*;
import java.lang.*;

public class knapsack {
	public static void main (String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int W=Integer.parseInt(br.readLine());
		int n=Integer.parseInt(br.readLine());
		int[] wt=new int[n+1];
		int[] val=new int[n+1];

		for (int i=1;i<=n;i++) {
			String inp=br.readLine();
			wt[i]=Integer.parseInt(inp.split(" ")[0]);
			val[i]=Integer.parseInt(inp.split(" ")[1]);			
		}

		// @-D Kanpsack of subproblems : sol[i][j] stores wether the optimal solution for ks[i][j] cpntains i;
		int[][] ks=new int[n+1][W+1];
		boolean[][] sol=new boolean[n+1][W+1];

		for (int i=0;i<=n;i++) {
			for (int j=0;j<=W;j++) {
				if (i==0 || j==0) ks[i][j]=0;
				else {
					int option1=ks[i-1][j];//dont include i'th item
					int option2=Integer.MIN_VALUE;
					if (j>=wt[i]) {
						option2=val[i]+ks[i-1][j-wt[i]];
					}
					ks[i][j]=Math.max(option1,option2);
					
						if (option1>option2) sol[i][j]=false;
						else sol[i][j]=true;
					
				}
			}
		}



		boolean[] take=new boolean[n+1];
		for (int i=n, j=W;i>=1;i--) {
			if (sol[i][j]) {take[i]=true; j=j-wt[i];}
			else take[i]=false;
		}
		for (int i=1;i<=n;i++)
		System.out.println(take[i]);




	}



}