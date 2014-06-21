//Arrays are fixed size once instantiated. You can use a List instead. so new int[n] will work.

import java.util.*;
class vertex implements Comparable<vertex> {
		double val;
		vertex parent;
		double level;
		ArrayList<edge> neighbours;
		boolean visited;
		double distance;
		public vertex(double v) {
			val=v;
			parent=null;
			neighbours=new ArrayList<edge>();
			level=-1;
			distance=Double.POSITIVE_INFINITY;
			visited=false;
		}
		public void addneighbour(edge e) {
			neighbours.add(e);
		}

		public int compareTo (vertex other) {
			return Double.compare(distance, other.distance);
		}
}

class edge {
		vertex target;
		double weight;
		public edge(vertex t, double w) {
			target=t;
			weight=w;
		}
}

public class shortestpath {
		public ArrayList<vertex> V;
		public shortestpath() {
			V=new ArrayList<vertex>();
		}

		public void bfs(vertex s) {
			s.parent=null;
			s.level=0;
			Queue<vertex> frontier=new LinkedList<vertex>();
			int level=0;
			frontier.add(s);
			while (!frontier.isEmpty()) {
				vertex v=frontier.remove();

				v.visited=true;
				for (edge e:v.neighbours) {
					vertex u=e.target;
					if (u.level==-1) {//It means u is not explored yet
						u.level=v.level+1;
						frontier.add(u);
					System.out.println("Vertex: "+u.val+" Parent: "+((u.parent!=null)?u.parent.val:"null")+" Distance: "+u.distance);
					}
				}
			}
		}

	public void dijkstra(vertex s) {//Complexity: O(VlogV+E) with fibb heaps, O(vlogv+elogv) with priority queue to decrease key, does not work with negative edges
		PriorityQueue<vertex> q=new PriorityQueue<vertex>();
		s.distance=0;
		s.parent=null;
		for (vertex v:V) q.add(v);
		while (!q.isEmpty()) {
			vertex v=q.poll();//Poll removes from the head - removes the least priority element
			for (edge e:v.neighbours)
				relax(v,e,q);
		}
	}

	public void dagshortestpath(vertex s) {//No Cycles, negative edges allowed, topological sort then relax vertices in the order obtained, O(V+E)
		topsort=new Stack<vertex>();
		dfs();
		s.distance=0;
		while (!topsort.isEmpty()) {//Relax in topology sorted order for the solution
			vertex v=topsort.pop();
			for (edge e: v.neighbours) relax(v,e);
		}

	}

	public void bellmanford(vertex s) {// O(VE) - Can deal with negative cycles
		s.distance=0;
		for (int i=1;i<V.size();i++) {
			for (vertex v:V) {
				for (edge e:v.neighbours) {
					relax(v,e);
				}
			}
		}

		//Check for negative weight cycle, if the consttaint d[u]<=d[v]+w(u,v) dnot satisfied any edge after running bellman ford. Note that incase of -ve weight cycles there is
		//no cheapest path, as 1 walk around the cycle can give lower wt. in this case, bellman ford detects -ve weights

			for (vertex v:V) {
				for (edge e:v.neighbours) {
		vertex u=e.target;
		if (u.distance>(v.distance+e.weight)) {
			System.out.println("negative wt cycle exists");
			return;
		}
				}
			}
	}

	public void dfs() {
		for (vertex v:V) {
			if (!v.visited) {
				v.visited=true;
				dfsvisit(v);
			}
		}
		//while (!topsort.isEmpty()) System.out.println(topsort.pop().val);
	}

	Stack<vertex> topsort;
	public void dfsvisit(vertex v) {

		for (edge e:v.neighbours) {
			vertex u=e.target;
			if (!u.visited) {//it means it is not explored yet
				u.visited=true;				
				dfsvisit(u);
			}
		}
		//For topological sort
		topsort.push(v);
	}

	public void relax(vertex v, edge e, PriorityQueue<vertex> q) {
		vertex u=e.target;
		if (u.distance>(v.distance+e.weight)) {
			q.remove(u);
			u.distance=v.distance+e.weight;
			u.parent=v;
			q.add(u);
		}
	}

	public void relax(vertex v, edge e) {
		vertex u=e.target;
		if (u.distance>(v.distance+e.weight)) {
			u.distance=v.distance+e.weight;
			u.parent=v;
		}
	}
	public static void main (String[] args) {
		shortestpath g=new shortestpath();
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
		g.addedge(v1,v2,7);
		g.addedge(v1,v3,9);
		g.addedge(v1,v6,14);
		g.addedge(v4,v2,-20);	
		g.addedge(v2,v3,10);
		g.addedge(v3,v4,11);
		g.addedge(v3,v6,2);
		g.addedge(v4,v5,6);	
		g.addedge(v5,v6,9);
		//g.addedge(v1,v8,2);	
		//g.dijkstra(v1);

		//g.dagshortestpath(v1);
		g.bellmanford(v1);
		g.bfs(v1);	
	}


	public void addvertex(vertex v) {
		V.add(v);
	}
	public void addedge(vertex v, vertex u, double weight) {
		v.addneighbour(new edge(u,weight));
		//u.addneighbour(new edge(v,weight));
	}
}