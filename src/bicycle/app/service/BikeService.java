package bicycle.app.service;

import java.util.List;

import bicycle.app.domain.Bike;
import bicycle.app.domain.User;
import bicycle.utils.PageBean;

public interface BikeService {
	public List<Bike> findAllBikes();
	public Bike findBikeByBluetoothId(String bluetoothId);
	public PageBean<Bike> findAllBikesPages(Bike bike,int pageNum, int pageSize);
	public Bike findByBluetoothId(String bluetoothId);
}
