public void iterativepreorder (node root) {
	Stack<node> s=new Stack<node>();
	s.push(root);
	while (!s.isEmpty()) {
		node curr=s.pop();
		System.out.prinln(curr.val);
		if (curr.right!=null) s.push(curr.right);
		if (curr.left!=null) s.push(curr.left);
	}

}

public void iterativeinorder (node root) {
	Stack<node> s=new Stack<node>();
	s.push(root);	
	node current=root;
	boolean done=false;
	while (!done) {
		if (current) {
			s.push(current);
			current=current.left;
		}
		else {
			if (s.isEmpty()) done=true;
			top=s.pop();
			System.out.println(top.val);
			current=top.right;
		}
	}
}

public void iterativepostorder (node root) {
	
}

Push the root node to the first stack.
Pop a node from the first stack, and push it to the second stack.
Then push its left child followed by its right child to the first stack.
Repeat step 2) and 3) until the first stack is empty.
Once done, the second stack would have all the nodes ready to be traversed in post-order. Pop off the nodes from the second stack one by one and you’re done.

i=hash(key);
if slot[i] is occupied , linearly probe nearby slots and store the key in the first neighbour found