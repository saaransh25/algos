import java.util.*;
import java.lang.*;
import java.io.*;

public class textjustification {

	String[] list;
	int maxw;
	public textjustification(String[] li, int l) {
		maxw=l;
		list=li;
	}
	public static void main (String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int length=Integer.parseInt(br.readLine());
		String input=br.readLine();
		String[] list=input.split(" ");
		textjustification t=new textjustification(list, length);
		t.textjustify();
	}


	public void textjustify() {
		newline=new HashMap<Integer,Integer>();
		dpmap=new HashMap<Integer, Double>();
		textjustify(0);
		int i=0;
		while (newline.containsKey(i)) {
			int j=newline.get(i);
			System.out.println();
			for (int k=i;k<j;k++) System.out.print(list[k]+" ");
			i=j;
		}
		//System.out.println("key values");
		//for (Integer k:dpmap.keySet()) System.out.println(k+"d"+dpmap.get(k));
	}

	public HashMap<Integer, Double> dpmap;
	public HashMap<Integer,Integer> newline;
	public double textjustify(int startindex) {//returns badness
		if (startindex==list.length) return 0;
		if (dpmap.containsKey(startindex)) return dpmap.get(startindex);
		double badness=Double.POSITIVE_INFINITY;
		int nextindex=-1;
		int sum=0;
		for (int i=startindex; i<list.length; i++) {
			//System.out.println(list[i].length()+1);
			sum+=list[i].length()+1;

			if (sum<=maxw) {
				double casebadness=Math.pow((maxw-sum),3.0);
				double r=textjustify(i+1);
				if ((casebadness+r)<badness) {
					badness=casebadness+r;
					nextindex=i+1;
				}
				//System.out.println(badness);
			}
		}
		newline.put(startindex, nextindex);
		dpmap.put(startindex,badness);
		return badness;
	}
}