package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.List;

public class League extends Competition implements Serializable {
	
	private int numberOfTeams;
	private List<Club> teams;
	
	public League() {
		super();
	}

	public League(int id, String name, int reputation, int numberOfTeams) {
		super(id, name, reputation);
		this.numberOfTeams = numberOfTeams;
	}

	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public List<Club> getTeams() {
		return teams;
	}

	public void setTeams(List<Club> teams) {
		this.teams = teams;
	}
}
