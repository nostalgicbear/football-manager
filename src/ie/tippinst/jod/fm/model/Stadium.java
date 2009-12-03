package ie.tippinst.jod.fm.model;

import java.io.Serializable;

public class Stadium implements Serializable {
	
	private static final long serialVersionUID = -6411318498229928266L;
	private int id;
	private String name;
	private int capacity;
	
	public Stadium(){
		super();
	}
	
	public Stadium(int id, String name, int capacity) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
	}

	public int getId() {
		return id;
	}
	
	public String getName(){
		return this.name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "Stadium [capacity=" + capacity + ", id=" + id + ", name="
				+ name + "]";
	}
	
}
