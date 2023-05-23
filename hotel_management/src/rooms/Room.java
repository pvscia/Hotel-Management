package rooms;

public abstract class Room {
	private String bedType, roomType, roomView,avaibility;
	private int roomSize, roomCapacity, roomNumber,roomPrice;
	
	public Room(String bedType, String roomType, String roomView, String avaibility, int roomSize, int roomCapacity,
			int roomNumber, int roomPrice) {
		super();
		this.bedType = bedType;
		this.roomType = roomType;
		this.roomView = roomView;
		this.avaibility = avaibility;
		this.roomSize = roomSize;
		this.roomCapacity = roomCapacity;
		this.roomNumber = roomNumber;
		this.roomPrice = roomPrice;
	}
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
		this.bedType = bedType;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getRoomView() {
		return roomView;
	}
	public void setRoomView(String roomView) {
		this.roomView = roomView;
	}
	public int getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
	public int getRoomCapacity() {
		return roomCapacity;
	}
	public void setRoomCapacity(int roomCapacity) {
		this.roomCapacity = roomCapacity;
	}
	public int getRoomCount() {
		return roomNumber;
	}
	public void setRoomCount(int roomCount) {
		this.roomNumber = roomCount;
	}
	public int getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getAvaibility() {
		return avaibility;
	}
	public void setAvaibility(String avaibility) {
		this.avaibility = avaibility;
	}

	
	
}
