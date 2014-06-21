public class rotate {
    public void nextPermutation(int[] num) {
        int i=num.length-1;
        int pivot=-1;
        while (i>0) {
            if (num[i]>num[i-1]) {
                pivot=i-1;
                break;
            }
            i--;
        }
        if (pivot==-1) return;
        
        int minvalue=Integer.MAX_VALUE, min=pivot;;
        for (i=pivot+1;i<num.length;i++) {
            if (num[i]>num[pivot] && num[i]-num[pivot]<minvalue) {
                minvalue=num[i]-num[pivot];
                min=i;
            }
        }
        
        swap(num, pivot, min);
        for (i=0;i<num.length;i++)
            System.out.println(num[i]);
        sort(num, pivot+1);
        
        return;
        
        
    }
    
    public void swap (int[] num, int i, int j) {
        int dum=num[i];
        num[i]=num[j];
        num[j]=dum;
    }
    
    public void sort(int[] num, int start) {
        for (int i=start+1; i<num.length;i++) {
            for (int j=i;j>start;j--) {
                if (num[j]<num[j-1]) swap(num,j, j-1);
            }
        }
    }

    public static void main(String[] args) {

        int[] a={2,3,1};
        rotate r=new rotate();
        r.nextPermutation(a);
        for (int i=0;i<a.length;i++)
            System.out.println(a[i]);
    }
}