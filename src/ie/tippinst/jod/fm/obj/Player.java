package ie.tippinst.jod.fm.obj;

import java.util.Date;

public class Player extends Person {
	
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
	
	public Player(int id, String firstName, String lastName, Nation nationality,
			double wages, int reputation, Date dob, int currentAbility,
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
	
	

}
