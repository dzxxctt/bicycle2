package bicycle.app.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import bicycle.app.dao.UserDao;
import bicycle.app.domain.User;
import bicycle.utils.PageBean;
import bicycle.utils.PageHibernateCallback;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	@Override
	public User findUserByTel(String telphone) {
		List<User> findUser = this.getHibernateTemplate().find(
				"from User where telphone=?", telphone);
		if (findUser.isEmpty()) {
			return null;
		}
		return findUser.get(0);
		/*
		 * Session session = this.getSession(); session.beginTransaction();
		 * String hql = "from User where telphone = ?"; List<User> users =
		 * session.createQuery(hql).setParameter(0, telphone).list();
		 * session.getTransaction().commit(); session.close(); if (users != null
		 * && users.size() > 0) { return users.get(0); } return null;
		 */

	}

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User findUser(String telphone, String password) {
		List<User> findUser = this.getHibernateTemplate()
				.find("from User where telphone=? and password=?", telphone,
						password);
		if (!findUser.isEmpty()) {
			return findUser.get(0);
		}
		return null;
	}

	@Override
	public void updateStartCharge(User user) {
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public void updateEndCharge(User user) {
		this.getHibernateTemplate().saveOrUpdate(user);
	}

	@Override
	public List<User> findAll() {
		return this.getHibernateTemplate().find("from User where type=0");
	}

	@Override
	public int findUserStatus(String telphone) {
		List<User> findUser = this.getHibernateTemplate().find(
				"from User where telphone=?", telphone);
		if (findUser.isEmpty()) {
			return 0;
		}
		return findUser.get(0).getUserStatus();
	}

	@Override
	public void update(User uesr) {
		this.getHibernateTemplate().update(uesr);
	}

	public int getTotalRecord() {
		String hql = "select count(*) from User";
		List<Long> list = this.getHibernateTemplate().find(hql);
		System.out.println(list.get(0).intValue());
		return list.get(0).intValue();
	}
	public List<User> findAll(int startIndex, int pageSize) {
		String hql = "from User";
		return this.getHibernateTemplate().execute(new PageHibernateCallback<User>().setHql(hql).setPageSize(pageSize).setStartIndex(startIndex));
		
	}
}
