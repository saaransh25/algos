import java.io.*;
import java.util.*;

public class Convert {
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int num=Integer.parseInt(br.readLine());
		Stack<Character> s=new Stack<Character>();
		int r=num;
		while (r!=0) {
			int dig=r%26;

			char ascii = (char) (dig!=0? (dig+64):90);
			s.push(ascii);
			r=(r-dig)/26;
			System.out.println(r);

		}

		System.out.println();
		while (!s.empty()) System.out.print(s.pop());


	}
}