package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NonPlayer extends Person implements Serializable {
	
	private static final long serialVersionUID = -3776885507558100224L;
	private int chairmanRole;
	private int managerRole;
	private int assistantManagerRole;
	private int coachRole;
	private int physioRole;
	private int scoutRole;
	private String role;
	
	public NonPlayer(){
		super();
	}
	
	public NonPlayer(int id, String firstName, String lastName, Nation nationality,
			double wages, int reputation, Calendar dob, int currentAbility,
			int potentialAbility, Calendar contractExpiry, Club currentClub,
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
			Calendar dob, int currentAbility, int potentialAbility, int chairmanRole, int managerRole,
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

	public void setRole() {
		this.role = "";
		if(this.getChairmanRole() == 20){
			this.role = "Chairman";
		}
		else if(this.getManagerRole() == 20){
			this.role = "Manager";
		}
		else if(this.getAssistantManagerRole() == 20){
			this.role = "Assistant Manager";
		}
		else if(this.getCoachRole() == 20){
			this.role = "Coach";
		}
		else if(this.getPhysioRole() == 20){
			this.role = "Physio";
		}
		else if(this.getScoutRole() == 20){
			this.role = "Scout";
		}
	}

	public String getRole() {
		return role;
	}
	
	public List<String> getStaffProfileInfo(){
		List<String> list = new ArrayList<String>();
		list.add(this.getFirstName() + " " + this.getLastName());
		list.add(this.getDob().get(Calendar.DATE) + "/" + (this.getDob().get(Calendar.MONTH) + 1) + "/" + this.getDob().get(Calendar.YEAR));
		list.add(this.getNationality().getName());
		list.add(this.getRole());
		list.add(this.getCurrentClub().getName());
		list.add("€" + this.getWages());
		return list;
	}

}
