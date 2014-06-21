import java.util.*;
 class node {
	double key;
	node left;
	node right;
	node parent;
	int height;
	int rank;
	node (double k) {key=k; left=right=null;}
}

//Best Case: Create a bst class with root as a private member, instead of static members
/* To implement an iterator for a collection, such as BST here, implement the Iterable<T> interface.
This interface comes with Iterator<node> iterator() method which needs to be implemented.
Now create your own iterator class MyIterator as a subclass by implementing Iterator<T> interface which comes with 
boolean hasNext(), T next(), remove() methods */
public class bst implements Iterable<Double> {

	private node root;
	//Inorder Iterator
	private class MyIterator implements Iterator<Double> {
		/* Preorder Iterator*/

		private Stack<node> s;
		public MyIterator() {
			s=new Stack<node>();
			if (root!=null) s.push(root);

		}
		public boolean hasNext() {
			return !s.isEmpty();
		}	
		public Double next() {
			if (!hasNext()) {throw new RuntimeException("Tree is empty");}
			node curr=s.peek();//curr.data
			if (curr.left!=null) {//Go Left
				s.push(curr.left);
			}
			else {
				node m=s.pop();
				if (m.right!=null) s.push(m.right);
			}
			return curr.key;
		}
		public void remove() {

		}


		/* Inorder Iterator using queue
		private Queue<Double> q;
		Iterator<Double> it;

		public MyIterator() {
			q=new LinkedList<Double>(); //Note that Queue is an interface, it needs to be implemented
			inorder(root);
			it=q.iterator();
		}

			public void inorder(node n) {
				if (n==null) return;
			inorder(n.left);
			q.add(n.key); //Autoboxing: carried out in case of passing argument to collection or assigning to other type
			inorder(n.right);			
		}
			public boolean hasNext() {return it.hasNext();}
		public Double next() {
			if (it.hasNext() it.next();
		}
		public void remove() {it.remove();}
		*/
	}


	public static void main(String[] args) {
		bst t=new bst();
		t.insert(4);
		t.insert(5);
		t.insert(4.5);
		t.insert(11);
		t.insert(12);
		t.insert(13);
		t.insert(12.5);
		t.insert(10.5);
		t.insert(3);
		t.insert(14);
		t.insert(-1);
		t.postorder();
		System.out.println(" ");
		t.postorderit();
		/*t.inorder();
		System.out.println(" ");
		t.inorderit();
		/*
		System.out.println(" ");
		t.levelorder();
		System.out.println("New");
		Iterator<Double> it=t.iterator();
		//while (it.hasNext())
		System.out.println(it.next());*/
		/*t.inplacemirror();
		t.inplacesinglist();
		t.inorder();
		System.out.println(" ");
		t.levelorder();*/
		/*t.delete(5);
		bst b=t.clone();
		b.inorder();
		System.out.println(" ");
		b.levelorder();*/

	}

	public bst(){root=null;}
	public bst(node n) {root=n;}

	public Iterator<Double> iterator() {
		MyIterator m=new MyIterator();
		return m;
	}



	//In-place list conversion such that all left nodes are null and BST property is maintained

	public void inplacesinglist() {
		inplacesinglist(root);
		root=head;
		head=tail=null;
	}

	/* Java does not allow static variables inside recursion. This is why 2 global variables, head & tail need to be maintained
	to track the list. PAssing around head and tail as function arguments does not work either as java stores objects by references,
	and passes them by value, meaning a new copy of pointer to object referenced by head is created. Hence modification to this new pointer, only modifies the new pointer
	and does not update the original pointer */
	public node head=null;
	public node tail=null;	
	public void inplacesinglist(node n) {
		if (n==null) return;
		inplacesinglist(n.left);
		if (head==null) head=n;
		if (tail==null) tail=n;
		else {
			tail.right=n;
			n.left=null;
			tail=n;
		}
		inplacesinglist(n.right);
	}

	//All branches need to be swapped
	public void inplacemirror() {
		root=inplacemirror(root);
	}

