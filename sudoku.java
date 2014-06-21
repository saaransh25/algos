/* Algo: Check that sum across each row, each column, inside each grid box = 45 for a valid sudoku string.

If the sudoku string is valid, then for a valid case the sum across all rows, columns, inside each box is 
sum of first 9 numbers i.e. 45 
*/


public class sudoku {

	public static boolean isvalidsolution(String str) {
		int strlen=str.length();
		double n=Math.sqrt(strlen);
		if (n!=9) return false;

		char stri=0;
		int[][] c=new int[9][9];
		for (int i=0;i<9;i++) {
			for (int j=0;j<9;j++) {
				int m=(int) str.charAt(stri);
				if (m<=48 || m>57) return false;//Check for characters other than 1-9
				c[i][j]=m-48;
				stri++;
			}
		}		

		if (!checkrows(c)) return false;
		if (!checkcolumns(c)) return false;

		for (int i=0;i<9;i=i+3) {
			for (int j=0;j<9;j=j+3) {
				if (!checkboxes(c,i,j)) return false;
			}
		}

		return true;

	}

	/* Check validity for all rows */
	public static boolean checkrows(int[][] a) {
		int sum=0;	
		for (int i=0;i<9;i++) {
			sum=0;
			for (int j=0;j<9;j++) {
				sum+=a[i][j];
			}
			if (sum!=45) return false;//If sum does not equal 45, sudoku is invalid
		}
		return true;
	}

	/* Check validity for all columns */
	public static boolean checkcolumns(int[][] a) {
		int sum=0;	
		for (int i=0;i<9;i++) {
			sum=0;
			for (int j=0;j<9;j++) {
				sum+=a[j][i];
			}
			if (sum!=45) return false;//If sum does not equal 45, sudoku is invalid
		}
		return true;

	}

		/* Check validity for all boxes */
	public static boolean checkboxes(int[][] a, int starti, int startj) { //we pass starting index for all boxes
		int sum=0;	
		for (int i=starti;i<starti+3;i++) {
			for (int j=startj;j<startj+3;j++) {
				sum+=a[i][j];
			}
		}
		if (sum!=45) return false;//If sum does not equal 45, sudoku is invalid
		return true;
	}

	public static void main(String[] args) {
		//Sample Test Cases

		System.out.println(isvalidsolution("751843926893625174642179583425316798176982345938754612364297851289531467517468239"));
		System.out.println(isvalidsolution("751843927893625174642179583425316798176982345938754612364297851289531467517468239"));
		System.out.println(isvalidsolution("571843926893625174642179583425316798176982345938754612364297851289531467517468239"));
		System.out.println(isvalidsolution("851743926693825174142679583425316798976182345738954612364297851289531467517468239"));	
		System.out.println(isvalidsolution("851743926693825174142679583425316798976182345738954612364297851289531467517468239"));	
		System.out.println(isvalidsolution("7518439268936251746421795834253167981769823459387546123642978512895314675174682391"));
		System.out.println(isvalidsolution("0751843926893625174642179583425316798176982345938754612364297851289531467517468239"));
	}

}