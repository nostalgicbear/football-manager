package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Player extends Person implements Serializable {
	
	private static final long serialVersionUID = -2483513857459943753L;
	private double marketValue;
	private double saleValue;
	private int intCaps;
	private int intGoals;
	private int leftFootAbility;
	private int rightFootAbility;
	private int fitness;
	private double matchCondition;
	private int fatigue;
	private int happinessAtClub;
	private int morale;
	private int goalkeepingAbility;
	private int leftFullbackAbility;
	private int rightFullbackAbility;
	private int centrebackAbility;
	private int rightMidfieldAbility;
	private int leftMidfieldAbility;
	private int centreMidfieldAbility;
	private int strikerAbility;
	private Injury injury;
	private int daysUnavailable;
	private String position;
	private enum SquadStatus {INDISPENSABLE, IMPORTANT, SQUAD, TOPYOUNGSTER, DECENTYOUNGSTER, NOTNEEDED};
	private SquadStatus status;
	private int handling;
	private int reflexes;
	private int commandOfArea;
	private int decisions;
	private int finishing;
	private int heading;
	private int marking;
	private int passing;
	private int crossing;
	private int dribbling;
	private int tackling;
	private int longShots;
	private int penaltyTaking;
	private int influence;
	private int pace;
	private int strength;
	private int stamina;
	private int loyalty;
	private int ambition;
	private int leagueAppearances = 0;
	private int leagueGoals = 0;
	private List<Club> interested = new ArrayList<Club>();
	
	public Player(){
		super();
	}
	
	public Player(int id, String firstName, String lastName, Nation nationality,
			double wages, int reputation, Calendar dob, int currentAbility,
			int potentialAbility, Calendar contractExpiry, Club currentClub,
			double marketValue, double saleValue, int intCaps, int intGoals,
			int leftFootAbility, int rightFootAbility, int fitness,
			int happinessAtClub, int morale, int goalkeepingAbility,
			int leftFullbackAbility, int rightFullbackAbility,
			int centrebackAbility, int rightMidfieldAbility,
			int leftMidfieldAbility, int centreMidfieldAbility,
			int strikerAbility, Injury injury) {
		super(id, firstName, lastName, nationality, wages, reputation, dob,
				currentAbility, potentialAbility, contractExpiry, currentClub);
		this.marketValue = marketValue;
		this.saleValue = saleValue;
		this.intCaps = intCaps;
		this.intGoals = intGoals;
		this.leftFootAbility = leftFootAbility;
		this.rightFootAbility = rightFootAbility;
		this.fitness = fitness;
		this.happinessAtClub = happinessAtClub;
		this.morale = morale;
		this.goalkeepingAbility = goalkeepingAbility;
		this.leftFullbackAbility = leftFullbackAbility;
		this.rightFullbackAbility = rightFullbackAbility;
		this.centrebackAbility = centrebackAbility;
		this.rightMidfieldAbility = rightMidfieldAbility;
		this.leftMidfieldAbility = leftMidfieldAbility;
		this.centreMidfieldAbility = centreMidfieldAbility;
		this.strikerAbility = strikerAbility;
		this.injury = injury;
	}
	
	public double getMarketValue() {
		return marketValue;
	}
	
	private int getMonthsRemainingOnContract(Calendar date){
		return (int) ((this.getContractExpiry().getTimeInMillis() - date.getTimeInMillis()) / 86400000L);
	}

	public void setMarketValue(Calendar date) {
		double positionValue = 1;
		int currentAbility = this.getCurrentAbility();
		double constant = 10000;
		
		if(currentAbility >= 150){
			constant = 100000;
		}
		else if(currentAbility >= 100){
			constant = 50000;
		}
		else if(currentAbility >= 50){
			constant = 20000;
		}
		
		if(this.getStrikerAbility() > 15){
			positionValue = 3;
		}
		else if(this.getRightMidfieldAbility() > 15 || this.getLeftMidfieldAbility() > 15 || this.getCentreMidfieldAbility() > 15){
			positionValue = 2.5;
		}
		else if(this.getRightFullbackAbility() > 15 || this.getLeftFullbackAbility() > 15 || this.getCentrebackAbility() > 15){
			positionValue = 1.5;
		}
		
		this.marketValue = this.getCurrentAbility() * constant * (getMonthsRemainingOnContract(date)/1826.0) * positionValue;
		DecimalFormat myFormatter = new DecimalFormat("000,000");
		@SuppressWarnings("unused")
		String output = myFormatter.format(this.marketValue);
		//System.out.println((this.getCurrentAbility() / 200.0));
		//System.out.println((getMonthsRemainingOnContract(date)/1826.0));
		//System.out.println(positionValue);
		//System.out.println(this.getFirstName() + " " + this.getLastName() + " €" + output);
	}

	public double getSaleValue() {
		return saleValue;
	}

	public void setSaleValue() {
		SquadStatus status = this.getStatus();
		switch(status){
		case INDISPENSABLE:
			this.saleValue = this.getMarketValue() * 4;
			break;
		case IMPORTANT:
			this.saleValue = this.getMarketValue() * 3;
			break;
		case SQUAD:
			this.saleValue = this.getMarketValue() * 1.5;
			break;
		case TOPYOUNGSTER:
			this.saleValue = this.getMarketValue() * 3;
			break;
		case DECENTYOUNGSTER:
			this.saleValue = this.getMarketValue() * 1.5;
			break;
		case NOTNEEDED:
			this.saleValue = this.getMarketValue() * 0.5;
			break;
		default: System.out.println("Error");
				 break;
		}
		/*DecimalFormat format = new DecimalFormat("000,000");
		String output = format.format(this.getSaleValue());
		System.out.println(output);*/
	}

	public int getIntCaps() {
		return intCaps;
	}

	public void setIntCaps(int intCaps) {
		this.intCaps = intCaps;
	}

	public int getIntGoals() {
		return intGoals;
	}

	public void setIntGoals(int intGoals) {
		this.intGoals = intGoals;
	}

	public int getLeftFootAbility() {
		return leftFootAbility;
	}

	public void setLeftFootAbility(int leftFootAbility) {
		this.leftFootAbility = leftFootAbility;
	}

	public int getRightFootAbility() {
		return rightFootAbility;
	}

	public void setRightFootAbility(int rightFootAbility) {
		this.rightFootAbility = rightFootAbility;
	}

	public int getFitness() {
		return fitness;
	}

	public void setFitness(int fitness) {
		if(fitness <= 10000 && fitness >= 1)
			this.fitness = fitness;
		else if(fitness > 10000)
			this.fitness = 10000;
		else if(fitness < 1)
			this.fitness = 1;	
	}

	public int getHappinessAtClub() {
		return happinessAtClub;
	}

	public void setHappinessAtClub(int happinessAtClub) {
		this.happinessAtClub = happinessAtClub;
	}

	public int getMorale() {
		return morale;
	}

	public void setMorale(int morale) {
		if(morale > 10000)
			morale = 10000;
		else if(morale < 1)
			morale = 1;
		this.morale = morale;
	}

	public int getGoalkeepingAbility() {
		return goalkeepingAbility;
	}

	public void setGoalkeepingAbility(int goalkeepingAbility) {
		this.goalkeepingAbility = goalkeepingAbility;
	}

	public int getLeftFullbackAbility() {
		return leftFullbackAbility;
	}

	public void setLeftFullbackAbility(int leftFullbackAbility) {
		this.leftFullbackAbility = leftFullbackAbility;
	}

	public int getRightFullbackAbility() {
		return rightFullbackAbility;
	}

	public void setRightFullbackAbility(int rightFullbackAbility) {
		this.rightFullbackAbility = rightFullbackAbility;
	}

	public int getCentrebackAbility() {
		return centrebackAbility;
	}

	public void setCentrebackAbility(int centrebackAbility) {
		this.centrebackAbility = centrebackAbility;
	}

	public int getRightMidfieldAbility() {
		return rightMidfieldAbility;
	}

	public void setRightMidfieldAbility(int rightMidfieldAbility) {
		this.rightMidfieldAbility = rightMidfieldAbility;
	}

	public int getLeftMidfieldAbility() {
		return leftMidfieldAbility;
	}

	public void setLeftMidfieldAbility(int leftMidfieldAbility) {
		this.leftMidfieldAbility = leftMidfieldAbility;
	}

	public int getCentreMidfieldAbility() {
		return centreMidfieldAbility;
	}

	public void setCentreMidfieldAbility(int centreMidfieldAbility) {
		this.centreMidfieldAbility = centreMidfieldAbility;
	}

	public int getStrikerAbility() {
		return strikerAbility;
	}

	public void setStrikerAbility(int strikerAbility) {
		this.strikerAbility = strikerAbility;
	}

	public Injury getInjury() {
		return injury;
	}

	public void setInjury(Injury injury) {
		this.injury = injury;
	}
	
	public boolean isInjured(){
		if(this.getInjury() == null){
			return false;
		}
		return true;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition() {
		this.position="";
		if(this.goalkeepingAbility >= 15)
			this.position = this.position + "GK/";
		if((this.rightFullbackAbility >= 15)||(this.leftFullbackAbility >= 15)||(this.centrebackAbility >= 15)){
			this.position = this.position + "D";
			if(this.rightFullbackAbility >= 15)
				this.position = this.position + "R";
			if(this.leftFullbackAbility >= 15)
				this.position = this.position + "L";
			if(this.centrebackAbility >= 15)
				this.position = this.position + "C";
			this.position = this.position + "/";
		}
		if((this.rightMidfieldAbility >= 15)||(this.leftMidfieldAbility >= 15)||(this.centreMidfieldAbility >= 15)){
			this.position = this.position + "M";
			if(this.rightMidfieldAbility >= 15)
				this.position = this.position + "R";
			if(this.leftMidfieldAbility >= 15)
				this.position = this.position + "L";
			if(this.centreMidfieldAbility >= 15)
				this.position = this.position + "C";
			this.position = this.position + "/";
		}
		if(this.strikerAbility >= 15)
			this.position = this.position + "S/";
		this.position = this.position.substring(0, (this.position.length() - 1));
	}

	public String returnString() {
		return toString() + "Player [centreMidfieldAbility=" + centreMidfieldAbility
				+ ", centrebackAbility=" + centrebackAbility + ", fitness="
				+ fitness + ", goalkeepingAbility=" + goalkeepingAbility
				+ ", happinessAtClub=" + happinessAtClub + ", injury=" + injury
				+ ", intCaps=" + intCaps + ", intGoals=" + intGoals
				+ ", leftFootAbility=" + leftFootAbility
				+ ", leftFullbackAbility=" + leftFullbackAbility
				+ ", leftMidfieldAbility=" + leftMidfieldAbility
				+ ", marketValue=" + marketValue + ", morale=" + morale
				+ ", rightFootAbility=" + rightFootAbility
				+ ", rightFullbackAbility=" + rightFullbackAbility
				+ ", rightMidfieldAbility=" + rightMidfieldAbility
				+ ", saleValue=" + saleValue + ", strikerAbility="
				+ strikerAbility + "]";
	}
	
	public List<String> getPlayerProfileInfo(Calendar date){
		List<String> list = new ArrayList<String>();
		list.add(this.getFirstName() + " " + this.getLastName());
		list.add(this.getAge() + "");
		list.add(this.getDob().get(Calendar.DATE) + "/" + (this.getDob().get(Calendar.MONTH) + 1) + "/" + this.getDob().get(Calendar.YEAR));
		list.add(this.getNationality().getName());
		if(this.getReputation() * 50 >= 9500){
			list.add("Worldwide");
		}
		else if(this.getReputation() * 50 >= 7500){
			list.add("Continental");
		}
		else if(this.getReputation() * 50 >= 4500){
			list.add("National");
		}
		else if(this.getReputation() * 50 >= 1500){
			list.add("Regional");
		}
		else {
			list.add("Local");
		}
		list.add(this.getPosition());
		if(this.getRightFootAbility() >= 15 && this.getLeftFootAbility() >= 15){
			list.add("Both");
		}
		else if(this.getRightFootAbility() >= 15){
			list.add("Right");
		}
		else if(this.getLeftFootAbility() >= 15){
			list.add("Left");
		}
		if(this.getMorale() >= 8000){
			list.add("Very High");
		}
		else if(this.getMorale() >= 6000){
			list.add("High");
		}
		else if(this.getMorale() >= 4000){
			list.add("Ok");
		}
		else if(this.getMorale() >= 2000){
			list.add("Low");
		}
		else{
			list.add("Very Low");
		}
		String timeOut;
		if(this.getDaysUnavailable() < 14){
			timeOut = this.getDaysUnavailable() + (this.getDaysUnavailable() == 1 ? " day" : " days");
		}
		else if(this.getDaysUnavailable() < 60){
			timeOut = this.getDaysUnavailable() / 7 + " weeks";
		}
		else{
			timeOut = this.getDaysUnavailable() / 30 + " months";
		}
		list.add((this.getInjury() == null) ? "None" : this.getInjury().getName() + " (Out for " + timeOut + ")");
		list.add(this.getMatchCondition() + "%");
		if(this.getFatigue() >= 9000){
			list.add("Exhausted");
		}
		else if(this.getFatigue() >= 7500){
			list.add("Tired");
		}
		else if(this.getFatigue() >= 6000){
			list.add("Slightly jaded");
		}
		else{
			list.add("Fresh");
		}
		if(this.getFitness() >= 8000){
			list.add("Superbly fit");
		}
		else if(this.getFitness() >= 5000){
			list.add("Match fit");
		}
		else if(this.getFitness() >= 3000){
			list.add("Lacking match fitness");
		}
		else if(this.getFitness() >= 1000){
			list.add("Unfit");
		}
		else{
			list.add("Severly unfit");
		}
		list.add(this.getHandling() + "");
		list.add(this.getReflexes() + "");
		list.add(this.getCommandOfArea() + "");
		list.add(this.getDecisions() + "");
		list.add(this.getFinishing() + "");
		list.add(this.getHeading() + "");
		list.add(this.getMarking() + "");
		list.add(this.getPassing() + "");
		list.add(this.getCrossing() + "");
		list.add(this.getTackling() + "");
		list.add(this.getLongShots() + "");
		list.add(this.getDribbling() + "");
		list.add(this.getPenaltyTaking() + "");
		list.add(this.getInfluence() + "");
		list.add(this.getPace() + "");
		list.add(this.getStrength() + "");
		list.add(this.getStamina() + "");
		list.add((this.getCurrentClub() == null) ? "None" : this.getCurrentClub().getName());
		list.add(this.getContractExpiry().get(Calendar.DATE) + "/" + (this.getContractExpiry().get(Calendar.MONTH) + 1) + "/" + this.getContractExpiry().get(Calendar.YEAR));
		list.add((this.getStatus() == null) ? "None" : this.getStatus().toString());
		DecimalFormat myFormatter = new DecimalFormat("000,000");
		list.add("€" + myFormatter.format(this.marketValue));
		list.add("€" + this.getWages());
		if(this.getHappinessAtClub() >= 9000){
			list.add("Loves playing for the club");
		}
		else if(this.getHappinessAtClub() >= 7500){
			list.add("Happy to stay at the club");
		}
		else if(this.getHappinessAtClub() >= 5000){
			list.add("Satisfied with the current conditions");
		}
		else if(this.getHappinessAtClub() >= 2500){
			list.add("Considering his future");
		}
		else{
			list.add("Wants to leave the club");
		}
		String interestedClubs = "";
		if(this.getInterested().size() == 0){
			interestedClubs = "None";
		}
		else{
			Iterator<Club> i = this.getInterested().iterator();
			while(i.hasNext()){
				interestedClubs = interestedClubs + i.next().getName() + " ";
			}
		}
		list.add(interestedClubs);
		return list;
	}

	public void setStatus(int index) {
		switch (index){
		case 0:
			this.status = SquadStatus.INDISPENSABLE;
			break;
		case 1:
			this.status = SquadStatus.IMPORTANT;
			break;
		case 2:
			this.status = SquadStatus.SQUAD;
			break;
		case 3:
			this.status = SquadStatus.TOPYOUNGSTER;
			break;
		case 4:
			this.status = SquadStatus.DECENTYOUNGSTER;
			break;
		case 5:
			this.status = SquadStatus.NOTNEEDED;
			break;
		default:
			System.out.println("Error");
			break;
		}
	}
	
	public String getStatusAsString() {
		return status.toString();
	}

	public SquadStatus getStatus() {
		return status;
	}

	public void setFatigue(int fatigue) {
		if(fatigue <= 10000 && fatigue >= 1)
			this.fatigue = fatigue;
		else if(fatigue > 10000)
			this.fatigue = 10000;
		else if(fatigue < 1)
			this.fatigue = 1;
	}

	public int getFatigue() {
		return fatigue;
	}

	public int getHandling() {
		return handling;
	}

	public void setHandling(int handling) {
		this.handling = handling;
	}

	public int getReflexes() {
		return reflexes;
	}

	public void setReflexes(int reflexes) {
		this.reflexes = reflexes;
	}

	public int getCommandOfArea() {
		return commandOfArea;
	}

	public void setCommandOfArea(int commandOfArea) {
		this.commandOfArea = commandOfArea;
	}

	public int getDecisions() {
		return decisions;
	}

	public void setDecisions(int decisions) {
		this.decisions = decisions;
	}

	public int getFinishing() {
		return finishing;
	}

	public void setFinishing(int finishing) {
		this.finishing = finishing;
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public int getMarking() {
		return marking;
	}

	public void setMarking(int marking) {
		this.marking = marking;
	}

	public int getPassing() {
		return passing;
	}

	public void setPassing(int passing) {
		this.passing = passing;
	}

	public int getCrossing() {
		return crossing;
	}

	public void setCrossing(int crossing) {
		this.crossing = crossing;
	}

	public int getDribbling() {
		return dribbling;
	}

	public void setDribbling(int dribbling) {
		this.dribbling = dribbling;
	}

	public int getTackling() {
		return tackling;
	}

	public void setTackling(int tackling) {
		this.tackling = tackling;
	}

	public int getLongShots() {
		return longShots;
	}

	public void setLongShots(int longShots) {
		this.longShots = longShots;
	}

	public int getPenaltyTaking() {
		return penaltyTaking;
	}

	public void setPenaltyTaking(int penaltyTaking) {
		this.penaltyTaking = penaltyTaking;
	}

	public int getInfluence() {
		return influence;
	}

	public void setInfluence(int influence) {
		this.influence = influence;
	}

	public int getPace() {
		return pace;
	}

	public void setPace(int pace) {
		this.pace = pace;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public int getAmbition() {
		return ambition;
	}

	public void setAmbition(int ambition) {
		this.ambition = ambition;
	}
	
	public void setCurrentAbility(){
		
		double goalkeepercurrentAbility = 0;
		double fullbackcurrentAbility = 0;
		double centrebackcurrentAbility = 0;
		double wingercurrentAbility = 0;
		double centremidfieldercurrentAbility = 0;
		double strikercurrentAbility = 0;
		int positions = 0;
		
		if(this.goalkeepingAbility >= 15){
			goalkeepercurrentAbility = (this.getHandling() + this.getReflexes() + this.getDecisions() + this.getCommandOfArea()) * 2.5;
			positions++;
		}
		if((this.rightFullbackAbility >= 15)||(this.leftFullbackAbility >= 15)){
			fullbackcurrentAbility = ((this.getTackling() * 2) + (this.getMarking() * 2) + ((this.getPace() + this.getStamina() + this.getStrength()) * 1.75) + ((this.getHeading() + this.getPassing() + this.getCrossing()) * 1.5)) * (2.0 / 3);
			positions++;
		}
		if(this.centrebackAbility >= 15){
			centrebackcurrentAbility = ((this.getTackling() * 2.5) + (this.getMarking() * 2.5) + (this.getHeading() * 2) + ((this.getPace() + this.getStamina() + this.getStrength()) * 1.75) + (this.getPassing() * 1.5)) * (8.0 / 11);
			positions++;
		}
		if((this.rightMidfieldAbility >= 15)||(this.leftMidfieldAbility >= 15)){
			wingercurrentAbility = ((this.getCrossing() * 2.5) + (this.getDribbling() * 2.5) + ((this.getPace() + this.getStamina() + this.getStrength()) * 1.75) + (this.getPassing() * 1.75)) * (5.0 / 6);
			positions++;
		}
		if(this.centreMidfieldAbility >= 15){
			centremidfieldercurrentAbility = ((this.getPassing() * 2.5) + (this.getTackling() * 2) + ((this.getPace() + this.getStamina() + this.getStrength()) * 2) + (this.getLongShots() * 2)) * (4.0 / 5);
			positions++;
		}
		if(this.strikerAbility >= 15){
			strikercurrentAbility = ((this.getFinishing() * 3) + ((this.getPace() + this.getStamina() + this.getStrength()) * 2) + (this.getHeading() * 2) + (this.getDribbling() * 2) + (this.getLongShots() * 2)) * (2.0 / 3);
			positions++;
		}
		int currentAbility = (int) ((goalkeepercurrentAbility + fullbackcurrentAbility + centrebackcurrentAbility + wingercurrentAbility + centremidfieldercurrentAbility + strikercurrentAbility) / positions);
		this.setCurrentAbility(currentAbility);
		System.out.println(this.getFirstName() + " " + this.getLastName() + " " + this.getCurrentAbility() + " " + this.getPotentialAbility());
		//System.out.println(strikercurrentAbility);
		//System.out.println(this.getCurrentAbility());
	}

	public void setMatchCondition(double matchCondition) {
		this.matchCondition = matchCondition;
		if(this.getMatchCondition() > 100){
			this.setMatchCondition(100);
		}
		else if(this.getMatchCondition() < 1){
			this.setMatchCondition(1);
		}
	}

	public double getMatchCondition() {
		return matchCondition;
	}
	
	public void transferPlayer(double d, Club club, double wages, Calendar contractExpiry, int status){
		this.getCurrentClub().setBankBalance(this.getCurrentClub().getBankBalance() + d);
		this.getCurrentClub().setTransferBudget(this.getCurrentClub().getTransferBudget() + d);
		List<Player> squad = this.getCurrentClub().getSquad();
		squad.remove(this);
		this.getCurrentClub().setSquad(squad);
		this.setCurrentClub(club);
		this.setWages(wages);
		this.setContractExpiry(contractExpiry);
		this.setStatus(status);
		squad = this.getCurrentClub().getSquad();
		squad.add(this);
		this.getCurrentClub().setSquad(squad);
		this.getCurrentClub().setBankBalance(this.getCurrentClub().getBankBalance() - d);
		this.getCurrentClub().setTransferBudget(this.getCurrentClub().getTransferBudget() - d);
	}

	public void setDaysUnavailable(int daysUnavailable) {
		this.daysUnavailable = daysUnavailable;
	}

	public int getDaysUnavailable() {
		return daysUnavailable;
	}

	public void setLeagueAppearances(int leagueAppearances) {
		this.leagueAppearances = leagueAppearances;
	}

	public int getLeagueAppearances() {
		return leagueAppearances;
	}

	public void setLeagueGoals(int leagueGoals) {
		this.leagueGoals = leagueGoals;
	}

	public int getLeagueGoals() {
		return leagueGoals;
	}

	public void setInterested(List<Club> interested) {
		this.interested = interested;
	}

	public List<Club> getInterested() {
		return interested;
	}
}
