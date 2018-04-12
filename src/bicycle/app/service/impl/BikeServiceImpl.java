
package bicycle.app.service.impl;

import java.util.List;

import bicycle.app.dao.BikeDao;
import bicycle.app.dao.UserDao;
import bicycle.app.domain.Bike;
import bicycle.app.domain.User;
import bicycle.app.service.BikeService;
import bicycle.utils.PageBean;

public class BikeServiceImpl implements BikeService {
	private BikeDao bikeDao;
	public void setBikeDao(BikeDao bikeDao) {
		this.bikeDao = bikeDao;
	}

	@Override
	public List<Bike> findAllBikes() {
		return bikeDao.findAllBikes();
	}

	@Override
	public Bike findBikeByBluetoothId(String bluetoothId) {
		return bikeDao.findBikeByBluetoothId(bluetoothId);
	}

	@Override
	public PageBean<Bike> findAllBikesPages(Bike bike, int pageNum, int pageSize) {
		
		int totalRecord = bikeDao.getTotalRecord();
		PageBean<Bike> pageBean = new PageBean<Bike>(pageNum, pageSize, totalRecord);
		
		List<Bike> data = this.bikeDao.findAll(pageBean.getStartIndex(), pageBean.getPageSize());
		pageBean.setData(data);
		return pageBean;
	}

	@Override
	public Bike findByBluetoothId(String bluetoothId) {
		
		return bikeDao.findBikeByBluetoothId(bluetoothId);
	}
	
}
