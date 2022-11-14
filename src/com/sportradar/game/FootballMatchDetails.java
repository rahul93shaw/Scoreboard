package com.sportradar.game;

import java.util.Date;
import java.util.Objects;

public class FootballMatchDetails {

	private int matchId;
	private String homeTeam;
	private String awayTeam;
	private FootballMatchLive matchUpdates = new FootballMatchLive();
	private Date startTime;
	private Date endDate;
	private String result;

	private String status;

	public FootballMatchDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FootballMatchDetails(int matchId, String homeTeam, String awayTeam, FootballMatchLive matchUpdates,
			Date startTime, Date endDate, String result, String status) {
		super();
		this.matchId = matchId;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchUpdates = matchUpdates;
		this.startTime = startTime;
		this.endDate = endDate;
		this.result = result;
		this.status = status;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public FootballMatchLive getMatchUpdates() {
		return matchUpdates;
	}

	public void setMatchUpdates(FootballMatchLive matchUpdates) {
		this.matchUpdates = matchUpdates;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "FootballMatchDetails [matchId=" + matchId + ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam
				+ ", matchUpdates=" + matchUpdates + ", startTime=" + startTime + ", endDate=" + endDate + ", result="
				+ result + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(awayTeam, endDate, homeTeam, matchId, matchUpdates, result, startTime, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FootballMatchDetails other = (FootballMatchDetails) obj;
		return Objects.equals(awayTeam, other.awayTeam) && Objects.equals(endDate, other.endDate)
				&& Objects.equals(homeTeam, other.homeTeam) && matchId == other.matchId
				&& Objects.equals(matchUpdates, other.matchUpdates) && Objects.equals(result, other.result)
				&& Objects.equals(startTime, other.startTime) && Objects.equals(status, other.status);
	}
}
