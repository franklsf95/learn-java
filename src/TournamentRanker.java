import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class TournamentRanker {
	public class Team implements Comparable<Team>{
		private String name;
		private int wins;
		private Team lostTo;
		
		public Team(String s) {
			name = s;
			wins = 0;
			lostTo = null;
		}
		
		public String getName() {
			return name;
		}
		
		public void incrementWins() {
			++wins;
		}
		
		public void setLostTo(Team t) {
			lostTo = t;
		}
		
		public int compareTo(Team other) {
			int diff = other.wins - wins;
			if (diff != 0)
				return diff;
			if (lostTo == null)
				return 1;
			if (other.lostTo == null)
				return -1;
			return lostTo.compareTo(other.lostTo);
		}
	}
	
	public String[] rankTeams(String[] names, String[] lostTo) {
		HashMap<String, Team> map = new HashMap<String, Team>();
		for (String n : names) {
			map.put(n, new Team(n));
		}
		for (int i = 0; i < lostTo.length; i++) {
			String winnerName = lostTo[i];
			Team winner = null;
			if (!winnerName.equals("")) {
				winner = map.get(winnerName);
				winner.incrementWins();
			}
			Team loser = map.get(names[i]);
			loser.setLostTo(winner);
		}
		int count = map.size();
		Team[] teams = map.values().toArray(new Team[count]);
		Arrays.sort(teams);
		String[] ret = new String[count];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = teams[i].getName();
		}
		return ret;
	}
	
	public static void main(String[] args) {
		TournamentRanker tr = new TournamentRanker();
		String[] teams = {"DUKE", "SETON HALL", "ILLINOIS", "CINCINNATI",
			 "NORTH CAROLINA", "TEXAS", "XAVIER", "MISSISSIPPI STATE"};
		String[] losts = {"", "DUKE", "DUKE", "ILLINOIS",
			 "TEXAS", "XAVIER", "DUKE", "XAVIER"};
		String[] results = tr.rankTeams(teams, losts);
		for (String s : results) {
			System.out.println(s);
		}
	}
}