	public node inplacemirror(node n) {
		if (n==null) return null;
		n.right=inplacemirror(n.right);
		n.left=inplacemirror(n.left);
		node tmp=n.right;
		n.right=n.left;
		n.left=tmp;
		return n;
	}
	public bst clone() {
		node nr=clone(root);
		return new bst(nr);
	}
	public node clone(node root) {
		if (root==null) return null;
		node n=new node(root.key);
		n.right=clone(root.right);
		n.left=clone(root.left);
		return n;
	}
	public void delete(double k) {
		root=delete(root,k);
	}
	public node delete(node root, double k) {
		if (root==null) throw new RuntimeException("Cannot delete");

		if (k>root.key) root.right=delete(root.right,k);
		if (k<root.key) root.left=delete(root.left,k);
		if (k==root.key) {
			//if either of children is null, just bypass this node
			if (root.left==null) return root.right;
			if (root.right==null) return root.left;
			else {
				//Find the rightmost node in left subtree, replace key
				node tmp=root.left;
				while (tmp.right!=null) tmp=tmp.right;
				root.key=tmp.key;
				root.left=delete(root.left,tmp.key);
			}
		}

		//Update heights for this node, and balance it..this is O(1)
		int lheight=(root.left==null)?-1:root.left.height;
		int rheight=(root.right==null)?-1:root.right.height;
		root.height=Math.max(lheight+1, rheight+1);

		if (Math.abs(rheight-lheight)>1)
		return balance(root);
		else return root;
	}
	public boolean search(double k) {
		return search(root,k);
	}
	public boolean search(node root, double k) {
		if (root==null) return false;
		if (k==root.key) return true;
		else if (k>root.key) return search(root.right,k);
		else return search(root.left,k);
	}
	public void insert(double k) {
		if (root==null) {
			root=new node(k);root.height=0;
		}
		else root=insert(root, k);
	}
	public node insert(node root, double k) {
		if (k>root.key) {
			if (root.right == null) {
				node n=new node(k);
				root.right=n;
				n.height=0;
			}
			else {
				root.right=insert(root.right, k);

			}
		}
		if (k<root.key) {
			if (root.left == null) {
				node n=new node(k);
				root.left=n;
				n.height=0;
			}
			else {
				root.left=insert(root.left, k);

			}
		}

		int lheight=(root.left==null)?-1:root.left.height;
		int rheight=(root.right==null)?-1:root.right.height;
		root.height=Math.max(lheight+1, rheight+1);

		if (Math.abs(rheight-lheight)>1)
		return balance(root);
		else return root;
	}
	/* http://www.javaworld.com/article/2077424/learn-java/does-java-pass-by-reference-or-pass-by-value.html */
	public node rotateleft(node root) {
			node m=root.right;
			root.right=root.right.left;
			m.left=root;
			
			m.left.height=Math.max((m.left.right==null)?-1:m.left.right.height, (m.left.left==null)?-1:m.left.left.height)+1;
			m.height=Math.max((m.right==null)?-1:m.right.height, (m.left==null)?-1:m.left.height)+1;
			return m;
	}
	public node rotateright(node root) {
			node m=root.left;
			root.left=root.left.right;
			m.right=root;
			m.right.height=Math.max((m.right.right==null)?-1:m.right.right.height, (m.right.left==null)?-1:m.right.left.height)+1;
			m.height=Math.max((m.right==null)?-1:m.right.height, (m.left==null)?-1:m.left.height)+1;		
			return m;
	}
	public node balance(node root) {

		int lheight=(root.left==null)?-1:root.left.height;
		int rheight=(root.right==null)?-1:root.right.height;
		if ((rheight-lheight)>1) {
			int lrheight=(root.right.left==null)?-1:root.right.left.height;
			int rrheight=(root.right.right==null)?-1:root.right.right.height;
			//Two cases here 
			if (rrheight>lrheight) return rotateleft(root);
			else {
				root.right=rotateright(root.right);
				return rotateleft(root);
			}
		}			
		else {
			int lrheight=(root.left.left==null)?-1:root.left.left.height;
			int rrheight=(root.left.right==null)?-1:root.left.right.height;
			if (lrheight>rrheight) return rotateright(root);
			else {
				root.left=rotateleft(root.left);
				return rotateright(root);
			}
		}
	}
	public void inorderit() {
		inorderit(root);
	}
	public void inorderit(node n) {
		/* Solution with O(n) space 

		HashSet<node> visited=new HashSet<node>();
		Stack<node> s=new Stack<node>();
		s.push(n);

		while (!s.isEmpty()) {
			node p=s.peek();
			if (visited.contains(p)) {
				s.pop();
				System.out.print(p.key+" ");
				if (p.right!=null) s.push(p.right);
			}
			else {
				visited.add(p);
				if (p.left!=null) s.push(p.left);
			}
		}
		*/

		/* Solution without O(n) space for hashmap*/

		Stack<node> s=new Stack<node>();
		node current=n;
		while (!s.isEmpty() || current!=null) {
			if (current!=null) {
				s.push(current);
				current=current.left;
			}
			else {
				node g=s.pop();
				System.out.print(g.key+" ");
				current=g.right;
			}
		}

	}


