package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.Arrays;
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

	public void setTable(int[][] table) {
		this.table = table;
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
        		Calendar c = new GregorianCalendar();
        		 switch(i){
                 case 0:
                 	c.set(2009, 7, 15);
                 	break;
                 case 1:
                 	c.set(2009, 7, 19);
                 	break;
                 case 2:
                 	c.set(2009, 7, 22);
                 	break;
                 case 3:
                 	c.set(2009, 7, 29);
                 	break;
                 case 4:
                 	c.set(2009, 8, 12);
                 	break;
                 case 5:
                 	c.set(2009, 8, 19);
                 	break;
                 case 6:
                 	c.set(2009, 8, 26);
                 	break;
                 case 7:
                 	c.set(2009, 9, 3);
                 	break;
                 case 8:
                 	c.set(2009, 9, 17);
                 	break;
                 case 9:
                 	c.set(2009, 9, 24);
                 	break;
                 case 10:
                 	c.set(2009, 9, 31);
                 	break;
                 case 11:
                 	c.set(2009, 10, 7);
                 	break;
                 case 12:
                 	c.set(2009, 10, 21);
                 	break;
                 case 13:
                 	c.set(2009, 10, 28);
                 	break;
                 case 14:
                 	c.set(2009, 11, 5);
                 	break;
                 case 15:
                 	c.set(2009, 11, 12);
                 	break;
                 case 16:
                 	c.set(2009, 11, 16);
                 	break;
                 case 17:
                 	c.set(2009, 11, 19);
                 	break;
                 case 18:
                 	c.set(2009, 11, 26);
                 	break;
                 case 19:
                  	c.set(2009, 11, 28);
                  	break;
                 case 20:
                  	c.set(2010, 0, 9);
                  	break;
                 case 21:
                  	c.set(2010, 0, 16);
                  	break;
                 case 22:
                  	c.set(2010, 0, 27);
                  	break;
                 case 23:
                  	c.set(2010, 0, 30);
                  	break;
                 case 24:
                  	c.set(2010, 1, 6);
                  	break;
                 case 25:
                  	c.set(2010, 1, 10);
                  	break;
                 case 26:
                  	c.set(2010, 1, 20);
                  	break;
                 case 27:
                  	c.set(2010, 1, 27);
                  	break;
                 case 28:
                  	c.set(2010, 2, 6);
                  	break;
                 case 29:
                  	c.set(2010, 2, 13);
                  	break;
                 case 30:
                  	c.set(2010, 2, 20);
                  	break;
                 case 31:
                  	c.set(2010, 2, 27);
                  	break;
                 case 32:
                  	c.set(2010, 3, 3);
                  	break;
                 case 33:
                  	c.set(2010, 3, 10);
                  	break;
                 case 34:
                  	c.set(2010, 3, 17);
                  	break;
                 case 35:
                  	c.set(2010, 3, 24);
                  	break;
                 case 36:
                  	c.set(2010, 4, 1);
                  	break;
                 case 37:
                  	c.set(2010, 4, 9);
                  	break;
                 default: System.out.println("ERROR");
                 }
        		rounds[i][j].setDate(c); 
        	}
        }
		return rounds;
	}
}
