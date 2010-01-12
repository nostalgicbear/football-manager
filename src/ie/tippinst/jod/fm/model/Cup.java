package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
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
	
	@Override
	public void setMatchSchedule(){
		Iterator<Round> i1 = this.getRounds().iterator();
		while(i1.hasNext()){
			Round r = i1.next();
			if(r.getRoundDate().get(0).get(Calendar.YEAR) == 2009 || ((r.getRoundDate().get(0).get(Calendar.YEAR) == 2010) && (r.getRoundDate().get(0).get(Calendar.MONTH) <= 5))){
				for(int i = 0; i < r.getRoundDate().size(); i++){
					r.getEarliestRoundDate().add((Calendar) r.getRoundDate().get(i).clone());
					r.getEarliestRoundDate().get(i).add(Calendar.DATE, -2);
				}
			}
			Iterator<Calendar> i2 = r.getRoundDate().iterator();
			while(i2.hasNext()){
				Calendar c = i2.next();
				c.add(Calendar.YEAR, 1);					
				if((c.get(Calendar.YEAR) % 4 == 0 && (c.get(Calendar.MONTH) > 1 || (c.get(Calendar.MONTH) == 1 && c.get(Calendar.DATE) == 29))) || ((c.get(Calendar.YEAR) - 1) % 4 == 0 && c.get(Calendar.MONTH) <= 1)){
					c.add(Calendar.DATE, -2);
				}
				else{
					c.add(Calendar.DATE, -1);
				}				
				if(c.get(Calendar.DAY_OF_YEAR) < r.getEarliestRoundDate().get(r.getRoundDate().indexOf(c)).get(Calendar.DAY_OF_YEAR)){
					c.add(Calendar.DATE, 7);
				}
			}
		}
	}
	
	public void createRound(int numberOfTeams, int roundNumber, List<Calendar> roundDate, boolean twoLegs){
		Round round = new Round();
		//System.out.println(roundDate.size());
		round.setNumberOfTeams(numberOfTeams);
		round.setRoundNumber(roundNumber);
		round.setRoundDate(roundDate);
		round.setTwoLegs(twoLegs);
		this.getRounds().add(round);
	}
}
