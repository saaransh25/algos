import java.util.List;

import java.io.*;

class Vertex {
	public int name;
	public List<Edge> adj;
	public void addname(int argname) {name=argname;}
	public void addEdge(Edge e) {adj.add(e);}
	public int value() {return name;}
	public double minDistance=Double.POSITIVE_INFINITY;
	public Vertex previous;
}

class Edge {
	public Vertex target;
	public void Edge(Vertex target) {this.target=target;}
}

class maze {
	public static void main (String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//		String mazeinput=br.readLine();
		String mazeinput=" ___________________ \n|_  |  ___     _   _|\n| | |  _|___| |_  | |\n|  _____|_  |  _|   |\n| | |  _  |  _|_  | |\n|___| | | |  _  | | |\n| |_  |  _____| | |_|\n| |___| |  _|   |_  |\n|     | |___  |_  | |\n|_| | |  _  |_| |_| |\n|___|___|_______|___|\n";
		String[] mazegraph=mazeinput.split("\\\\n");
		for (int i=0;i<mazegraph.length;i++) System.out.println(mazegraph[i]);
		

/*
		int mazecolumns=10, mazerows=10;
		
		int node=0;
		Vertex[] mazegraph=new Vertex[mazecolumns*mazerows];
		for (int it1=0;it1<mazerows;it1++) {
			for (int it2=0;it2<mazecolumns;it2++) {
				 vertex.addname(node);
				 node++;
			}
		}

		node=0;
		int i=mazerows; //Skips the first row
		
		while (true) {
			if (mazeinput[i]==' ' || mazeinput[i]=='_' ) {
					
					if (mazeinput[i+1]!='|') {
						mazegraph[node].addEdge(new Edge(mazegraph[node+1]));
						mazegraph[node+1].addEdge(new Edge(mazegraph[node]));
					}

					if (mazeinput[i]==' ') {
						mazegraph[node].addEdge(new Edge(mazegraph[node+mazecolumns]));
						mazegraph[node+mazecolumns].addEdge(new Edge(mazegraph[node]));
					}
						
					node++;
			}
			i=i+2;
		}*/
	}
	
}
		
		