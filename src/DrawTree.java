import java.util.ArrayList;

 public class DrawTree {
	 private int[] parents;
	 private String[] names;
	 
	 private int find(int root, int start) {
		 if (start < 0) return -1;
		 for (int i = start; i < parents.length; ++i) {
			 if (parents[i] == root)
				 return i;
		 }
		 return -1;
	 }
	 
	 public String[] draw(int[] parents, String[] names) {
		 this.parents = parents;
		 this.names   = names;
		 
		 int root = find(-1, 0);
		 ArrayList<String> arr = _draw(root, 0, new ArrayList<Integer>());
		 return arr.toArray(new String[arr.size()]);
	 }
	 
//	 private void pr(ArrayList<Integer> ps) {
//		 for (int i = 0; i < ps.size(); ++i) {
//			 System.out.print(ps.get(i));
//			 System.out.print(" ");
//		 }
//		 System.out.println();
//	 }
	 
	 private void sprint(ArrayList<String>ss, int depth, String n, ArrayList<Integer> pipes) {
		 StringBuilder sb = new StringBuilder();
		 for (int i = 0; i < depth; ++i) {
			 boolean print_pipe = pipes.indexOf(i) != -1;
			 if (print_pipe)
				 sb.append("| ");
			 else
				 sb.append("  ");
		 }
		 sb.append("+-");
		 sb.append(n);
		 ss.add(sb.toString());
	 }
	 
	 private ArrayList<String> _draw(int root, int depth, ArrayList<Integer> pipes) {
//		 System.out.print(depth);
//		 System.out.print(": ");
//		 pr(pipes);
		 ArrayList<String> ret = new ArrayList<String>();
		 ArrayList<String> tmp;
		 sprint(ret, depth, names[root], pipes);
		 
		 int p = -1;
		 int nxt = -1;
		 while (true) {
			 p = find(root, p + 1);
			 if (p == -1)
				 break;
			 nxt = find(root, p + 1);
			 if (nxt != -1) { // need pipe
				 pipes.add(depth + 1);
			 }
			 tmp = _draw(p, depth + 1, pipes);
			 ret.addAll(tmp);
			 pipes.remove(new Integer(depth + 1));
		 }
		 
		 return ret;
	 }
	 
	 public static void main(String[] args) {
		 int parents[] = {-1,0,1,1,2,2,3,3,0,8,8,9,9,10,10};

		 String names[] = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
		 
		 String [] res = (new DrawTree()).draw(parents, names);
		 for (String s : res) {
			 System.out.println(s);
		 }
	 }
}
 
