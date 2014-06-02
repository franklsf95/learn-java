import java.util.ArrayList;

public class FamilyTree {
	
	private ArrayList<Node> graph;
	
	class Node {
		public ArrayList<Node> parents;
		public ArrayList<Node> children;
		public String name;
		public String gender;
		
		public Node(String s) {
			name = s;
			gender = "";
			parents = new ArrayList<Node>();
			children = new ArrayList<Node>();
		}
		
		public void addParent(Node p) {
			for (Node pp : parents)
				if (pp.name.equals(p.name))
					return;
			parents.add(p);
		}
	}
	
	private Node findOrCreate(String s) {
		for (Node n : graph) {
			if (s.equals(n.name)) {
				return n;
			}
		}
		// create a new one
		Node x = new Node(s);
		graph.add(x);
		return x;
	}
	
	private boolean pathExist(Node from, Node to) {
		ArrayList<Node> q = new ArrayList<Node>(from.children);
		int cur = 0;
		while (cur < q.size()) {
			Node c = q.get(cur);
			cur++;
			if (c.name.equals(to.name))  // or maybe object identity
				return true;
			for (Node n : c.children) {
				boolean already = false;
				for (int i = 0; i < cur - 1; ++i)
					if (q.get(i) == n) {
						already = true;
						break;
					}
				if (!already)
					q.add(n);
			}
		}
		return false;
	}
	
	public int firstBad(String[] data) {
		graph = new ArrayList<Node>();
		
		String s[] = new String[2];
		for (int i = 0; i < data.length; ++i) {
			s = data[i].split(" ");
			if (s[1].equals("f") || s[1].equals("m")) {
				// write gender
				Node n = findOrCreate(s[0]);
				if (!n.gender.equals("") || !n.gender.equals(s[1]))
					return i;  // GENDER ALREADY CERTAIN
				n.gender = s[1];
				// for all of its children
				for (Node c : n.children) {
					// its parents must have opposite genders
					String existingGender = "";
					for (Node p : c.parents) {
						if (p.gender.equals(existingGender)) {
							return i;  // GENDER CONFLICT WITH SPOUSE
						}
						existingGender = p.gender;
					}
				}
			} else {
				// write parent
				Node child = findOrCreate(s[0]);
				Node parent = findOrCreate(s[1]);
				// search for cycles
				if (pathExist(child, parent))
					return i;  // FORMING A CYCLE
				child.addParent(parent);
				parent.children.add(child);
				if (child.parents.size() > 2)
					return i;  // TOO MANY PARENTS
			}
		}
		return -1;
	}

	public static void main(String[] args) {  // Bob's parent is John
		String[] dat = {"BO LESLIE", "SUE CASEY", "SUE ROBIN", "DEE ROBIN", "DEE LESLIE", "BO CASEY"};
		
		int ret = (new FamilyTree()).firstBad(dat);
		System.out.println(ret);
	}

}
