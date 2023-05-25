package finance;

public class AmenitiesRequest {
	private String id;
	private int roomNumber,qty;
	
	
	public AmenitiesRequest(String id, int roomNumber, int qty) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.qty = qty;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
}
