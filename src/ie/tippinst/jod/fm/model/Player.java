package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Player extends Person implements Serializable {
	
	private double marketValue;
	private double saleValue;
	private int intCaps;
	private int intGoals;
	private int leftFootAbility;
	private int rightFootAbility;
	private int fitness;
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
	private String position;
	
	public Player(){
		super();
	}
	
	public Player(int id, String firstName, String lastName, Nation nationality,
			double wages, int reputation, Calendar dob, int currentAbility,
			int potentialAbility, Date contractExpiry, Club currentClub,
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

	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}

	public double getSaleValue() {
		return saleValue;
	}

	public void setSaleValue(double saleValue) {
		this.saleValue = saleValue;
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
		this.fitness = fitness;
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
	
	public List<String> getPlayerProfileInfo(){
		List<String> list = new ArrayList<String>();
		list.add(this.getFirstName() + " " + this.getLastName());
		list.add(this.getDob() + "");
		list.add(this.getNationality().getName());
		list.add(this.getPosition());
		list.add(this.getCurrentClub().getName());
		list.add("€" + this.getWages());
		return list;
	}

}
