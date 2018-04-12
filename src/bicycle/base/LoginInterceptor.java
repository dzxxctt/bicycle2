package bicycle.base;

import org.apache.struts2.ServletActionContext;

import bicycle.app.domain.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if (user == null) {
			//未登录，去登录
			return "login";
		}
		return invocation.invoke();
	}

}
