package bicycle.utils;

import net.sf.json.JSONObject;

/**
 * 百度工具类
 * 
 * @author xuyw
 * @email xyw10000@163.com
 * @date 2014-06-22
 */
public class BaiDuUtil {
	public static String getAddr(String lat, String lng) {
		String str = (String) getLocationInfo(lat, lng).getJSONObject("result").get("formatted_address");
		return str;
	}

	public static JSONObject getLocationInfo(String lat, String lng) {
		String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat + ","
				+ lng + "&output=json&ak=B3beXkW8ifHPdGrQFLLCB6O8D6LC1ycY"+"&pois=0";
		JSONObject obj = JSONObject.fromObject(HttpUtil.getRequest(url));
		return obj;
	}

	public static void main(String[] args) {
		System.out.println(BaiDuUtil.getAddr("30.322462", "120.349773"));
	}
}