



import java.io.*;

class mazesolver {
	public static String[] maze;
	public static char[][] flag;
	public static String path;
	public static void main (String[] args) throws IOException {		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		String mazeinput=br.readLine();
		String mazeinput=" ___________________ \\n|_  |  ___     _   _|\\n| | |  _|___| |_  | |\\n|  _____|_  |  _|   |\\n| | |  _  |  _|_  | |\\n|___| | | |  _  | | |\\n| |_  |  _____| | |_|\\n| |___| |  _|   |_  |\\n|     | |___  |_  | |\\n|_| | |  _  |_| |_| |\\n|___|___|_______|___|\\n";
		
		maze=mazeinput.split("\\\\n"); 
		for (int i=0;i<maze.length;i++) System.out.println(maze[i]);
		
		path="";			

		flag=new char[maze.length][maze[0].length()];
		for (int i=0;i<flag.length;i++) 
			for (int j=0;j<flag[i].length;j++) flag[i][j]='T';

		if (traverse(1,1)==true){		
			String correctpath="";
			for (int i=path.length()-1;i>=0;i--)
				correctpath+=path.charAt(i);
			System.out.println(correctpath);
		}
		else System.out.println("Maze cannot be solved");

/*		for (int i=0;i<flag.length;i++) {
			for (int j=0;j<flag[i].length;j++)
				System.out.print(flag[i][j]+" ");
			System.out.print("\n");
		}	*/	
	}	
	
	public static boolean traverse(int i, int j) {
	
		if (i==(maze.length-1)	&& j==(maze[maze.length-1].length()-2)) return true;

		if (flag[i][j]=='P') return false;

		flag[i][j]='P';

		if (maze[i-1].charAt(j)!='_') {if (traverse(i-1,j)==true) 
						    {
							path=path+"N";	
							return true;
						    }	
						}

		if (maze[i].charAt(j)!='_') {if (traverse(i+1,j)==true) 
						    {
							path=path+"S";	
							return true;
						    }
					    }

		if (maze[i].charAt(j+1)!='|') {if (traverse(i,j+2)==true) 
						    {
							path=path+"E";	
							return true;
						    }
					      }

		if (maze[i].charAt(j-1)!='|') {if (traverse(i,j-2)==true) 
						    {
							path=path+"W";	
							return true;
						    }
					     }

		flag[i][j]='N';
		return false;
	}
}
		
		