package bicycle.app.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bicycle.app.dao.BikeDao;
import bicycle.app.domain.Bike;
import bicycle.utils.PageHibernateCallback;

public class BikeDaoImpl extends HibernateDaoSupport implements BikeDao {

	@Override
	public void updateEndBike(Bike bike){
			this.getHibernateTemplate().saveOrUpdate(bike);
	}

	@Override
	public List<Bike> findAllBikes() {
		return this.getHibernateTemplate().find("from Bike");
	}

	@Override
	public void updateStartBike(Bike bike) {
		//Bike findBike = this.getHibernateTemplate().get(Bike.class, bike.getBikeId());
		this.getHibernateTemplate().saveOrUpdate(bike);
	}

	@Override
	public Bike findBikeByBluetoothId(String bluetoothId) {
		List<Bike> findBike = this.getHibernateTemplate().find("from Bike where bluetoothId=?", bluetoothId);
		if (findBike.isEmpty()) {
			return null;
		}
		return findBike.get(0);
	}
	public int getTotalRecord() {
		String hql = "select count(*) from Bike";
		List<Long> list = this.getHibernateTemplate().find(hql);
		System.out.println(list.get(0).intValue());
		return list.get(0).intValue();
	}
	public List<Bike> findAll(int startIndex, int pageSize) {
		String hql = "from Bike";
		return this.getHibernateTemplate().execute(new PageHibernateCallback<Bike>().setHql(hql).setPageSize(pageSize).setStartIndex(startIndex));
		
	}

	@Override
	public Bike findBike(String telphone, String bluetoothId) {
		List<Bike> list = this.getHibernateTemplate().find("from Bike where telphone = ? and bluetoothId = ?",telphone, bluetoothId);
		if (list != null) {
			return list.get(0);
		}
		return null;
	}
}