	public void postorderit() {
		postorderit(root);
	}
	public void postorderit(node n) {
		/* Solution with O(n) space for visited map 
		
		HashMap<node, Integer> visited=new HashMap<node,Integer>();
		Stack<node> s=new Stack<node>();
		s.push(n);

		while (!s.isEmpty()) {
			node p=s.peek();
			if (visited.containsKey(p)) {
				if (visited.get(p)==2) {
					s.pop();
					System.out.print(p.key+" ");
				}
				else {
					if (p.right!=null) s.push(p.right);
					visited.put(p,2);
				}
			}
			else {
				visited.put(p,1);
				if (p.left!=null) s.push(p.left);
			}
		}
		*/

		/* Solution without O(n) space for visited map */

		Stack<node> s=new Stack<node>();
		node current=n;

		while (!s.isEmpty() || current!=null) {
			if (current!=null) {
				if (current.right!=null) s.push(current.right);
				s.push(current);
				current=current.left;
			}
			else {
				current=s.pop();
				if (current.right!=null && !s.isEmpty() && current.right==s.peek()) {
					s.pop();
					s.push(current);
					current=current.right;
				}
				else {
					System.out.print(current.key+" ");
					current=null;
				}

			}
		}
	}

	public void preorder() {
		preorder(root);
	}
	public void preorder(node n) {
		if (n==null) return;
		System.out.print(n.key+" ");
		preorder(n.left);
		preorder(n.right);
	}
	public void preorderit() {
		preorderit(root);
	}
	public void preorderit(node n) {
		/* Solution with O(n) space */
		Stack<node> s=new Stack<node>();
		s.push(n);

		while (!s.isEmpty()) {
			node m=s.pop();
			System.out.print(m.key+" ");
			if (m.right!=null) s.push(m.right);
			if (m.left!=null) s.push(m.left);
		}

	}



	public void postorder() {
		postorder(root);
	}
	public void inorder() {
		inorder(root);
	}
	public void levelorder() {
		levelorder(root);
	}
	public void inorder(node n) {
		if (n==null) return;
		inorder(n.left);
		System.out.print(n.key+" ");
		inorder(n.right);
	}
	public void postorder(node n) {
		if (n==null) return;
		postorder(n.left);
		postorder(n.right);
		System.out.print(n.key+" ");
	}
	//BFS for tree with ArrayList, storing parent nodes for each node
	public void levelorder(node root) {
		ArrayList<node> frontier=new ArrayList<node>();
		frontier.add(root);
		root.parent=null;
		while (!frontier.isEmpty()) {
			ArrayList<node> next=new ArrayList<node>();
			for (node n:frontier) {
				//System.out.print((n.parent==null)?0:n.parent.key + " ");
				System.out.print(n.key+" ");
				//System.out.print(n.height+" ");
				if (n.left!=null) {next.add(n.left); n.left.parent=n;}
				if (n.right!=null) {next.add(n.right); n.right.parent=n;}

			}
			System.out.print("\n");
			frontier=next;
		}
	}


}