package bicycle.website.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.test.JSONAssert;

import com.opensymphony.xwork2.ActionContext;

import bicycle.app.domain.Bike;
import bicycle.app.service.BikeService;
import bicycle.base.BaseAction;
import bicycle.utils.PageBean;

public class BikeAction extends BaseAction<Bike> {
	public BikeService bikeService;
	public void setBikeService(BikeService bikeService) {
		this.bikeService = bikeService;
	}
	// 分页数据
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
	private String keywords;
	
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * 查询所有自行车
	 * 
	 * @return
	 */
	public String findAllBikes() {
		PageBean<Bike> pageBeanBikes = bikeService.findAllBikesPages(this.getModel(), pageNum, pageSize);
		ActionContext.getContext().put("pageBeanUsers", pageBeanBikes);
		List<Bike> data = pageBeanBikes.getData();
		/*for (Bike bike : data) {
			System.out.println(bike.getAddress());
		}*/
		
		return "findAllBikes";
	}
	public String mapUI() {
		return "mapUI";
	}
	public String findBikeMap() throws IOException{
		//System.out.println("++++++++++++++++++");
		List<Bike> allBikes = bikeService.findAllBikes();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"address"});
		
		JSONArray jsonArray = JSONArray.fromObject(allBikes,jsonConfig);
		String json = jsonArray.toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=utf-8");
		ServletActionContext.getResponse().getWriter().write(json);
		System.out.println(json);
		
		return NONE;
	}
	/**通过车锁id查询车锁
	 * @return
	 */
	public String findByBluetoothId(){
		if (StringUtils.isNotBlank(keywords)) {
			Bike oneBike = bikeService.findBikeByBluetoothId(keywords);
			if (oneBike != null) {
				ActionContext.getContext().getValueStack().push(oneBike);
				System.out.println(oneBike.toString());
			}else {
				this.addActionError("查不到此车锁id");
			}
		}else {
			this.addActionError("请输入车锁id");
		}
		return "findByBluetoothId";
	}
}
