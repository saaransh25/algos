import static java.lang.Math.sqrt;
import java.util.HashMap;

public class largestpalin {
	
	public static void main (String[] args) {
		
		long startTime=System.currentTimeMillis();
		int[] digits=new int[15];
		long palin;

		HashMap h=new HashMap();
		for (int n=0;n<100;n++) h.put(((n*n)%100),0);

		for (long i=99999999; i>=10000000; i--) {
			long dup=i;
			int digindex=7;
			palin=0;
			while (dup!=0) {
				digits[digindex]=(int)dup%10;
				digits[14-digindex]=digits[digindex];
				dup=dup/10;
				digindex--;
			}
			long pow=1;
			
			int last2=digits[14]+digits[13]*10;
			if (h.get(last2)==null) continue;

			for (int j=14;j>=0;j--) {
				palin=palin+digits[j]*pow;
				pow=pow*10;
			}

			long proot=(long) Math.sqrt(palin);
			if ((proot*proot)==palin) {
				System.out.println(palin);
				break;
			}			
		}
		long endTime=System.currentTimeMillis();
		long searchTime=endTime-startTime;
		System.out.println(searchTime);
	}
}
			
			
			