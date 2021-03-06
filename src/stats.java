import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Scanner;


@SuppressWarnings("unused")
public class stats {
	
	static game gm;

	public static void main(String[] args) {
		// Testing for applet purposes
		Scanner in = new Scanner(System.in);
		
		String answer = "";
		boolean exit = false;
		
		gm = new game();
		
		while (!exit) {
			System.out.print("Enter what you would like to do (\"h\" for help): ");
			
			answer = in.nextLine().toLowerCase();
			
			switch (answer) {
				case "h":
					printHelp();
					break;
				case "t":
					createOrLoadTeam(in);
					break;
				case "p":
					playGame();
					break;
				case "s":
					saveTeams(in);
					break;
				case "x":
					exportForMaxPreps(in);
					break;
				case "e":
					System.out.println("Thank you for using the stats app.");
					exit = true;
					break;
				default:
					System.out.println("Invalid entry, try again.");
					break;
			}
				
		}
		
		System.out.println("Would you like to save the game log (y/n): ");
		String ans = in.nextLine().toLowerCase();
		
		if (ans.length() > 0) {
			if (ans.charAt(0) == 'y') {
				saveLogs(in);
			} else if (ans.charAt(0) == 'n') {
				System.out.println("Okay. Thank you.");
			} else {
				System.out.println("ERROR: Invalid entry. Exiting.");
			}
		}
		
		System.out.println("Goodbye.");
		in.close();
	}
	
	private static void saveLogs(Scanner in) {
		System.out.print("Enter the name of the text file: ");
		String ans = in.nextLine();
		
		if (ans.length() < 1) {
			ans = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
			ans += "_STATS-GAME";
			System.out.println(ans);
		}
		
		if (ans.length() > 3) {
			if (ans.substring(ans.length() - 3) != ".txt") {
				ans += ".txt";
			}
		}
		
		String div = "<------------------------->";
		
		try {
			PrintWriter out = new PrintWriter(ans);
			
			for (int i = 0; i < gm.logs.length; i++) {
				if (i <= 3) {
					out.println("~Quarter " + (i + 1) + ":");
					out.println(gm.logs[i]);
					out.println(div);
				} else if (i == 4 && gm.logs[i].length() > 0) {
					out.println("~First Overtime: ");
					out.println(gm.logs[i]);
					out.println(div);
				} else if (i == 5 && gm.logs[i].length() > 0) {
					out.println("~Second Overtime: ");
					out.println(gm.logs[i]);
					out.println(div);
				}
				out.println(); // Empty line for added separation.				
			}
			
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void saveTeams(Scanner in) {
		System.out.println("Would you like to save the home(h) team or away(a) team? ");
		String ans = in.nextLine().toLowerCase();
		
		if (ans.charAt(0) == 'h') {
			gm.homeTeam.saveTeam(in);
		} else if (ans.charAt(0) == 'a') {
			gm.awayTeam.saveTeam(in);
		} else {
			System.out.println("ERROR: Invalid entry. Exiting.");
		}
	}

	private static void playGame() {
		if (gm.homeTeam == null || gm.awayTeam == null) {
			System.out.println("ERROR: Need to load or create both teams.");
			return;
		}
		
		gm.play();
	}

	private static void createOrLoadTeam(Scanner in) {
		team tm = new team();
		System.out.print("Would you like to load(l) or create(c) teams? ");
		String ans = in.nextLine().toLowerCase();
		
		if (ans.charAt(0) == 'l') {
			tm = tm.loadTeam();
		} else if (ans.charAt(0) == 'c') {
			tm = tm.createTeam(in);
		} else {
			System.out.println("ERROR: Invalid entry. Exiting.");
			return;
		}
		
		ans = "";
		
		System.out.print("Is this going to be the home(h) team or away(a) team? ");
		ans = in.nextLine().toLowerCase();
		
		if (ans.charAt(0) == 'h') {
			gm.homeTeam = tm;
		} else if (ans.charAt(0) == 'a') {
			gm.awayTeam = tm;
		} else {
			System.out.println("ERROR: Invalid entry. Exiting.");
			return;
		}
	}

	private static void printHelp() {
		System.out.println("<----------Help---------->");
		System.out.println("Available commands: ");
		System.out.println("   \"t\" -> Create or load a team.");
		System.out.println("   \"p\" -> Play a game.");
		System.out.println("   \"s\" -> Save team.");
		System.out.println("   \"x\" -> Export a game for max preps.");
		System.out.println("   \"e\" -> Exit application.");
		System.out.println("<------------------------>");
	}

	private static void exportForMaxPreps(Scanner in) {
		System.out.print("Export for home(h) team or away(a) team? ");
		String team = in.nextLine().toLowerCase();
		
		team xTm = new team();
		
		if (team.charAt(0) == 'h') {
			xTm = gm.homeTeam;
		} else if (team.charAt(0) == 'a') {
			xTm = gm.awayTeam;
		} else {
			System.out.println("ERROR: Invalid entry. Exiting.");
			return;
		}
		
		System.out.print("Enter the name of the text file: ");
		String ans = in.nextLine();
		
		if (ans.length() < 1) {
			ans = new SimpleDateFormat("yyyy.MM.dd").format(new java.util.Date());
			ans += "_STATS-GAME";
			System.out.println(ans);
		}
		
		if (ans.length() > 3) {
			if (ans.substring(ans.length() - 3) != ".txt") {
				ans += ".txt";
			}
		}
		
		try {
			PrintWriter out = new PrintWriter(ans);
			
			xTm.exportForMaxPreps(out);
			
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
