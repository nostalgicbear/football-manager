package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class League extends Competition implements Serializable {
	
	private static final long serialVersionUID = -5427999255560805692L;
	private int numberOfTeams;
	private List<Club> teams;
	private int table [][];
	private Match[][] fixtures;
	private Calendar[] matchDates;
	private Calendar earliestStartDate;
	//private Calendar[] earliestMatchDates;
	private League promotedTo;
	private League relegatedTo;
	private int numberOfTeamsPromoted;
	private int numberOfTeamsRelegated;
	private int numberOfTeamsInPlayoff;
	private Cup playoffs;
	
	public League() {
		super();
	}

	public League(int id, String name, int reputation, int numberOfTeams) {
		super(id, name, reputation);
		this.numberOfTeams = numberOfTeams;
		table = new int [this.getNumberOfTeams()][10];
		for(int i = 0; i < this.getNumberOfTeams(); i++){
			table[i][0] = i + 1;
			table[i][1] = this.getTeams().get(i).getId();
		}
	}

	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
		if(numberOfTeams != 0)
			matchDates = new Calendar[(numberOfTeams - 1) * 2];
	}

	public List<Club> getTeams() {
		return teams;
	}

	public void setTeams(List<Club> teams) {
		Collections.shuffle(teams);
		this.teams = teams;
	}

	public int[][] getTable() {
		return table;
	}

	public void setTable() {
		int[][] table = null;
		table = new int [this.getNumberOfTeams()][10];
		for(int i = 0; i < this.getNumberOfTeams(); i++){
			table[i][0] = i + 1;
			table[i][1] = this.getTeams().get(i).getId();
		}
		this.table = table;
		//sortTable(this.table);
	}

	public void setFixtures(Match[][] fixtures) {
		this.fixtures = fixtures;
		/*for (int i = 0; i < fixtures.length; i++) {
            System.out.println("Round " + (i + 1));
            System.out.println(Arrays.asList(fixtures[i]));
            System.out.println();
        }*/
	}

	public Match[][] getFixtures() {
		return fixtures;
	}
	
	public void updateTable(Match match){
		for(int i = 0; i < this.getTable().length; i++){
			if(this.table[i][1] == match.getHomeTeam().getId()){
				//System.out.println(this.table[i][1]);
				//System.out.println(match.getHomeTeam().getId());
				this.table[i][2]++;
				if(match.getHomeScore() > match.getAwayScore()){
					this.table[i][3]++;
				}
				else if(match.getHomeScore() == match.getAwayScore()){
					this.table[i][4]++;
				}
				else if(match.getHomeScore() < match.getAwayScore()){
					this.table[i][5]++;
				}
				this.table[i][6] = this.table[i][6] + match.getHomeScore();
				this.table[i][7] = this.table[i][7] + match.getAwayScore();
				this.table[i][8] = this.table[i][6] - this.table[i][7];
				this.table[i][9] = (this.table[i][3] * 3) + this.table[i][4];
			}
			else if(this.table[i][1] == match.getAwayTeam().getId()){
				this.table[i][2]++;
				if(match.getAwayScore() > match.getHomeScore()){
					this.table[i][3]++;
				}
				else if(match.getAwayScore() == match.getHomeScore()){
					this.table[i][4]++;
				}
				else if(match.getAwayScore() < match.getHomeScore()){
					this.table[i][5]++;
				}
				this.table[i][6] = this.table[i][6] + match.getAwayScore();
				this.table[i][7] = this.table[i][7] + match.getHomeScore();
				this.table[i][8] = this.table[i][6] - this.table[i][7];
				this.table[i][9] = (this.table[i][3] * 3) + this.table[i][4];
			}
		}
		//sortTable(table);
	}
	
	public void sortTable(String[][] table){
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table.length - i - 1; j++){
				if(Integer.parseInt(table[j][9]) < Integer.parseInt(table[j+1][9])){
					String [] temp = table[j];
					table[j] = table[j+1];
					table[j+1] = temp;
				}
				else if(Integer.parseInt(table[j][9]) == Integer.parseInt(table[j+1][9])){
					if(Integer.parseInt(table[j][8]) < Integer.parseInt(table[j+1][8])){
						String [] temp = table[j];
						table[j] = table[j+1];
						table[j+1] = temp;
					}
					else if(Integer.parseInt(table[j][8]) == Integer.parseInt(table[j+1][8])){
						if(Integer.parseInt(table[j][6]) < Integer.parseInt(table[j+1][6])){
							String [] temp = table[j];
							table[j] = table[j+1];
							table[j+1] = temp;
						}
						else if(Integer.parseInt(table[j][6]) == Integer.parseInt(table[j+1][6])){
							if(table[j][1].compareTo(table[j+1][1]) > 0){
								String [] temp = table[j];
								table[j] = table[j+1];
								table[j+1] = temp;
							}
						}
					}
				}
			}			
		}
		for(int i = 0; i < table.length; i++){
			table[i][0] = i + 1 + "";
		}
	}
	
	public Match[][] generateFixtures(){
		
		 // Find out how many teams we want fixtures for.
        int teams = this.numberOfTeams;

        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }

        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        Match[][] rounds = new Match[totalRounds * 2][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                //rounds[round][match] = (home + 1) + " v " + (away + 1);
                rounds[round][match] = new Match(this.getTeams().get(home), this.getTeams().get(away), -1, -1, this, this.getTeams().get(home).getHomeGround());
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        Match[][] interleaved = new Match[totalRounds * 2][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length / 2; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length / 2; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = new Match(rounds[round][0].getAwayTeam(), rounds[round][0].getHomeTeam(), -1, -1, this, rounds[round][0].getAwayTeam().getHomeGround());
            }
        }
        
        for(int i = rounds.length / 2; i < rounds.length; i++){
        	for(int j = 0; j < rounds[i].length; j++){
        		rounds[i][j] =  new Match(rounds[i - (rounds.length/2)][j].getAwayTeam(), rounds[i - (rounds.length/2)][j].getHomeTeam(), -1, -1, this, rounds[i - (rounds.length/2)][j].getAwayTeam().getHomeGround());
        	}
        }
        
        for(int i = 0; i < rounds.length; i++){
        	for(int j = 0; j < rounds[i].length; j++){
        		rounds[i][j].setDate(this.getMatchDates()[i]); 
        	}
        }
		return rounds;
	}

	public void setMatchDates(Calendar[] matchDates) {
		this.matchDates = matchDates;
		if(matchDates[0].get(Calendar.YEAR) == 2009){
			Calendar cal = (Calendar) matchDates[0].clone();
			cal.add(Calendar.DATE, -2);
			this.setEarliestStartDate(cal);
		}
		/*this.setEarliestMatchDates(new Calendar[this.getMatchDates().length]);
		if(this.getMatchDates()[0].get(Calendar.YEAR) == 2009){
			System.arraycopy(this.getMatchDates(), 0, this.getEarliestMatchDates(), 0, this.getMatchDates().length);
		}
		else{
			for(int i = 0; i < this.getEarliestMatchDates().length; i++){
				this.getEarliestMatchDates()[i].add(Calendar.YEAR, 1);
			}
		}*/
	}

	public Calendar[] getMatchDates() {
		return matchDates;
	}

	public void setPromotedTo(League promotedTo) {
		this.promotedTo = promotedTo;
	}

	public League getPromotedTo() {
		return promotedTo;
	}

	public void setRelegatedTo(League relegatedTo) {
		this.relegatedTo = relegatedTo;
	}

	public League getRelegatedTo() {
		return relegatedTo;
	}

	public void setNumberOfTeamsPromoted(int numberOfTeamsPromoted) {
		this.numberOfTeamsPromoted = numberOfTeamsPromoted;
	}

	public int getNumberOfTeamsPromoted() {
		return numberOfTeamsPromoted;
	}

	public void setNumberOfTeamsRelegated(int numberOfTeamsRelegated) {
		this.numberOfTeamsRelegated = numberOfTeamsRelegated;
	}

	public int getNumberOfTeamsRelegated() {
		return numberOfTeamsRelegated;
	}

	public void setNumberOfTeamsInPlayoff(int numberOfTeamsInPlayoff) {
		this.numberOfTeamsInPlayoff = numberOfTeamsInPlayoff;
	}

	public int getNumberOfTeamsInPlayoff() {
		return numberOfTeamsInPlayoff;
	}

	public void setPlayoffs(Cup playoffs) {
		this.playoffs = playoffs;
	}

	public Cup getPlayoffs() {
		return playoffs;
	}
	
	@Override
	public void setMatchSchedule(){
		//System.out.println(this.getName());
		int daysToAddOn = 0;
		Calendar[] currentMatchDates = this.getMatchDates();
		currentMatchDates[0].add(Calendar.YEAR, 1);
		if((currentMatchDates[0].get(Calendar.YEAR) % 4 == 0 && (currentMatchDates[0].get(Calendar.MONTH) > 1 || (currentMatchDates[0].get(Calendar.MONTH) == 1 && currentMatchDates[0].get(Calendar.DATE) == 29))) || ((currentMatchDates[0].get(Calendar.YEAR) - 1) % 4 == 0 && currentMatchDates[0].get(Calendar.MONTH) <= 1)){
			currentMatchDates[0].add(Calendar.DATE, -2);
		}
		else{
			currentMatchDates[0].add(Calendar.DATE, -1);
		}
		if(currentMatchDates[0].get(Calendar.DATE) < this.getEarliestStartDate().get(Calendar.DATE)){
			currentMatchDates[0].add(Calendar.DATE, 7);
			daysToAddOn = 7;
		}
		for(int i = 1; i < currentMatchDates.length; i++){
			currentMatchDates[i].add(Calendar.YEAR, 1);
			if((currentMatchDates[i].get(Calendar.DATE) == 26 && currentMatchDates[i].get(Calendar.MONTH) == 11)){
				// do nothing
			}
			else if(currentMatchDates[i].get(Calendar.DATE) >= 28  && currentMatchDates[i].get(Calendar.MONTH) == 11){
				currentMatchDates[i].set(Calendar.DATE, 28);
				if(currentMatchDates[i].get(Calendar.DAY_OF_WEEK) == 4){
					currentMatchDates[i].add(Calendar.DATE, 3);
				}
				else if(currentMatchDates[i].get(Calendar.DAY_OF_WEEK) == 5){
					currentMatchDates[i].add(Calendar.DATE, 2);
				}
				else if(currentMatchDates[i].get(Calendar.DAY_OF_WEEK) == 6){
					currentMatchDates[i].add(Calendar.DATE, 1);
				}
				else{
					// do nothing
				}
			}
			else{
				if((currentMatchDates[i].get(Calendar.YEAR) % 4 == 0 && (currentMatchDates[i].get(Calendar.MONTH) > 1 || (currentMatchDates[i].get(Calendar.MONTH) == 1 && currentMatchDates[i].get(Calendar.DATE) == 29))) || ((currentMatchDates[i].get(Calendar.YEAR) - 1) % 4 == 0 && currentMatchDates[i].get(Calendar.MONTH) <= 1)){
					currentMatchDates[i].add(Calendar.DATE, (daysToAddOn - 2));
				}
				else{
					currentMatchDates[i].add(Calendar.DATE, (daysToAddOn - 1));
				}
			}
		}
	}

	public void setEarliestStartDate(Calendar earliestStartDate) {
		this.earliestStartDate = earliestStartDate;
	}

	public Calendar getEarliestStartDate() {
		return earliestStartDate;
	}

	/*public void setEarliestMatchDates(Calendar[] earliestMatchDates) {
		this.earliestMatchDates = earliestMatchDates;
	}

	public Calendar[] getEarliestMatchDates() {
		return earliestMatchDates;
	}*/
}
