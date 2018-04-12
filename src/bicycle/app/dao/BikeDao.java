package bicycle.app.dao;

import java.util.List;

import bicycle.app.domain.Bike;
import bicycle.app.domain.User;

public interface BikeDao {
	public void updateEndBike(Bike bike);
	public List<Bike> findAllBikes();
	public void updateStartBike(Bike bike);
	public Bike findBikeByBluetoothId(String bluetoothId);
	public int getTotalRecord();
	public List<Bike> findAll(int startIndex, int pageSize);
	public Bike findBike(String telphone, String bluetoothId);
}
