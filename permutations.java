import java.lang.*;
public class permutations {
	
	public static void main (String[] args) {
		char[] str=new char[args[0].length()];
		for (int i=0;i<str.length;i++) {
			str[i]=args[0].charAt(i);
		}

		permute(str,0);

	}

	public static void permute(char[] str,int i) {

		if (i==str.length)
		System.out.println(str);
		for (int j=i;j<str.length;j++) {
			swap(str,i,j);
			permute(str,i+1);
			swap(str,j,i);

		}
	}

	public static void swap(char[] str, int i, int j) {
		char a=str[i];
		str[i]=str[j];
		str[j]=a;
	}
}