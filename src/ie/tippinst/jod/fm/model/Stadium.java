package ie.tippinst.jod.fm.model;

public class Stadium {
	
	private int id;
	private String name;
	private int capacity;
	
	public Stadium(int id, String name, int capacity) {
		super();
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}
	
	
}
