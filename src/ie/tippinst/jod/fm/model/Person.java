package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Person implements Serializable {
	
	private static final long serialVersionUID = -4798728817236898432L;
	private int id;
	private String firstName;
	private String lastName;
	private Nation nationality;
	private double wages;
	private int reputation;
	private Calendar dob;
	private int currentAbility;
	private int potentialAbility;
	private Calendar contractExpiry;
	private Club currentClub;
	private int age;
	
	public Person(){
		super();
	}
	
	public Person(Calendar dob){
		this.dob=dob;
	}
	
	public Club getCurrentClub() {
		return currentClub;
	}

	public void setCurrentClub(Club currentClub) {
		this.currentClub = currentClub;
	}

	protected Person(int id, String firstName, String lastName, Nation nationality, double wages,
			int reputation, Calendar dob, int currentAbility, int potentialAbility,
			Calendar contractExpiry, Club currentClub) {
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
			int reputation, Calendar dob, int currentAbility, int potentialAbility, Club currentClub) {
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
				+ currentAbility + ", currentClub=" + ((currentClub == null)?"No club":currentClub.getName())
				+ ", dob=" + dob.get(Calendar.DAY_OF_MONTH) + "/" + (dob.get(Calendar.MONTH)+1)
				+ "/" + dob.get(Calendar.YEAR) + ", firstName=" + firstName + ", id=" + id
				+ ", lastName=" + lastName + ", nationality=" + nationality.getName()
				+ ", potentialAbility=" + potentialAbility + ", reputation="
				+ reputation + ", wages=" + wages + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Nation getNationality() {
		return nationality;
	}

	public void setNationality(Nation nationality) {
		this.nationality = nationality;
	}

	public double getWages() {
		return wages;
	}

	public void setWages(double wages) {
		this.wages = wages;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public Calendar getDob() {
		return dob;
	}

	public void setDob(Calendar dob) {
		this.dob = dob;
	}

	public int getCurrentAbility() {
		return currentAbility;
	}

	public void setCurrentAbility(int currentAbility) {
		this.currentAbility = currentAbility;
	}

	public int getPotentialAbility() {
		return potentialAbility;
	}

	public void setPotentialAbility(int potentialAbility) {
		this.potentialAbility = potentialAbility;
	}

	public Calendar getContractExpiry() {
		return contractExpiry;
	}

	public void setContractExpiry(Calendar contractExpiry) {
		this.contractExpiry = contractExpiry;
	}

	public void setAge(Calendar c) {
		int age = c.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
	    if((dob.get(Calendar.MONTH) > c.get(Calendar.MONTH)) || (dob.get(Calendar.MONTH) == c.get(Calendar.MONTH)&& dob.get(Calendar.DAY_OF_MONTH) > c.get(Calendar.DAY_OF_MONTH)))
	       age--;
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	

}
