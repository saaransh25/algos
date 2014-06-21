import java.io.*;
import java.lang.*;
import java.util.*;
public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String chr=br.readLine();
        String[] li=chr.split(" ");
        String str=br.readLine(); 
        int n=li.length;
        int wl=li[0].length();
        HashMap<String,Integer> wmap=new HashMap<String,Integer>();


        for (int i=0;i<n;i++) {
            if (wmap.containsKey(li[i])) {
                int value=wmap.get(li[i]);
                wmap.put(li[i],value+1);
            }
            else wmap.put(li[i],1);
        }

        int startindex=-1;
        for (int j=0;j<str.length();j++) {
            String substr=str.substring(j,Math.min(str.length(),j+n*wl));
            if (substr.length()<n*wl) break;
            startindex=j;
            //System.out.println(substr);
            for (int i=0;i<n;i++){
                String word=substr.substring(i*wl,i*wl+wl);
                //System.out.println(word);
                if (wmap.containsKey(word)) {
                    //System.out.println(word);
                    int value=wmap.get(word);
                   // System.out.println(value);
                    if (value>0) {
                        value=value-1;
                        wmap.put(word,value);
                    }
                    else {
                        startindex=-1;
                        break;
                    }
                }
                else {
                        startindex=-1;
                        break;
                }
               // for (String key:wmap.keySet()) {System.out.println(key+wmap.get(key));}
            }

            for (String key:wmap.keySet()) {wmap.put(key,0); }
            for (int i=0;i<n;i++) {
                if (wmap.containsKey(li[i])) {
                    int value=wmap.get(li[i])+1;
                    wmap.put(li[i],value);
                }
                else wmap.put(li[i],1);
            }

            if (startindex!=-1) break;
        }

        System.out.println(startindex);



    }
}