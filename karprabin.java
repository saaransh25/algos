import java.util.*;

import java.lang.*;


public class karprabin {
    public class rollinghash {
        int hash;
        Queue<Character> hashstr;
        int Q=Integer.MAX_VALUE;//Large Random prime number
        int pow;
        int b=256;
        public rollinghash (String str) {
            hashstr=new LinkedList<Character>();
            hash=0;
            pow=1;
            for (int i=0;i<str.length();i++) {
                hash=(b*hash+str.charAt(i))%Q;
                hashstr.add(str.charAt(i));
                pow=pow*b;
            }
            pow=pow/b;
        }
        
        public void append(char a) {
           char r=hashstr.remove();
           hash=(hash-r*pow)%Q;
           hashstr.add(a);
           hash=(hash*b+a)%Q;

        }

        public String toStr() {
        	String str="";
        	for (Character a:hashstr) str=str+a;
        	return str;
        }
    }
    
    public int strStr(String haystack, String needle) {
        if (haystack==null || needle==null || needle.length()>haystack.length()) return -1;
        if (needle.equals(haystack)) return 0;


        rollinghash nh=new rollinghash(needle);
        int needlehash=nh.hash;
		rollinghash hh=new rollinghash(haystack.substring(0,needle.length()));

        
        
        for (int i=needle.length();i<haystack.length();i++) {
            hh.append(haystack.charAt(i));
            if (hh.hash==needlehash) {
             	if (hh.toStr().equals(needle)) return i-needle.length()+1;               
            }
        }
        return -1;
    }

    public static void main (String[] args) {
    	karprabin m=new karprabin();
    	System.out.println(m.strStr(args[1],args[0]));
    }
}