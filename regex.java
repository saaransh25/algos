public class regex {
    public boolean isMatch(String s, String p) {
        return isMatch(s,0,p,0);   
    }
    
    public boolean isMatch(String s, int starts, String p, int startp) {
        //if (starts>=s.length() && startp>=p.length()) return true;
        if (starts>=s.length()) return false;
        if (startp>=p.length()) return true;
        
        String str="";
        for (int i=startp;i<p.length();i++) {
            char a=p.charAt(i);
            if (a=='*' || a=='.') {
                int in=starts;
                if (!str.equals(""))  {
                      in=matchstr(s, starts, str);
                      if (in==-1) return false;
                }
                
                if (a=='*') {
                    if(isMatch(s,in+str.length(),p,i+1)) return true;
                } else {
                        if (in+str.length()<s.length())
                           if(isMatch(s,in+str.length()+1,p,i+1)) return true;
                }

                if (str.equals("")) return isMatch(s,in+str.length()+1,p,startp);
                else return isMatch(s,in+str.length(),p,startp);                    
                
            } 
            else str+=a;
        }
        int in=matchstr(s, starts, str);
        if (in==-1) return false; 
        else return true;
    }
    
    public int matchstr(String s, int start, String str) {
        System.out.println(str);
        for (int i=start;i<s.length();i++) {
            String k=s.substring(i,Math.min(s.length(),i+str.length()));
            if (str.equals(k)) return i;
        }
        return -1;
    }

    public static void main (String[] args) {
        regex r=new regex();
        System.out.println(r.isMatch("baccbbcbcacacbbc","ba*b*b*.a*"));
    }
}