import java.util.HashSet;
import java.util.Scanner;

public class team {

	HashSet<player> players;
	String name;
	
	// Robust constructor.
	public team(String _name, boolean setUp) {
		players = new HashSet<player>();
		this.name = _name;
	}

	// Standard constructor.
	public team(String _name) {
		players = new HashSet<player>();
		this.name = _name;
	}
	
	// Default constructor.
	public team() {
		players = new HashSet<player>();
		this.name = "Unknown";
	}
	
	// Make a random team with players with numbers 0->n
	protected HashSet<player> makeRandomTeam(int numPlrs) {
		// TODO
		HashSet<player> team = new HashSet<player>();
		
		for (int i = 0; i < numPlrs; i++) {
			player temp = new player(i);
			team.add(temp);
		}
		
		return team;
	}
	
	// Load a team from a "x.team" file
	protected team loadTeam() {
		// TODO 
		return null;
	}
	
	// Making a team with setting up names, numbers, and positions manually
	protected team makeTeam() {
		// TODO
		team team = new team();
		
		
		
		return team;
	}
	
	// Saving "x.team" file
	public void saveTeam() {
		// TODO
	}

	public team createTeam(Scanner in) {
		// TODO Auto-generated method stub
		return null;
	}

}