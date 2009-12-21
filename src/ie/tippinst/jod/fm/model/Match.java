package ie.tippinst.jod.fm.model;

import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Match {
	private Calendar date;
	private Club homeTeam;
	private Club awayTeam;
	private int homeScore;
	private int awayScore;
	private Competition competition;
	private Stadium stadium;
	
	public Match() {
		super();
	}
	
	public Match(Club homeTeam, Club awayTeam, int homeScore,
			int awayScore, Competition competition, Stadium stadium) {
		super();
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.competition = competition;
		this.stadium = stadium;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Club getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Club homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Club getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Club awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	@Override
	public String toString() {
		return homeTeam.getName() + " " + (homeScore == -1 ? "" : homeScore) + "-" + (awayScore == -1 ? "" : awayScore) + " " + awayTeam.getName();
	}
	
	public double getTeamRating(boolean home){
		List<Player> players = null;
		if(home){
			players = this.getHomeTeam().getSelectedTeam();
		}
		else{
			players = this.getAwayTeam().getSelectedTeam();
		}
		double teamAbility = 0;
		double teamMorale = 0;
		double teamCondition = 0;
		double teamGround = 0;
		double teamLuck = 0;
		
		Iterator<Player> i = players.iterator();
		while(i.hasNext()){
			Player p = i.next();
			teamAbility+=p.getCurrentAbility();
			teamMorale+=p.getMorale();
			teamCondition+=p.getMatchCondition();
		}
		
		teamMorale = teamMorale * 0.008;
		teamCondition = teamCondition * 0.6;
		if(home)
			teamGround = 220;
		teamLuck = ((int)(Math.random() * 440)) + 1;
		
		// This is for testing only
		if(teamAbility == 0){
			//if(home){
				//teamAbility = (this.getHomeTeam().getReputation() / 10000) * 3740;
				teamAbility = 1800;
				teamMorale = 600;
				teamCondition = 650;
			//}
		}
		//
		
		return (teamAbility + teamMorale + teamCondition + teamGround + teamLuck);	
	}
	
	public void generateResult(){
		
		double homeTeamRating = getTeamRating(true);
		double awayTeamRating = getTeamRating(false);
		double difference = homeTeamRating - awayTeamRating;
		
		if(difference < 250 && difference > -250){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 95){
				this.setHomeScore(4);
			}
			else if(randomNum > 80){
				this.setHomeScore(3);
			}
			else if(randomNum > 55){
				this.setHomeScore(2);
			}
			else if(randomNum > 25){
				this.setHomeScore(1);
			}
			else{
				this.setHomeScore(0);
			}
			this.setAwayScore(this.getHomeScore());
		}
		else if(difference < 500 && difference > -500){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 90){
				if(difference > 0)
					this.setHomeScore(4);
				else
					this.setAwayScore(4);
			}
			else if(randomNum > 65){
				if(difference > 0)
					this.setHomeScore(3);
				else
					this.setAwayScore(3);
			}
			else if(randomNum > 35){
				if(difference > 0)
					this.setHomeScore(2);
				else
					this.setAwayScore(2);
			}
			else{
				if(difference > 0)
					this.setHomeScore(1);
				else
					this.setAwayScore(1);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 1);
			else
				this.setHomeScore(this.getAwayScore() - 1);
		}
		else if(difference < 750 && difference > -750){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 95){
				if(difference > 0)
					this.setHomeScore(5);
				else
					this.setAwayScore(5);
			}
			else if(randomNum > 80){
				if(difference > 0)
					this.setHomeScore(4);
				else
					this.setAwayScore(4);
			}
			else if(randomNum > 45){
				if(difference > 0)
					this.setHomeScore(3);
				else
					this.setAwayScore(3);
			}
			else{
				if(difference > 0)
					this.setHomeScore(2);
				else
					this.setAwayScore(2);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 2);
			else
				this.setHomeScore(this.getAwayScore() - 2);
		}
		else if(difference < 1000 && difference > -1000){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 95){
				if(difference > 0)
					this.setHomeScore(6);
				else
					this.setAwayScore(6);
			}
			else if(randomNum > 85){
				if(difference > 0)
					this.setHomeScore(5);
				else
					this.setAwayScore(5);
			}
			else if(randomNum > 50){
				if(difference > 0)
					this.setHomeScore(4);
				else
					this.setAwayScore(4);
			}
			else{
				if(difference > 0)
					this.setHomeScore(3);
				else
					this.setAwayScore(3);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 3);
			else
				this.setHomeScore(this.getAwayScore() - 3);
		}
		else if(difference < 1250 && difference > -1250){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 90){
				if(difference > 0)
					this.setHomeScore(6);
				else
					this.setAwayScore(6);
			}
			else if(randomNum > 60){
				if(difference > 0)
					this.setHomeScore(5);
				else
					this.setAwayScore(5);
			}
			else{
				if(difference > 0)
					this.setHomeScore(4);
				else
					this.setAwayScore(4);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 4);
			else
				this.setHomeScore(this.getAwayScore() - 4);
		}
		else if(difference < 1500 && difference > -1500){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 70){
				if(difference > 0)
					this.setHomeScore(6);
				else
					this.setAwayScore(6);
			}
			else{
				if(difference > 0)
					this.setHomeScore(5);
				else
					this.setAwayScore(5);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 5);
			else
				this.setHomeScore(this.getAwayScore() - 5);
		}
		else if(difference < 1750 && difference > -1750){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 80){
				if(difference > 0)
					this.setHomeScore(7);
				else
					this.setAwayScore(7);
			}
			else{
				if(difference > 0)
					this.setHomeScore(6);
				else
					this.setAwayScore(6);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 6);
			else
				this.setHomeScore(this.getAwayScore() - 6);
		}
		else if(difference < 2000 && difference > -2000){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 90){
				if(difference > 0)
					this.setHomeScore(8);
				else
					this.setAwayScore(8);
			}
			else{
				if(difference > 0)
					this.setHomeScore(7);
				else
					this.setAwayScore(7);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 7);
			else
				this.setHomeScore(this.getAwayScore() - 7);
		}
		else if(difference < 2250 && difference > -2250){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 95){
				if(difference > 0)
					this.setHomeScore(9);
				else
					this.setAwayScore(9);
			}
			else{
				if(difference > 0)
					this.setHomeScore(8);
				else
					this.setAwayScore(8);
			}
			if(difference > 0)
				this.setAwayScore(this.getHomeScore() - 8);
			else
				this.setHomeScore(this.getAwayScore() - 8);
		}
		else{
			if(difference > 0){
				this.setHomeScore((int) (difference/250));
				this.setAwayScore(0);
			}
			else{
				this.setAwayScore((int) (Math.abs(difference)/250));
				this.setHomeScore(0);
			}
			
		}
		
		//TODO: update each player's current ability after match
		//TODO: update each player's fitness, fatigue and condition after match
		//TODO: update each player's morale after every match
		//TODO: update each player's reputation after every match
		//TODO: update each player's happiness after every match
		//TODO: update each club's reputation after every match
		((League) this.getCompetition()).updateTable(this);
	}
}
