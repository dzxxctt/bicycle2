package bicycle.app.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;

import bicycle.app.dao.BikeDao;
import bicycle.app.dao.UserDao;
import bicycle.app.domain.Bike;
import bicycle.app.domain.User;
import bicycle.app.service.UserService;
import bicycle.utils.BaiDuUtil;
import bicycle.utils.MD5Utils;
import bicycle.utils.PageBean;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private BikeDao bikeDao;

	public void setBikeDao(BikeDao bikeDao) {
		this.bikeDao = bikeDao;
	}

	@Override
	public User findUserByTelphone(String telphone) {
		return this.userDao.findUserByTel(telphone);
	}

	@Override
	public void addUser(User user) {
		this.userDao.save(user);
	}

	@Override
	public User login(User user) {
		user.setPassword(MD5Utils.md5(user.getPassword()));
		return this.userDao.findUser(user.getTelphone(), user.getPassword());
	}

	@Override
	public int updateStartCharge(User user) throws IOException {
		int flag = 0;
		if (user != null && user.getBluetoothId() != null
				&& user.getTelphone() != null) {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String startTime = sd.format(new Date());
			User findUser = userDao.findUserByTel(user.getTelphone());
			findUser.setStartTime(startTime);
			findUser.setUserStatus(1);
			findUser.setCost(0);
			findUser.setEndTime("");
			findUser.setBluetoothId(user.getBluetoothId());

			Bike findBike = bikeDao
					.findBikeByBluetoothId(user.getBluetoothId());
			if (findBike != null) {
				if (findBike.getBikeStatus() == 0) {
					findBike.setBikeStatus(1);
					findBike.setTelphone(user.getTelphone());
					// this.bikeDao.updateStartBike(findBike);
				} else {
					return flag;
				}
			} else {
				Bike bike = new Bike();
				bike.setTelphone(user.getTelphone());
				// 正在使用
				bike.setBikeStatus(1);
				bike.setBluetoothId(user.getBluetoothId());
				this.bikeDao.updateStartBike(bike);
			}
			// this.userDao.updateStartCharge(findUser);
			flag = 1;
		}
		return flag;
	}

	@Override
	public int updateEndCharge(User user, String longitude, String latitude) {
		if (user != null && user.getTelphone() != null
				&& StringUtils.isNotBlank(latitude)
				&& StringUtils.isNotBlank(longitude)) {
			// 获取地址
			String addr = BaiDuUtil.getAddr(latitude, longitude);
			// 计费
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String end = sdf.format(new Date());
			User findUser = userDao.findUserByTel(user.getTelphone());
			Bike findBike = bikeDao
					.findBikeByBluetoothId(user.getBluetoothId());
			String findStartTime = findUser.getStartTime();
			long endTime;
			long startTime;
			int minutes = 0;
			int cost = 0;
			if (findUser != null && findBike != null) {
				
				try {
					endTime = sdf.parse(end).getTime();
					startTime = sdf.parse(findStartTime).getTime();
					// 分钟
					minutes = (int) Math.ceil((endTime - startTime)
							/ (1000 * 60));
					cost = minutes * 1 + 1;
					if (cost != 0) {
						findUser.setEndTime(end);
						findUser.setCost(cost);
						findUser.setUserStatus(0);
						// userDao.updateEndCharge(findUser);

						findBike.setBikeStatus(0);
						findBike.setLongitude(longitude);
						findBike.setLatitude(latitude);
						findBike.setAddress(addr);
						findBike.setTelphone(user.getTelphone());
						// bikeDao.updateEndBike(findBike);

						return cost;
					}
				} catch (ParseException e) {
					new RuntimeException("转化失败");
				}
			}
		}
		return -1;
	}

	@Override
	public List<User> findAllUsers() {
		return this.userDao.findAll();
	}

	@Override
	public int findUserStatus(String telphone) {
		return this.userDao.findUserStatus(telphone);
	}

	@Override
	public void updatePassword(User user) {
		userDao.update(user);
	}

	@Override
	public PageBean<User> findAllUsersPages(User user, int pageNum, int pageSize) {

		int totalRecord = userDao.getTotalRecord();
		PageBean<User> pageBean = new PageBean<User>(pageNum, pageSize,
				totalRecord);

		List<User> data = this.userDao.findAll(pageBean.getStartIndex(),
				pageBean.getPageSize());
		pageBean.setData(data);
		return pageBean;
	}

}
