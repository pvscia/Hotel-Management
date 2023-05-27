package facilities;

public class Facility {
	private String name, status, location;	private int custCount,capacity;
	public Facility(String name, int capacity, String status, String location, int custCount) {
		super();
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.location = location;
		this.custCount = custCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCustCount() {
		return custCount;
	}
	public void setCustCount(int custCount) {
		this.custCount = custCount;
	}
	
	
	
	
	
}
