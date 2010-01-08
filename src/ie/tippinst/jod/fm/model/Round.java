package ie.tippinst.jod.fm.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Round {
	private Calendar roundDate;
	private int roundNumber;
	private Calendar drawDate;
	private List<Club> teams = new ArrayList<Club>();
	private List<Match> matches = new ArrayList<Match>();
	private int numberOfTeams;

	public Round() {
	}

	public void setRoundDate(Calendar roundDate) {
		this.roundDate = roundDate;
	}

	public Calendar getRoundDate() {
		return roundDate;
	}

	public void setRoundNumber(int roundNumber) {
		this.roundNumber = roundNumber;
	}

	public int getRoundNumber() {
		return roundNumber;
	}

	public void setDrawDate(Calendar drawDate) {
		this.drawDate = drawDate;
	}

	public Calendar getDrawDate() {
		return drawDate;
	}

	public void setTeams(List<Club> teams) {
		this.teams = teams;
	}

	public List<Club> getTeams() {
		return teams;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public int getNumberOfTeams() {
		return numberOfTeams;
	}
}
