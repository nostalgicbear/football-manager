package ie.tippinst.jod.fm.model;

import java.util.Date;

public class NonPlayer extends Person {
	
	private int chairmanRole;
	private int managerRole;
	private int assistantManagerRole;
	private int coachRole;
	private int physioRole;
	private int scoutRole;
	
	public NonPlayer(int id, String firstName, String lastName, Nation nationality,
			double wages, int reputation, Date dob, int currentAbility,
			int potentialAbility, Date contractExpiry, Club currentClub,
			int chairmanRole, int managerRole, int assistantManagerRole,
			int coachRole, int physioRole, int scoutRole) {
		super(id, firstName, lastName, nationality, wages, reputation, dob,
				currentAbility, potentialAbility, contractExpiry, currentClub);
		this.chairmanRole = chairmanRole;
		this.managerRole = managerRole;
		this.assistantManagerRole = assistantManagerRole;
		this.coachRole = coachRole;
		this.physioRole = physioRole;
		this.scoutRole = scoutRole;
	}

	public NonPlayer(String firstName, String lastName, Nation nationality, int reputation,
			Date dob, int currentAbility, int potentialAbility, int chairmanRole, int managerRole,
			int assistantManagerRole, int coachRole, int physioRole, int scoutRole, Club currentClub) {
		super(firstName, lastName, nationality, reputation, dob,
				currentAbility, potentialAbility, currentClub);
		this.chairmanRole = chairmanRole;
		this.managerRole = managerRole;
		this.assistantManagerRole = assistantManagerRole;
		this.coachRole = coachRole;
		this.physioRole = physioRole;
		this.scoutRole = scoutRole;
	}

	public int getChairmanRole() {
		return chairmanRole;
	}

	public void setChairmanRole(int chairmanRole) {
		this.chairmanRole = chairmanRole;
	}

	public int getManagerRole() {
		return managerRole;
	}

	public void setManagerRole(int managerRole) {
		this.managerRole = managerRole;
	}

	public int getAssistantManagerRole() {
		return assistantManagerRole;
	}

	public void setAssistantManagerRole(int assistantManagerRole) {
		this.assistantManagerRole = assistantManagerRole;
	}

	public int getCoachRole() {
		return coachRole;
	}

	public void setCoachRole(int coachRole) {
		this.coachRole = coachRole;
	}

	public int getPhysioRole() {
		return physioRole;
	}

	public void setPhysioRole(int physioRole) {
		this.physioRole = physioRole;
	}

	public int getScoutRole() {
		return scoutRole;
	}

	public void setScoutRole(int scoutRole) {
		this.scoutRole = scoutRole;
	}
	
	

}
