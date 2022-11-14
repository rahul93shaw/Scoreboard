package com.sportradar.game;

/**
 * @author rahul 
 * Interface of game for a football.
 */

public interface Game {
	
	public String start(String awayTeam, String homeTeam);
	public String print();
	public String score(String team);
	public String end();
	public String invalid();

}
