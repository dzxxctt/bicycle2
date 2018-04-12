package bicycle.app.dao;

import java.util.List;

import bicycle.app.domain.User;
import bicycle.utils.PageBean;

public interface UserDao {
	/**
	 * 通过手机号查询是否存在
	 * @param telphone
	 * @return
	 */
	public User findUserByTel(String telphone);
	/**
	 * 通过手机号和密码查询
	 * @param telphone
	 * @param password
	 * @return
	 */
	public User findUser(String telphone, String password);
	/**
	 * 添加新用户
	 * @param user
	 */
	public void save(User user);
	/**
	 * 开始计费
	 * @param user
	 * @return
	 */
	public void updateStartCharge(User user);
	/**
	 * 结束计费
	 * @param user
	 */
	public void updateEndCharge(User user);
	/**
	 * 查询所有
	 * @return 所有信息
	 */
	public List<User> findAll();
	/**获取用户当前用车状态
	 * @param telphone
	 * @return
	 */
	public int findUserStatus(String telphone);
	public void update(User uesr);
	
	/**获得总记录数
	 * @param condition
	 * @return
	 */
	public int getTotalRecord();
	public List<User> findAll(int startIndex, int pageSize);
}
