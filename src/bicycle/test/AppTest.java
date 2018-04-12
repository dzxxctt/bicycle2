package bicycle.test;

import org.junit.Before;
import org.junit.Test;

import bicycle.app.dao.impl.UserDaoImpl;
import bicycle.app.domain.User;
import bicycle.app.service.UserService;
import bicycle.app.service.impl.UserServiceImpl;
import bicycle.utils.MD5Utils;

public class AppTest {
	@Test
	public void test1(){
		UserDaoImpl userDao = new UserDaoImpl();
		User user = new User();
		user.setTelphone("120120120120");
		user.setPassword("120");
		userDao.save(user);
		
	}
	@Test
	public void test2(){
		System.out.println(MD5Utils.md5("111"));
	}
	@Before
	public void testaa(){
		UserService userService = new UserServiceImpl();
	}
	@Test
	public void testStart(){
		
	}
	@Test
	public void testEnd(){
		
	}
}
