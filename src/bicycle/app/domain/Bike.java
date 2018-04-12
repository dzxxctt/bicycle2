package bicycle.app.domain;

public class Bike {
	@Override
	public String toString() {
		return "Bike [bikeId=" + bikeId + ", bluetoothId=" + bluetoothId
				+ ", bikeStatus=" + bikeStatus + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", telphone=" + telphone + "]";
	}
	private String bikeId;
	private String bluetoothId;
	
	private int bikeStatus;
	
	private String longitude;
	private String latitude;
	private String telphone;
	private String address;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getBluetoothId() {
		return bluetoothId;
	}
	public void setBluetoothId(String bluetoothId) {
		this.bluetoothId = bluetoothId;
	}
	
	public int getBikeStatus() {
		return bikeStatus;
	}
	public void setBikeStatus(int bikeStatus) {
		this.bikeStatus = bikeStatus;
	}
	public String getBikeId() {
		return bikeId;
	}
	public void setBikeId(String bikeId) {
		this.bikeId = bikeId;
	}
	
	
}
