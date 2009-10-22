package ie.tippinst.jod.fm.obj;

import java.util.Date;

abstract class Person {
	
	private int id;
	private String firstName;
	private String lastName;
	private Nation nationality;
	private double wages;
	private int reputation;
	private Date dob;
	private int currentAbility;
	private int potentialAbility;
	private Date contractExpiry;
	private Club currentClub;
	
	protected Person(String firstName, String lastName, Nation nationality, double wages,
			int reputation, Date dob, int currentAbility, int potentialAbility,
			Date contractExpiry, Club currentClub) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.wages = wages;
		this.reputation = reputation;
		this.dob = dob;
		this.currentAbility = currentAbility;
		this.potentialAbility = potentialAbility;
		this.contractExpiry = contractExpiry;
		this.currentClub = currentClub;
	}

}
