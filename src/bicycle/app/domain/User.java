package bicycle.app.domain;

public class User {
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", telphone=" + telphone
				+ ", password=" + password + ", type=" + type + ", startTime="
				+ startTime + ", endTime=" + endTime + ", cost=" + cost
				+ ", bluetoothId=" + bluetoothId + ", userStatus=" + userStatus
				+ "]";
	}
	private String userId;
	private String telphone;
	private String password;
	//0:用户;	1:管理员
	private Integer type;
	
	private String startTime;
	private String endTime;
	private int cost;
	//自行车信息
	private String bluetoothId;
	private int userStatus;
	
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getBluetoothId() {
		return bluetoothId;
	}
	public void setBluetoothId(String bluetoothId) {
		this.bluetoothId = bluetoothId;
	}
	
	public int getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
