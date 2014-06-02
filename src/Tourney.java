import java.util.ArrayList;
import java.util.Arrays;

public class Tourney {
	public static <T> ArrayList<ArrayList<T>> chopped(ArrayList<T> list,
			final int L) {
		ArrayList<ArrayList<T>> parts = new ArrayList<ArrayList<T>>();
		final int N = list.size();
		for (int i = 0; i < N; i += L) {
			parts.add(new ArrayList<T>(list.subList(i, Math.min(N, i + L))));
		}
		return parts;
	}

	public String winner(String[] bracket, String results) {
		// fill in code here
		// String ret = "";
		if (results == "") {
			// return bracket[0] / the non-bye?
			if (bracket[0] != "bye") {
				return bracket[0];
			} else {
				return bracket[1];
			}
		}
		if (bracket.length == 2) {
			if (results == "H") {
				return bracket[0];
			} else {
				return bracket[1];
			}
		}

		ArrayList<String> list = new ArrayList<String>(Arrays.asList(bracket));
		ArrayList<ArrayList<String>> parts = chopped(list, 2);
		ArrayList<String> winList = new ArrayList<String>();
		int current = 0;
		for (int i = 0; i < parts.size(); i++) {
			// int len = parts.get(i).size();
			String[] newPart = new String[2];
			parts.get(i).toArray(newPart);
			// String result = results.substring(1);//43,44 indexoutofrange
			// list2.add(winner(newPart, result));
			String ret = "";
			if (newPart[0].equals("bye")) {
				ret = newPart[1];
			} else if (newPart[1] == "bye") {
				ret = newPart[0];
			}

			else {
//				System.out.print(results.substring(current,current+1));
				if (results.substring(current, current + 1).equals("H")) {
					ret = newPart[0];
				} else {
					ret = newPart[1];
				}
				current++;

			}
			
			winList.add(ret);
			System.out.print("");
		}

		results = results.substring(current);
		String[] winArray = winList.toArray(new String[winList.size()]);
		return winner(winArray, results);

	}

	public static void main(String[] args) {
		Tourney t = new Tourney();
		String[] bracket = { "A", "B", "C", "bye", "D", "E", "F", "bye" };
		String results = "LHHLH";
		System.out.println(t.winner(bracket, results));
	}
}