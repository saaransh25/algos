import java.lang.*;
public class permutation {
	
	public static void main (String[] args) {
		char[] str=new char[args[0].length()];
		for (int i=0;i<str.length;i++) {
			str[i]=args[0].charAt(i);
		}

		permute(str,0);

	}
// For permutation with duplicates, before swapping check if a[i] & a[j] are different
	public static void permute(char[] str,int i) {
		if (i==str.length) {System.out.println(str); return;}
		for (int j=i;j<str.length;j++) {
			if (str[i]!=str[j] || i==j) {
			swap(str,i,j);
			permute(str,i+1);
			swap(str,j,i);
			}
		}
	}

	public static void swap(char[] str, int i, int j) {
		char a=str[i];
		str[i]=str[j];
		str[j]=a;
	}
}