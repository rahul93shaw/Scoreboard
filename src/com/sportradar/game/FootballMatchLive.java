package com.sportradar.game;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * @author rahul 
 * Implements the game for a football.
 */
public class FootballMatchLive implements Game {

	public static enum State {
		START, PLAYING, END
	}

	private String awayTeam;
	private String homeTeam;

	private int awayScore;
	private int homeScore;

	private Map<String, Integer> goalAwayTeam;
	private Map<String, Integer> goalHomeTeam;

	private State state;

	/**
	 * Constructor
	 */
	public FootballMatchLive() {
	}

	/**
	 * Start a new game
	 * 
	 * @param String awayTeam, String homeTeam
	 * @return String message
	 */
	@Override
	public String start(String awayTeam, String homeTeam) {
		reset();

		state = State.START;

		this.awayTeam = awayTeam;
		this.homeTeam = homeTeam;

		return String.format("%s - %s : %d - %d\n", this.homeTeam, this.awayTeam, homeScore, awayScore);

	}

	/**
	 * Print the current game info.
	 * 
	 * @return String message
	 */
	@Override
	public String print() {
		if (state == State.END) {
			return "No game currently in progress";
		}

		return printInfo();
	}

	/**
	 * Adds a goal to the game.
	 * 
	 * @param String team
	 * @return String message
	 */
	@Override
	public String score(String team) {
		if (state == State.END) {
			return "No game currently in progress";
		}

		state = State.PLAYING;

		if (this.awayTeam.equals(team)) {
			awayScore++;
			goalAwayTeam.put(team, awayScore);
		}

		if (this.homeTeam.equals(team)) {
			homeScore++;
			goalHomeTeam.put(team, homeScore);
		}

		return "Goal!!!\n" + printInfo();
	}

	/**
	 * Finish the game.
	 * 
	 * @return String message
	 */
	@Override
	public String end() {
		if (state == State.END) {
			return "No game currently in progress";
		}

		String info = printInfo();

		state = State.END;

		return "The game has ended!\n" + info;
	}

	/**
	 * Will return the current info on the football game as a String. 
	 * 
	 * @return String info
	 */
	public String printInfo() {

		return homeTeam + " - " + awayTeam + " : " + this.homeScore + " - " + this.awayScore;
	}

	public String printSummary() {

		return homeTeam + " " + this.homeScore + " - " + awayTeam + " " + this.awayScore;
	}

	/**
	 * Handles the invalid input on state.
	 * 
	 * @return String message
	 */
	@Override
	public String invalid() {
		if ((state == State.START || state == State.PLAYING)) {
			return " 'input error - please type 'print' for game details'.";
		} else
			return " 'input error - please start a game through typing 'Start:'<Name of Home Team>' vs. '<Name of Away Team>''.";
	}

	/**
	 * Reset the game to a new one.
	 */
	public void reset() {
		awayTeam = "";
		homeTeam = "";
		awayScore = 0;
		homeScore = 0;
		goalAwayTeam = new TreeMap<String, Integer>();
		goalHomeTeam = new TreeMap<String, Integer>();
		state = State.END;
	}

	public State getState() {
		return state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(awayScore, awayTeam, goalAwayTeam, goalHomeTeam, homeScore, homeTeam, state);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootballMatchLive other = (FootballMatchLive) obj;
		return awayScore == other.awayScore && Objects.equals(awayTeam, other.awayTeam)
				&& Objects.equals(goalAwayTeam, other.goalAwayTeam) && Objects.equals(goalHomeTeam, other.goalHomeTeam)
				&& homeScore == other.homeScore && Objects.equals(homeTeam, other.homeTeam) && state == other.state;
	}

}
