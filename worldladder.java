import java.util.*;
import java.lang.*;
public class worldladder {
    public int ladderLength(String start, String end, HashSet<String> dict) {
        dpmap=new HashMap<String,Integer>();
        int min=Integer.MAX_VALUE;
        for (int i=0;i<start.length();i++) {
            int k=ladderLength(start,end,dict,i);
            if (k>0)
                min=Math.min(k,min);
        }
        return (min==Integer.MAX_VALUE)?0:min;
    }
    public HashMap<String,Integer> dpmap;
    public int ladderLength(String start, String end, HashSet<String> dict, int stindex) {
        if (dpmap.containsKey(start)) return dpmap.get(start);
        if (start.equals(end)) {return 1;}
        if (stindex>=start.length()) {return Integer.MIN_VALUE;}
        int min=Integer.MAX_VALUE;
        int b=(int) start.charAt(stindex);
        //System.out.println(a);
        for (int i=0;i<26;i++) {
            int a=b+i;
            if (a>122) a=97;
            //System.out.println((char) a);
            String str=((stindex>0)?start.substring(0,stindex):"")+((char) a)+((stindex!=(start.length()-1))?start.substring(stindex+1,start.length()):"");
            if (dict.contains(str)) {
                int k=ladderLength(str,end,dict,stindex+1);
                if (k>0) {
                    min=Math.min(1+k,min);
                }
            }
        }
        dpmap.put(start,(min==Integer.MAX_VALUE)?0:min);
        return (min==Integer.MAX_VALUE)?0:min;
        
    }
    public static void main (String[] args) {
        worldladder s1=new worldladder();
        HashSet<String> dict=new HashSet<String>();
        dict.add("a");
        dict.add("b");
        dict.add("c");
        System.out.println(s1.ladderLength("a","c",dict));
    }
}