package bicycle.website.web.action;
import java.io.ByteArrayInputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;

import bicycle.app.domain.Bike;
import bicycle.app.domain.User;
import bicycle.app.service.BikeService;
import bicycle.app.service.UserService;
import bicycle.base.BaseAction;
import bicycle.utils.MD5Utils;
import bicycle.utils.PageBean;
import bicycle.utils.SecurityCode;
import bicycle.utils.SecurityImage;

import com.opensymphony.xwork2.ActionContext;

public class WebsiteAction extends BaseAction<User> {
	private static final long serialVersionUID = 5633780176793520460L;
	private ByteArrayInputStream imageStream;

	public ByteArrayInputStream getImageStream() {
		return imageStream;
	}

	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BikeService bikeService;

	public void setBikeService(BikeService bikeService) {
		this.bikeService = bikeService;
	}
	//验证码
	private String code;
	public void setCode(String code) {
		this.code = code;
	}

	// 3分页数据
	private static int pageNum = 1;

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageNum() {
		return pageNum;
	}

	private int pageSize = 8;// 默认为8

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	private String oldPwd1;
	private String oldPwd2;

	public String getOldPwd1() {
		return oldPwd1;
	}

	public void setOldPwd1(String oldPwd1) {
		this.oldPwd1 = oldPwd1;
	}

	public String getOldPwd2() {
		return oldPwd2;
	}

	public void setOldPwd2(String oldPwd2) {
		this.oldPwd2 = oldPwd2;
	}

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * 登录
	 * 
	 * @return
	 */
	public String login() {
		String sercurityCode = (String) ActionContext.getContext().getSession().get("securityCode");
		//System.out.println(code);
		//System.out.println(sercurityCode);
		User findUser = userService.login(this.getModel());
		if (findUser != null) {
			if (findUser.getType() == 0) {
				this.addActionError("亲~您不是管理员用户哟~~");
				return "login";
			} else {
				if (code.equals(sercurityCode)) {
					// 登录成功
					putSession("loginUser", findUser);
					return "home";
				}else {
					this.addActionError("验证码都输不对！还能干点啥！！");
					return "login";
				}
			}
		} else {
			this.addActionError("亲 用户名与密码不匹配滴");
		}
		return "login";
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	public String logout() {
		ActionContext.getContext().getSession().remove("loginUser");
		return "login";
	}

	/**
	 * 查询所有app用户
	 * 
	 * @return
	 */
	public String findAllUsers() {
		/*
		 * List<User> findAllUsers = userService.findAllUsers(); put("allUsers",
		 * findAllUsers);
		 */

		PageBean<User> pageBeanUsers = userService.findAllUsersPages(
				getModel(), pageNum, pageSize);
		ActionContext.getContext().put("pageBeanUsers", pageBeanUsers);
		List<User> data = pageBeanUsers.getData();
		/*for (User user : data) {
			System.out.println(user.getTelphone());
		}*/
		return "findAllUsers";
	}

	public String findByTelphone() {
		if (StringUtils.isNotBlank(keyword)) {
			User oneUser = userService.findUserByTelphone(keyword);
			if (oneUser != null) {
				System.out.println(oneUser);
				ActionContext.getContext().getValueStack().push(oneUser);
			}else {
				this.addActionError("查不到此用户");
			}
		}else {
			this.addActionError("请输入用户电话");
		}
		return "findByTelphone";
	}

	/**
	 * 更改密码
	 * 
	 * @return
	 */
	public String updatePassword() {
		User findUser = userService.findUserByTelphone(this.getModel()
				.getTelphone());
		if (oldPwd1.equals(oldPwd2) && findUser != null
				&& findUser.getPassword().equals(MD5Utils.md5(getModel().getPassword()))) {
			findUser.setPassword(MD5Utils.md5(oldPwd1));
			userService.updatePassword(findUser);
		} else {
			this.addActionError("更改密码失败");
		}
		ActionContext.getContext().getSession().remove("loginUser");
		return "login";
	}

	/**
	 * 更改密码页面
	 * 
	 * @return
	 */
	public String updatePasswordUI() {
		User user = (User) ActionContext.getContext().getSession().get("loginUser");
		if (user != null) {
			push(user);
		}
		return "updatePasswordUI";
	}

	public String valideCode() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String securityCode = SecurityCode.getSecurityCode();
			imageStream = SecurityImage.getImageAsInputStream(securityCode);
			// 放入session中
			// session.put("securityCode", securityCode);
			//System.out.println("securityCode = " + securityCode);
			putSession("securityCode", securityCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "valideCode";
	}
}
