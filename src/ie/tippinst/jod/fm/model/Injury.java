package ie.tippinst.jod.fm.model;

public class Injury {
	
	private int id;
	private String name;
	private int daysOut;
	
	public Injury(int id, String name, int daysOut) {
		super();
		this.id = id;
		this.name = name;
		this.daysOut = daysOut;
	}

	public int getId() {
		return id;
	}
	
	

}
