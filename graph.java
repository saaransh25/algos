import java.util.*;

/* This is the adjacency list representation */
class vertex {
		double val;
		vertex parent;
		double level;
		ArrayList<vertex> neighbours;
		public vertex(double v) {
			val=v;parent=null;
			neighbours=new ArrayList<vertex>();
			level=-1;
		}
		public void addneighbour(vertex e) {
			neighbours.add(e);
		}
}
public class graph {


	private ArrayList<vertex> V;
	public boolean directed;
	private HashMap<vertex,vertex> parent;
	public HashMap<vertex,Integer> entry;
	public HashMap<vertex,Integer> exit;
	public int t=0;

	public graph() {
		directed=true;
		V=new ArrayList<vertex>();
	    parent=new HashMap<vertex,vertex>();
	    entry=new HashMap<vertex,Integer>();
	    exit=new HashMap<vertex,Integer>();	    
	}	

	/* Graph is a collection of vertices */
	public static void main(String[] args) {
		graph g=new graph();
		/*vertex v1=new vertex(1);
		vertex v2=new vertex(2);
		vertex v3=new vertex(3);
		vertex v4=new vertex(4);
		vertex v5=new vertex(5);
		vertex v6=new vertex(6);
		vertex v7=new vertex(7);
		vertex v8=new vertex(8);
		g.addvertex(v1);
		g.addvertex(v2);
		g.addvertex(v3);
		g.addvertex(v4);
		g.addvertex(v5);
		g.addvertex(v6);
		g.addvertex(v7);
		g.addvertex(v8);
		g.addedge(v1,v2);
		g.addedge(v2,v3);
		g.addedge(v3,v4);
		g.addedge(v4,v2);	
		g.addedge(v1,v5);
		g.addedge(v5,v6);
		g.addedge(v6,v8);
		g.addedge(v6,v7);	
		g.addedge(v6,v3);
		g.addedge(v1,v8);	*/
		//g.bfs(v1);
		//g.printallpaths(v1,v2);	
		//g.fillparentmap(v1);
		/*ArrayList<ArrayList<vertex>> k=g.findallpaths(v1,v8);
		for (ArrayList<vertex> l:k) {
			System.out.println("New Path");
			for (vertex f:l) System.out.print(f.val+":");
			System.out.println(" ");
		}*/
		/*g.addedge(v1,v2);
		g.addedge(v3,v2);
		g.addedge(v3,v1);
		g.addedge(v2,v4);
		g.addedge(v4,v5);
		g.addedge(v6,v5);
		g.addedge(v2,v6);
		g.addedge(v6,v7);
		//g.bfs(v1);*/
		vertex v1=new vertex(1);
		vertex v2=new vertex(2);
		vertex v3=new vertex(3);
		vertex v4=new vertex(4);
		vertex v5=new vertex(5);
		vertex v6=new vertex(6);
		/*vertex v7=new vertex(7);
		vertex v8=new vertex(8);*/
		g.addvertex(v1);
		g.addvertex(v2);
		g.addvertex(v3);
		g.addvertex(v4);
		g.addvertex(v5);
		g.addvertex(v6);
		/*g.addvertex(v7);
		g.addvertex(v8);*/
		g.addedge(v1,v2);
		g.addedge(v1,v3);
		g.addedge(v1,v6);
		g.addedge(v2,v4);	
		g.addedge(v2,v3);
		g.addedge(v3,v4);
		g.addedge(v3,v6);
		g.addedge(v4,v5);	
		g.addedge(v6,v5);
		g.dfs();	

	}


	public Stack<vertex> path;
	public HashSet<vertex> onpath;
	public void printallpaths(vertex s, vertex v) {
		path=new Stack<vertex>();
		onpath=new HashSet<vertex>();
		printpath(s,v);

	}

