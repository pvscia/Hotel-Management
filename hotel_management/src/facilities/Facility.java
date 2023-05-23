package facilities;

public class Facility {
	private String id, name, type,capacity, status, location;	private int cost,custCount;
	
	public Facility(String id, String name, String type, String capacity, String status, String location, int cost,
			int custCount) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.capacity = capacity;
		this.status = status;
		this.location = location;
		this.cost = cost;
		this.custCount = custCount;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCapacity() {
		return capacity;
	}
	public void setCapacity(String capacity) {
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
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCustCount() {
		return custCount;
	}
	public void setCustCount(int custCount) {
		this.custCount = custCount;
	}
	
	
	
}
