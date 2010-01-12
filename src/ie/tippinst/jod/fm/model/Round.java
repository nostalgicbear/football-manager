package ie.tippinst.jod.fm.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Round {
	private List<Calendar> roundDate = new ArrayList<Calendar>();
	private List<Calendar> earliestRoundDate = new ArrayList<Calendar>();
	private int roundNumber;
	private Calendar drawDate;
	private List<Club> teams = new ArrayList<Club>();
	private List<Match> matches = new ArrayList<Match>();
	private int numberOfTeams;
	private List<Club> winners = new ArrayList<Club>();
	private boolean twoLegs;

	public Round() {
	}

	public void setRoundDate(List<Calendar> roundDate) {
		this.roundDate = roundDate;
	}

	public List<Calendar> getRoundDate() {
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

	public void setWinners(List<Club> winners) {
		this.winners = winners;
	}

	public List<Club> getWinners() {
		return winners;
	}
	
	public void playMatches(Calendar date){
		Iterator<Calendar> iterator = this.getRoundDate().iterator();
		while(iterator.hasNext()){
			Calendar c = iterator.next();
			if((c.get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH))
					&& (c.get(Calendar.MONTH) == date.get(Calendar.MONTH))
					&& (c.get(Calendar.YEAR) == date.get(Calendar.YEAR))){
				Iterator<Match> i = this.getMatches().iterator();
				while(i.hasNext()){
					Match m = i.next();
					if((m.getDate().get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH))
					&& (m.getDate().get(Calendar.MONTH) == date.get(Calendar.MONTH))
					&& (m.getDate().get(Calendar.YEAR) == date.get(Calendar.YEAR))){
						m.getHomeTeam().setSelectedTeam(m.getHomeTeam().getBestTeam(m.getHomeTeam().getAvailablePlayers()));
						m.getAwayTeam().setSelectedTeam(m.getAwayTeam().getBestTeam(m.getAwayTeam().getAvailablePlayers()));
						if(this.hasTwoLegs() && c.compareTo(this.getRoundDate().get(this.getRoundDate().size() - 1)) == 0){
							int index = (this.getMatches().indexOf(m)) - (this.getNumberOfTeams() / 2);
							m.setHomeFirstLegScore(this.getMatches().get(index).getHomeScore());
							m.setAwayFirstLegScore(this.getMatches().get(index).getAwayScore());
							m.generateResult();
							this.getWinners().add(m.getWinner());
						}
						else if(this.hasTwoLegs()){
							m.generateResult();
						}
						else{
							m.generateResult();
							this.getWinners().add(m.getWinner());
						}
					}
				}
			}			
		}
	}

	public void setTwoLegs(boolean twoLegs) {
		this.twoLegs = twoLegs;
	}

	public boolean hasTwoLegs() {
		return twoLegs;
	}

	public void setEarliestRoundDate(List<Calendar> earliestRoundDate) {
		this.earliestRoundDate = earliestRoundDate;
	}

	public List<Calendar> getEarliestRoundDate() {
		return earliestRoundDate;
	}
}