	public void printpath(vertex s, vertex v) {
		path.push(s);
		onpath.add(s);

		if (s==v) {
			System.out.println("New Path");
			for (vertex e:path) System.out.println(e.val);
		}
		else {
			for (vertex e:s.neighbours) {
				if (!onpath.contains(e)) printpath(e,v);
			}
		}

		path.pop();
		onpath.remove(s);


	}

/*
// This function takes source vertex and destination vertex as input and returns a list of all the paths from source to vertex
//The method  below is a dynamic programming solution to the problem


	public HashMap<vertex, ArrayList<vertex>> parentmap;

	public void fillparentmap(vertex s) {
		parentmap=new HashMap<vertex, ArrayList<vertex>>();
		pathmap=new HashMap<vertex, ArrayList<ArrayList<vertex>>>();
		s.level=0;
		Queue<vertex> frontier=new LinkedList<vertex>();
		int level=0;
		frontier.add(s);
		ArrayList<vertex> a=new ArrayList<vertex>();
		parentmap.put(s,a);
		while (!frontier.isEmpty()) {
			vertex v=frontier.remove();
			for (vertex u:v.neighbours) {
				if (u.level==-1) {//It means u is not explored yet
					u.level=v.level+1;
					ArrayList<vertex> newarr=new ArrayList<vertex>();
					newarr.add(v);
					parentmap.put(u,newarr);
					frontier.add(u);
				}
				else parentmap.get(u).add(v);
			}
		}	
		
//	for (vertex key:parentmap.keySet()) {
			System.out.println("for key  "+key.val);
			for (vertex i:parentmap.get(key)) {
				System.out.println(i.val);
			}
//		}	
	}
	public HashMap<vertex, ArrayList<ArrayList<vertex>>> pathmap;
	public ArrayList<ArrayList<vertex>> findallpaths(vertex s, vertex v) {
		if (v==s) {
			ArrayList<ArrayList<vertex>> finallist=new ArrayList<ArrayList<vertex>>();
			ArrayList<vertex> innerList=new ArrayList<vertex>();
			innerList.add(s);
			finallist.add(innerList);
			pathmap.put(s,finallist);
			return finallist;
		}

		if (pathmap.containsKey(v)) return pathmap.get(v);

		else {
			ArrayList<ArrayList<vertex>> finallist=new ArrayList<ArrayList<vertex>>();
			for (vertex u:parentmap.get(v)) {
				ArrayList<ArrayList<vertex>> subpath=findallpaths(s,u);
				for (ArrayList<vertex> innerList:subpath) {
					if (!innerList.contains(v))
						innerList.add(v);
				}
				if (subpath!=null) finallist.addAll(subpath);

			}

			pathmap.put(v,finallist);
			return finallist;
		}
		for (vertex u:s.neighbours) {
			if (!parent.containsKey(u)) {//it means it is not explored yet
				parent.put(u,v);
				//System.out.println(u.val);				
				dfsvisit(u);
				//These edges are tree edges
			}
		}

	} 

*/





	/* Breadth first search starting from s*/
	public void bfs(vertex s) {
		s.parent=null;
		s.level=0;
		Queue<vertex> frontier=new LinkedList<vertex>();
		int level=0;
		frontier.add(s);
		while (!frontier.isEmpty()) {
			vertex v=frontier.remove();
			for (vertex u:v.neighbours) {
				if (u.level==-1) {//It means u is not explored yet
					System.out.println(u.val);
					u.parent=v;
					u.level=v.level+1;
					frontier.add(u);
				}
			}
		}

	}

	
	/* Not possible to do topological stack with iterative version */
	public void dfsstack() {
		Stack<vertex> frontier=new Stack<vertex>();

		for (vertex v:V) {
			if (!parent.containsKey(v)) {
				frontier.push(v);
				parent.put(v,null);
				while (!frontier.isEmpty()) {
					vertex u=frontier.pop();
										//System.out.println(u.val);
					for (vertex e:u.neighbours) {
						if (!parent.containsKey(e)) {
							frontier.push(e);

							parent.put(e,u);
						}
					}
				}
			}
		}
		//while (!topsort.isEmpty()) System.out.println(topsort.remove().val);
	}

	public Stack<vertex> topsort=new Stack<vertex>();;
	public void dfs() {
		for (vertex v:V) {
			if (!parent.containsKey(v)) {
				parent.put(v,null);
				entry.put(v,t++);
				//System.out.println(v.val);
				dfsvisit(v);
				exit.put(v,t++);
			}
		}
		while (!topsort.isEmpty()) System.out.println(topsort.pop().val);
	}

	public void dfsvisit(vertex v) {

		for (vertex u:v.neighbours) {
			if (!parent.containsKey(u)) {//it means it is not explored yet
				parent.put(u,v);
				entry.put(u,t++);
				//System.out.println(u.val);				
				dfsvisit(u);
				exit.put(u,t++);
				//These edges are tree edges
			}
			else {
				System.out.println("Edge "+v.val+" to "+u.val);
				if (!exit.containsKey(u)) System.out.println("Backedge");
				else {
					if (entry.get(v)<exit.get(u)) System.out.println("Forward edge"); 
					if (entry.get(v)>exit.get(u)) System.out.println("Cross edge");
				}
			}
		}
		//For topological sort
		topsort.push(v);
	}



	public void addvertex(vertex v) {
		V.add(v);
	}
	public void addedge(vertex v, vertex u) {
		v.addneighbour(u);
		if (directed==false) u.addneighbour(v);
	}




}