package bicycle.app.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import bicycle.app.domain.User;
import bicycle.app.service.UserService;
import bicycle.base.BaseAction;
import bicycle.utils.MD5Utils;

/**
 * @author Administrator
 *
 */
public class AppAction extends BaseAction<User> {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String longitude;
	private String latitude;

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

	/**
	 * 手机用户登录
	 * 
	 * @return
	 * @throws IOException
	 */
	public String login() throws IOException {
		ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();

		System.out.println(this.getModel().getTelphone());
		System.out.println(this.getModel().getPassword());

		User findUser = userService.login(this.getModel());
		if (findUser != null) {
			// 返回数据给app
			putSession("loginUser", this.getModel());
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
		response.getWriter().close();
		// push(this.getModel());

		return NONE;
	}

	/**
	 * 手机用户注册
	 * 
	 * @return
	 * @throws IOException
	 */
	public String register() throws IOException {
		ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();

		System.out.println(this.getModel().getTelphone());
		System.out.println(this.getModel().getPassword());
		String password = this.getModel().getPassword();
		this.getModel().setPassword(MD5Utils.md5(password));
		User findUser = userService.findUserByTelphone(this.getModel()
				.getTelphone());

		if (findUser != null) {
			// 数据库已注册
			response.getWriter().write("0");
		} else {
			// 添加到数据库 注册成功
			userService.addUser(this.getModel());
			response.getWriter().write("1");
		}

		response.getWriter().close();

		return NONE;
	}

	/**
	 * 开始计费
	 * 
	 * @return
	 * @throws IOException
	 */
	public String startCharge() throws IOException {
		ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		// 返回至手机 开始计费
		// model里含有bluetoothId
		
		int flag = userService.updateStartCharge(this.getModel());
		if (flag == 1) {
			response.getWriter().write("1");
		}else {
			response.getWriter().write("0");
		}
		response.getWriter().close();

		return NONE;
	}

	/**
	 * 获得当前用户是否用车
	 * 
	 * @return 1：正在使用 0：否
	 * @throws IOException
	 */
	public String getUserStatus() throws IOException{
		ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();

		int currentUserStatus = userService.findUserStatus(this.getModel()
				.getTelphone());
		if (currentUserStatus == 1) {
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
		response.getWriter().close();
		return NONE;
	}

	/**
	 * 结束计费 返回需交费用给app
	 * 
	 * @return
	 * @throws IOException
	 */
	public String endCharge() throws IOException {
		ServletActionContext.getRequest().getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		int cost = -1;
		System.out.println(latitude + "111111111111");
		if (StringUtils.isNotBlank(latitude) && StringUtils.isNotBlank(longitude) && StringUtils.isNotBlank(this.getModel().getTelphone())) {
			cost = userService.updateEndCharge(this.getModel(), longitude,latitude);
		}
		// 返回费用给App -1出错 		0 以上为正确
		response.getWriter().write(String.valueOf(cost));
			
		response.getWriter().close();
		return NONE;
	}
}
