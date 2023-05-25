package rooms;

public abstract class Room {
	private String bedType, roomType, roomView,avaibility;
	private int roomSize, roomCapacity, roomNumber,roomPrice;
	private boolean do_not_disturb,wake_up_call,emergency;
	
	

	public Room(String bedType, String roomType, String roomView, String avaibility, int roomSize, int roomCapacity,
			int roomNumber, int roomPrice, boolean do_not_disturb, boolean wake_up_call, boolean emergency) {
		super();
		this.bedType = bedType;
		this.roomType = roomType;
		this.roomView = roomView;
		this.avaibility = avaibility;
		this.roomSize = roomSize;
		this.roomCapacity = roomCapacity;
		this.roomNumber = roomNumber;
		this.roomPrice = roomPrice;
		this.do_not_disturb = do_not_disturb;
		this.wake_up_call = wake_up_call;
		this.emergency = emergency;
	}


	
	public boolean isEmergency() {
		return emergency;
	}


	public void setEmergency(boolean emergency) {
		this.emergency = emergency;
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


	public String getAvaibility() {
		return avaibility;
	}


	public void setAvaibility(String avaibility) {
		this.avaibility = avaibility;
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


	public int getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}


	public int getRoomPrice() {
		return roomPrice;
	}


	public void setRoomPrice(int roomPrice) {
		this.roomPrice = roomPrice;
	}


	public boolean isDo_not_disturb() {
		return do_not_disturb;
	}


	public void setDo_not_disturb(boolean do_not_disturb) {
		this.do_not_disturb = do_not_disturb;
	}


	public boolean isWake_up_call() {
		return wake_up_call;
	}


	public void setWake_up_call(boolean wake_up_call) {
		this.wake_up_call = wake_up_call;
	}
	
	
	
	
}
