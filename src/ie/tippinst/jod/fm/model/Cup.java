package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cup extends Competition implements Serializable {
	
	private static final long serialVersionUID = 4642933844714495259L;
	List<Round> rounds = new ArrayList<Round>();
	int numberOfRounds;
	
	public Cup(){
	}
	
	public List<Round> getRounds() {
		return rounds;
	}

	public void setRounds(List<Round> rounds) {
		this.rounds = rounds;
	}

	public int getNumberOfRounds() {
		return numberOfRounds;
	}

	public void setNumberOfRounds(int numberOfRounds) {
		this.numberOfRounds = numberOfRounds;
	}
	
	public void createRound(int numberOfTeams, int roundNumber, List<Calendar> roundDate){
		Round round = new Round();
		System.out.println(roundDate.size());
		if(roundDate.size() == 2){
			round.setTwoLegs(true);
		}
		else{
			round.setTwoLegs(false);
		}
		round.setNumberOfTeams(numberOfTeams);
		round.setRoundNumber(roundNumber);
		round.setRoundDate(roundDate);
		this.getRounds().add(round);
	}
}
