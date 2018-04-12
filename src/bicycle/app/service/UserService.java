package bicycle.app.service;

import java.io.IOException;
import java.util.List;

import bicycle.app.domain.User;
import bicycle.utils.PageBean;

public interface UserService {
	/**
	 * 登录
	 * 
	 * @param user
	 * @return
	 */
	public User login(User user);

	/**
	 * 通过手机号查询
	 * 
	 * @param telphone
	 * @return
	 */
	public User findUserByTelphone(String telphone);

	/**
	 * 添加员工
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * 开始计费
	 * 
	 * @param model
	 * @throws Exception
	 */
	public int updateStartCharge(User user) throws IOException;

	/**
	 * 结束计费
	 * 
	 * @param model
	 */
	public int updateEndCharge(User model, String longitude, String latitude);

	/**
	 * 查询所有
	 */
	public List<User> findAllUsers();

	public int findUserStatus(String telphone);

	public void updatePassword(User user);

	/**
	 * 分页查询
	 * 
	 * @param pageBean
	 * @return
	 */
	public PageBean<User> findAllUsersPages(User user, int pageNum, int pageSize);
}
