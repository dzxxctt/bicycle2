package bicycle.base;

import java.lang.reflect.ParameterizedType;

import bicycle.app.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	private T t;
	@Override
	public T getModel() {
		
		return t;
	}
	public BaseAction(){
		try {
			//1获得T运行时Class
			ParameterizedType paramType = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) paramType.getActualTypeArguments()[0];
			//2反射创建实例
			t = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	public void push(Object o){
		ActionContext.getContext().getValueStack().push(o);
	}
	public void set(String key, Object o){
		ActionContext.getContext().getValueStack().set(key, o);
	}
	public void put(String key, Object value){
		ActionContext.getContext().put(key, value);
	}
	public void putSession(String key, Object value){
		ActionContext.getContext().getSession().put(key, value);
	}
	public void putApplication(String key, Object value){
		ActionContext.getContext().getApplication().put(key, value);
	}
	
	
}
