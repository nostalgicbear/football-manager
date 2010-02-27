package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Match implements Comparable<Match>, Serializable {
	private Calendar date;
	private Club homeTeam;
	private Club awayTeam;
	private int homeScore;
	private int awayScore;
	/*private List<Player> homeScorers = new ArrayList<Player>();
	private List<Player> awayScorers = new ArrayList<Player>();
	private List<Integer> homeGoalMinutes = new ArrayList<Integer>();
	private List<Integer> awayGoalMinutes = new ArrayList<Integer>();*/
	private List<Goal> homeGoals = new ArrayList<Goal>();
	private List<Goal> awayGoals = new ArrayList<Goal>();
	private Competition competition;
	private Stadium stadium;
	private int attendance;
	private boolean penalties = false;
	private Club winner;
	private int homeFirstLegScore = -1;
	private int awayFirstLegScore = -1;
	private boolean postponed = false;
	private boolean replay = false;
	private List<Card> homeYellowCards = new ArrayList<Card>();
	private List<Card> awayYellowCards = new ArrayList<Card>();
	private List<Card> homeRedCards = new ArrayList<Card>();
	private List<Card> awayRedCards = new ArrayList<Card>();
	
	public Match() {
		super();
	}
	
	public Match(Calendar date, Club homeTeam, Club awayTeam, int homeScore,
			int awayScore, Competition competition, Stadium stadium, boolean penalties) {
		super();
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.competition = competition;
		this.stadium = stadium;
		this.penalties = penalties;
	}
	
	public Match(Calendar date, Club homeTeam, Club awayTeam, int homeScore,
			int awayScore, Competition competition, Stadium stadium) {
		super();
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.competition = competition;
		this.stadium = stadium;
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
		String homeGoalscorers = "";
		/*Iterator<Player> i = this.getHomeScorers().iterator();
		while(i.hasNext()){
			Player p = i.next();
			homeGoalscorers = homeGoalscorers + ", " + p.getFirstName() + " " + p.getLastName() + " " + (((int)(Math.random() * 90)) + 1) + "'"; 
		}*/
		String awayGoalscorers = "";
		/*i = this.getHomeScorers().iterator();
		while(i.hasNext()){
			Player p = i.next();
			awayGoalscorers = awayGoalscorers + ", " + p.getFirstName() + " " + p.getLastName() + " " + (((int)(Math.random() * 90)) + 1) + "'"; 
		}*/
		String match = homeTeam.getName() + " " + (this.isPostponed() ? "P" : "") + (homeScore == -1 ? "" : homeScore) + "-" + (this.isPostponed() ? "P" : "") + (awayScore == -1 ? "" : awayScore) + " " + awayTeam.getName()
		+ (homeScore >= 100 ? "\n" + homeTeam.getName() + " Goalscorers: " + homeGoalscorers : "") + (awayScore >= 100 ? "\n" + awayTeam.getName() + " Goalscorers: " + awayGoalscorers : "");
		//System.out.println(match);
		//System.out.println(this.isPostponed());
		return match;
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
			teamGround = 60;
		teamLuck = ((int)(Math.random() * 600)) + 1;
		
		// This is for testing only
		if(teamAbility == 0){
			//if(home){
				//teamAbility = (this.getHomeTeam().getReputation() / 10000) * 3740;
				teamAbility = 1200;
				teamMorale = 300;
				teamCondition = 350;
			//}
		}
		//
		
		return (teamAbility + teamMorale + teamCondition + teamGround + teamLuck);	
	}
	
	public void generateResult(){
		double homeTeamRating = getTeamRating(true);
		double awayTeamRating = getTeamRating(false);
		double difference = homeTeamRating - awayTeamRating;
		
		if(difference < 100 && difference > -100){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 99){
				this.setHomeScore(4);
			}
			else if(randomNum > 94){
				this.setHomeScore(3);
			}
			else if(randomNum > 74){
				this.setHomeScore(2);
			}
			else if(randomNum > 29){
				this.setHomeScore(1);
			}
			else{
				this.setHomeScore(0);
			}
			this.setAwayScore(this.getHomeScore());
		}
		else if(difference < 350 && difference > -350){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 98){
				if(difference > 0)
					this.setHomeScore(4);
				else
					this.setAwayScore(4);
			}
			else if(randomNum > 84){
				if(difference > 0)
					this.setHomeScore(3);
				else
					this.setAwayScore(3);
			}
			else if(randomNum > 44){
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
		else if(difference < 600 && difference > -600){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 98){
				if(difference > 0)
					this.setHomeScore(5);
				else
					this.setAwayScore(5);
			}
			else if(randomNum > 89){
				if(difference > 0)
					this.setHomeScore(4);
				else
					this.setAwayScore(4);
			}
			else if(randomNum > 52){
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
		else if(difference < 850 && difference > -850){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 99){
				if(difference > 0)
					this.setHomeScore(6);
				else
					this.setAwayScore(6);
			}
			else if(randomNum > 92){
				if(difference > 0)
					this.setHomeScore(5);
				else
					this.setAwayScore(5);
			}
			else if(randomNum > 75){
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
		else if(difference < 1100 && difference > 1100){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 96){
				if(difference > 0)
					this.setHomeScore(6);
				else
					this.setAwayScore(6);
			}
			else if(randomNum > 69){
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
		else if(difference < 1350 && difference > -1350){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 75){
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
		else if(difference < 1600 && difference > -1600){
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
		else if(difference < 1850 && difference > -1850){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 80){
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
		else if(difference < 2100 && difference > -2100){
			int randomNum = ((int)(Math.random() * 100)) + 1;
			if(randomNum > 80){
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
		int maxHomeAttendance = (this.getStadium().getCapacity() / 20) * 19;
		int maxAwayAttendance = this.getStadium().getCapacity() / 20;
		int avgHomeAttendance = this.getHomeTeam().getAverageAttendance();
		int avgAwayAttendance = this.getAwayTeam().getAverageAttendance() / 20;
		int homeAttendance = ((avgHomeAttendance > maxHomeAttendance) ? maxHomeAttendance : avgHomeAttendance);
		int awayAttendance = ((avgAwayAttendance > maxAwayAttendance) ? maxAwayAttendance : avgAwayAttendance);
		this.setAttendance(homeAttendance + awayAttendance);
		
		if (this.getHomeTeam().getSelectedTeam().size() == 11) {
			for (int i = 0; i < this.getHomeScore(); i++) {
				Goal goal = new Goal();
				goal.setMinute((int) (Math.random() * 90) + 1);
				//this.getHomeGoalMinutes().add(new Integer((int) (Math.random() * 90) + 1));
				int index = (int) (Math.random() * 1341);
				if (index == 0)
					index = 0;
				else if (index <= 30)
					index = 1;
				else if (index <= 60)
					index = 2;
				else if (index <= 100)
					index = 3;
				else if (index <= 140)
					index = 4;
				else if (index <= 290)
					index = 5;
				else if (index <= 440)
					index = 6;
				else if (index <= 590)
					index = 7;
				else if (index <= 740)
					index = 8;
				else if (index <= 1040)
					index = 9;
				else if (index <= 1340)
					index = 10;
				//this.getHomeScorers().add(this.getHomeTeam().getSelectedTeam().get(index));
				goal.setScorer(this.getHomeTeam().getSelectedTeam().get(index));
				this.getHomeGoals().add(goal);
				this.getHomeTeam().getSelectedTeam().get(index).setLeagueGoals(this.getHomeTeam().getSelectedTeam().get(index).getLeagueGoals() + 1);
			}
		}
		this.getYellowCards(true);
		this.getRedCards(true);
		
		if (this.getAwayTeam().getSelectedTeam().size() == 11) {
			for (int i = 0; i < this.getAwayScore(); i++) {
				Goal goal = new Goal();
				goal.setMinute((int) (Math.random() * 90) + 1);
				//this.getAwayGoalMinutes().add(new Integer((int) (Math.random() * 90) + 1));
				int index = (int) (Math.random() * 1341);
				if (index == 0)
					index = 0;
				else if (index <= 30)
					index = 1;
				else if (index <= 60)
					index = 2;
				else if (index <= 100)
					index = 3;
				else if (index <= 140)
					index = 4;
				else if (index <= 290)
					index = 5;
				else if (index <= 440)
					index = 6;
				else if (index <= 590)
					index = 7;
				else if (index <= 740)
					index = 8;
				else if (index <= 1040)
					index = 9;
				else if (index <= 1340)
					index = 10;
				//this.getAwayScorers().add(this.getAwayTeam().getSelectedTeam().get(index));
				goal.setScorer(this.getAwayTeam().getSelectedTeam().get(index));
				this.getAwayGoals().add(goal);
				this.getAwayTeam().getSelectedTeam().get(index).setLeagueGoals(this.getAwayTeam().getSelectedTeam().get(index).getLeagueGoals() + 1);
			}
		}
		
		this.getYellowCards(false);
		this.getRedCards(false);
		
		if (this.getHomeFirstLegScore() == -1) {
			if (this.getHomeScore() > this.getAwayScore()) {
				this.setWinner(this.getHomeTeam());
			} else if (this.getAwayScore() > this.getHomeScore()) {
				this.setWinner(this.getAwayTeam());
			} else if (this.hasReplay()) {
				System.out.println(this);
			} else if (this.hasPenalties()) {
				if (((int) (Math.random() * 2)) == 0) {
					this.setWinner(this.getHomeTeam());
				} else {
					this.setWinner(this.getAwayTeam());
				}
			}
		}
		else{
			if (this.getHomeScore() + this.getAwayFirstLegScore() > this.getAwayScore() + this.getHomeFirstLegScore()) {
				this.setWinner(this.getHomeTeam());
			} else if (this.getAwayScore() + this.getHomeFirstLegScore() > this.getHomeScore() + this.getAwayFirstLegScore()) {
				this.setWinner(this.getAwayTeam());
			} else if (this.hasPenalties()) {
				if (((int) (Math.random() * 2)) == 0) {
					this.setWinner(this.getHomeTeam());
				} else {
					this.setWinner(this.getAwayTeam());
				}
			}
		}
		Iterator<Player> i = this.getHomeTeam().getSelectedTeam().iterator();
		while(i.hasNext()){
			Player p = i.next();
			//TODO: update each player's current ability after match
			//update each player's fitness, fatigue and condition after match
			p.setFitness(p.getFitness() + 250);
			p.setFatigue(p.getFatigue() + 250);
			double decrease = (((10000.0 - p.getFitness()) + p.getFatigue()) / 450.0);
			p.setMatchCondition(p.getMatchCondition() - decrease);
			p.setLeagueAppearances(p.getLeagueAppearances() + 1);
			p.setMorale(p.getMorale() + (200 * (this.getHomeScore() - this.getAwayScore())));
			//TODO: update each player's reputation after every match
			//TODO: update each player's happiness after every match
			//TODO: update each club's reputation after every match
		}
		
		i = this.getAwayTeam().getSelectedTeam().iterator();
		while(i.hasNext()){
			Player p = i.next();
			//TODO: update each player's current ability after match
			//update each player's fitness, fatigue and condition after match
			p.setFitness(p.getFitness() + 250);
			p.setFatigue(p.getFatigue() + 250);
			double decrease = (((10000.0 - p.getFitness()) + p.getFatigue()) / 450.0);
			p.setMatchCondition(p.getMatchCondition() - decrease);
			p.setLeagueAppearances(p.getLeagueAppearances() + 1);
			//update each player's morale after every match
			p.setMorale(p.getMorale() + (200 * (this.getAwayScore() - this.getHomeScore())));
			//TODO: update each player's reputation after every match
			//TODO: update each player's happiness after every match
			//TODO: update each club's reputation after every match
		}
		int tickets = homeAttendance - this.getHomeTeam().getNumberOfSeasonTicketHolders();
		if(tickets < 0)
			tickets = 0;
		this.getHomeTeam().setBankBalance(this.getHomeTeam().getBankBalance() + tickets * this.getHomeTeam().getTicketPrice());
		this.getAwayTeam().setBankBalance(this.getAwayTeam().getBankBalance() + awayAttendance * this.getHomeTeam().getTicketPrice());
		if(this.getCompetition() instanceof League)
			((League) this.getCompetition()).updateTable(this);
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public int getAttendance() {
		return attendance;
	}

	/*public void setHomeScorers(List<Player> homeScorers) {
		this.homeScorers = homeScorers;
	}

	public List<Player> getHomeScorers() {
		return homeScorers;
	}

	public void setAwayScorers(List<Player> awayScorers) {
		this.awayScorers = awayScorers;
	}

	public List<Player> getAwayScorers() {
		return awayScorers;
	}*/

	public void setPenalties(boolean penalties) {
		this.penalties = penalties;
	}

	public boolean hasPenalties() {
		return penalties;
	}

	public void setWinner(Club winner) {
		this.winner = winner;
	}

	public Club getWinner() {
		return winner;
	}

	public void setHomeFirstLegScore(int homeFirstLegScore) {
		this.homeFirstLegScore = homeFirstLegScore;
	}

	public int getHomeFirstLegScore() {
		return homeFirstLegScore;
	}

	public void setAwayFirstLegScore(int awayFirstLegScore) {
		this.awayFirstLegScore = awayFirstLegScore;
	}

	public int getAwayFirstLegScore() {
		return awayFirstLegScore;
	}

	@Override
	public int compareTo(Match m) {
		int compare = this.getDate().compareTo(m.getDate());
        return compare;
	}

	public void setPostponed(boolean postponed) {
		this.postponed = postponed;
	}

	public boolean isPostponed() {
		return postponed;
	}

	public void setReplay(boolean replay) {
		this.replay = replay;
	}

	public boolean hasReplay() {
		return replay;
	}

	public void setHomeYellowCards(List<Card> homeYellowCards) {
		this.homeYellowCards = homeYellowCards;
	}

	public List<Card> getHomeYellowCards() {
		return homeYellowCards;
	}

	public void setAwayYellowCards(List<Card> awayYellowCards) {
		this.awayYellowCards = awayYellowCards;
	}

	public List<Card> getAwayYellowCards() {
		return awayYellowCards;
	}

	public void setHomeRedCards(List<Card> homeRedCards) {
		this.homeRedCards = homeRedCards;
	}

	public List<Card> getHomeRedCards() {
		return homeRedCards;
	}

	public void setAwayRedCards(List<Card> awayRedCards) {
		this.awayRedCards = awayRedCards;
	}

	public List<Card> getAwayRedCards() {
		return awayRedCards;
	}
	
	private void getYellowCards(boolean home){
		Club c = this.getAwayTeam();
		List<Card> teamYellowCards = this.getAwayYellowCards();
		if(home){
			c = this.getHomeTeam();
			teamYellowCards = this.getHomeYellowCards();
		}
		if (c.getSelectedTeam().size() == 11) {
			int numberOfYellowCards = getNumberOfYellowCards();
			for (int i = 0; i < numberOfYellowCards; i++) {
				int index = (int) (Math.random() * 11);
				while(teamYellowCards.contains(c.getSelectedTeam().get(index)))
					index = (int) (Math.random() * 11);
				teamYellowCards.add(new Card(c.getSelectedTeam().get(index), ((int) (Math.random() * 90) + 1), 0));
			}
		}
	}
	
	private void getRedCards(boolean home){
		Club c = this.getAwayTeam();
		List<Card> teamRedCards = this.getAwayRedCards();
		if(home){
			c = this.getHomeTeam();
			teamRedCards = this.getHomeRedCards();
		}
		if (c.getSelectedTeam().size() == 11) {
			int numberOfRedCards = getNumberOfRedCards();
			for (int i = 0; i < numberOfRedCards; i++) {
				int index = (int) (Math.random() * 11);
				while(teamRedCards.contains(c.getSelectedTeam().get(index)))
					index = (int) (Math.random() * 11);
				teamRedCards.add(new Card(c.getSelectedTeam().get(index), ((int) (Math.random() * 90) + 1), 1));
			}
		}
	}
	
	private int getNumberOfRedCards(){
		int redCardDeterminant = (int) (Math.random() * 1000) + 1;
		int redCards = -1;
		if(redCardDeterminant > 100){
			//0
			redCards = 0;
		}
		else if(redCardDeterminant > 10){
			//1
			redCards = 1;
		}
		else if(redCardDeterminant > 1){
			//2
			redCards = 2;
		}
		else{
			//3
			redCards = 3;
		}
		return redCards;
	}

	private int getNumberOfYellowCards() {
		int yellowCardDeterminant = (int) (Math.random() * 1000) + 1;
		int yellowCards = -1;
		if(yellowCardDeterminant > 850){
			//0
			yellowCards = 0;
		}
		else if(yellowCardDeterminant > 650){
			//1
			yellowCards = 1;
		}
		else if(yellowCardDeterminant > 400){
			//2
			yellowCards = 2;
		}
		else if(yellowCardDeterminant > 220){
			//3
			yellowCards = 3;
		}
		else if(yellowCardDeterminant > 120){
			//4
			yellowCards = 4;
		}
		else if(yellowCardDeterminant > 60){
			//5
			yellowCards = 5;
		}
		else if(yellowCardDeterminant > 20){
			//6
			yellowCards = 6;
		}
		else if(yellowCardDeterminant > 8){
			//7
			yellowCards = 7;
		}
		else if(yellowCardDeterminant > 3){
			//8
			yellowCards = 8;
		}
		else if(yellowCardDeterminant > 1){
			//9
			yellowCards = 9;
		}
		else{
			//10
			yellowCards = 10;
		}
		return yellowCards;
	}
	
	public int checkGoals(boolean home, int minute){
		int numberOfGoals = 0;
		List<Goal> goals = this.getAwayGoals();
		if(home)
			goals = this.getHomeGoals();
		Iterator<Goal> i = goals.iterator();
		while(i.hasNext()){
			int goalMinute = i.next().getMinute();
			if(goalMinute == minute){
				numberOfGoals++;
			}
		}
		return numberOfGoals;
	}
	
	public List<Goal> getGoalsForMinute(boolean home, int minute){
		List<Goal> goalsForMinute = new ArrayList<Goal>();
		List<Goal> goals = this.getAwayGoals();
		if(home)
			goals = this.getHomeGoals();
		Iterator<Goal> i = goals.iterator();
		while(i.hasNext()){
			Goal goal = i.next();
			if(goal.getMinute() == minute){
				goalsForMinute.add(goal);
			}
		}
		return goalsForMinute;
	}
	
	public List<Card> getCardsForMinute(boolean home, int minute){
		List<Card> cardsForMinute = new ArrayList<Card>();
		List<Card> yellowCards = this.getAwayYellowCards();
		List<Card> redCards = this.getAwayRedCards();
		if(home){
			yellowCards = this.getHomeYellowCards();
			redCards = this.getHomeRedCards();
			
		}
		Iterator<Card> i = yellowCards.iterator();
		while(i.hasNext()){
			Card card = i.next();
			if(card.getMinute() == minute){
				cardsForMinute.add(card);
			}
		}
		i = redCards.iterator();
		while(i.hasNext()){
			Card card = i.next();
			if(card.getMinute() == minute){
				cardsForMinute.add(card);
			}
		}
		return cardsForMinute;
	}

	/*public void setHomeGoalMinutes(List<Integer> homeGoalMinutes) {
		this.homeGoalMinutes = homeGoalMinutes;
	}

	public List<Integer> getHomeGoalMinutes() {
		return homeGoalMinutes;
	}

	public void setAwayGoalMinutes(List<Integer> awayGoalMinutes) {
		this.awayGoalMinutes = awayGoalMinutes;
	}

	public List<Integer> getAwayGoalMinutes() {
		return awayGoalMinutes;
	}*/

	public void setHomeGoals(List<Goal> homeGoals) {
		this.homeGoals = homeGoals;
	}

	public List<Goal> getHomeGoals() {
		return homeGoals;
	}

	public void setAwayGoals(List<Goal> awayGoals) {
		this.awayGoals = awayGoals;
	}

	public List<Goal> getAwayGoals() {
		return awayGoals;
	}
}
