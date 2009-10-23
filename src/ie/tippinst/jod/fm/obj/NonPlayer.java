package ie.tippinst.jod.fm.obj;

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
	
	

}
