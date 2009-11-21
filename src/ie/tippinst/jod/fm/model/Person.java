package ie.tippinst.jod.fm.model;

import java.util.Date;

public abstract class Person {
	
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
	
	public Club getCurrentClub() {
		return currentClub;
	}

	public void setCurrentClub(Club currentClub) {
		this.currentClub = currentClub;
	}

	protected Person(int id, String firstName, String lastName, Nation nationality, double wages,
			int reputation, Date dob, int currentAbility, int potentialAbility,
			Date contractExpiry, Club currentClub) {
		super();
		this.id = id;
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

	public Person(String firstName, String lastName, Nation nationality,
			int reputation, Date dob, int currentAbility, int potentialAbility, Club currentClub) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.reputation = reputation;
		this.dob = dob;
		this.currentAbility = currentAbility;
		this.potentialAbility = potentialAbility;
		this.currentClub = currentClub;
	}

	@Override
	public String toString() {
		return "Person [contractExpiry=" + contractExpiry + ", currentAbility="
				+ currentAbility + ", currentClub=" + currentClub.getName() + ", dob="
				+ dob + ", firstName=" + firstName + ", id=" + id
				+ ", lastName=" + lastName/* + ", nationality=" + nationality.getName()*/
				+ ", potentialAbility=" + potentialAbility + ", reputation="
				+ reputation + ", wages=" + wages + "]";
	}

}
