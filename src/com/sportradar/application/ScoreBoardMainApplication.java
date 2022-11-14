package com.sportradar.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.sportradar.game.FootballMatchDetails;

public class ScoreBoardMainApplication {

	public static void main(String[] args) throws IOException {
		List<FootballMatchDetails> matches = new ArrayList<>();
		List<FootballMatchDetails> finishedMatches = new ArrayList<>();

		// Adding matches
		FootballMatchDetails match1 = new FootballMatchDetails();
		match1.setHomeTeam("Scotland");
		match1.setAwayTeam("Canada");
		match1.setMatchId(100);

		FootballMatchDetails match2 = new FootballMatchDetails();
		match2.setHomeTeam("Portugal");
		match2.setAwayTeam("France");
		match2.setMatchId(200);
		
		matches.add(match1);
		matches.add(match2);

		// Scanner matchStart = new Scanner(System.in);
		System.out.println("Welcome to Sportradar.");
		System.out.println("Type \"Start\" to stop the game");
		System.out.println("Type \"End - <match id>\" to stop the game");
		System.out.println("Type \"Summary\" for the game summary");
		System.out.println("Type \"Update - <match Id> - <Team Name>\" for the score update");
		// System.out.println("Type Home Team first, then Away Team (example = Scotland
		// - Canada) and press Enter");
		BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
		String gameStr = inputReader.readLine();
		if (gameStr.trim().isEmpty()) {
			System.out.println("Game id is invalid. Please restart game application");
		} else {
			while (!gameStr.equalsIgnoreCase("End Game")) {
				if(matches.isEmpty()) {
					System.out.println("Currently no game is in progress.");
					break;
				}
				
				//Start matches
				matches.stream().forEach(match -> {
					if(null == match.getStatus()) {
						System.out.println(match.getMatchUpdates().start(match.getHomeTeam(), match.getAwayTeam()));
						match.setStatus(match.getMatchUpdates().getState().toString());
						match.setStartTime(new Date());
					}
				});
				Scanner userInput = new Scanner(System.in);
				gameStr = userInput.nextLine();
				
				//Games Progress
				if (gameStr.contains("Update")) {
					//Update scores
					String[] goalUpd = gameStr.split(" - ");
					Optional<FootballMatchDetails> matchUpd = matches.stream()
							.filter(match -> match.getMatchId() == Integer.parseInt(goalUpd[1])).findFirst();
					System.out.println(matchUpd.get().getMatchUpdates().score(goalUpd[2]));
					matchUpd.get().setStatus(matchUpd.get().getMatchUpdates().getState().toString());
					matches.removeIf(match -> match.getMatchId() == Integer.parseInt(goalUpd[1]));
					matches.add(matchUpd.get());
				} else if(gameStr.equalsIgnoreCase("Summary")) {
					//get summary of matches
					System.out.println(getMatchSummary(finishedMatches));
				} else if(gameStr.contains("End - ")) {
					// match end
					String[] goalUpd = gameStr.split(" - ");
					Optional<FootballMatchDetails> matchUpd = matches.stream()
							.filter(match -> match.getMatchId() == Integer.parseInt(goalUpd[1])).findFirst();
					matchUpd.get().setEndDate(new Date());
					finishedMatches.add(matchUpd.get());
					System.out.println(matchUpd.get().getMatchUpdates().end());
					matches.removeIf(match -> match.getMatchId() == Integer.parseInt(goalUpd[1]));
				}
			}
			System.out.println("All games ended successfully");
		}
	}

	//Match summary
	private static String getMatchSummary(List<FootballMatchDetails> finishedMatches) {
		String matchSummary = "";
		if(finishedMatches != null && !finishedMatches.isEmpty()) {
			if(finishedMatches.size()>1) {
				finishedMatches.stream().sorted((FootballMatchDetails f1, FootballMatchDetails f2) -> f2.getEndDate().compareTo(f1.getEndDate()));
			}
			for(FootballMatchDetails match : finishedMatches) {
				matchSummary += match.getMatchUpdates().printSummary() + "\n"; 
			}
		}
		return matchSummary;
	}

}
