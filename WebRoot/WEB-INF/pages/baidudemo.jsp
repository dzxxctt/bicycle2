<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
<style type="text/css">
body, html, #allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
	font-family: "微软雅黑";
}
</style>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/pintuer.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/admin.css">
<script src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript"
	src="http://api.map.baidu.com/api?v=2.0&ak=B3beXkW8ifHPdGrQFLLCB6O8D6LC1ycY"></script>
<title>地图展示 我的家</title>
</head>
<body>
	<!-- <div class="padding border-bottom">
      <ul class="search">
        <li>
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a>
        </li>
      </ul>
    </div> -->
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	//百度地图API功能
	var map = new BMap.Map("allmap"); // 创建Map实例
	var point = new BMap.Point(120.349773, 30.322462); // 创建点坐标

	window.onload = loadMap; //将显示界面指向loadMap函数定义的界面
	function loadMap() {
		loadJScript(); //初始化地图
		map.centerAndZoom(point, 14); //设定地图显示比例              
		map.enableScrollWheelZoom();
		//向地图中添加缩放控件
		var ctrlNav = new window.BMap.NavigationControl({
			anchor : BMAP_ANCHOR_TOP_LEFT,
			type : BMAP_NAVIGATION_CONTROL_LARGE
		});
		map.addControl(ctrlNav);

		//向地图中添加缩略图控件
		var ctrlOve = new window.BMap.OverviewMapControl({
			anchor : BMAP_ANCHOR_BOTTOM_RIGHT,
			isOpen : 1
		});
		map.addControl(ctrlOve);

		//向地图中添加比例尺控件
		var ctrlSca = new window.BMap.ScaleControl({
			anchor : BMAP_ANCHOR_BOTTOM_LEFT
		});
		map.addControl(ctrlSca);

		init(); //实时刷新
		//setInterval("init()",1000)
		//getdata();	
	}
	function loadJScript() {
		var script = document.createElement("script");
		script.type = "text/javascript";
		script.src = "http://api.map.baidu.com/api?v=2.0&ak=B3beXkW8ifHPdGrQFLLCB6O8D6LC1ycY&callback=init";
		document.body.appendChild(script);
	}
	function init() {
		getdata();
		//setInterval("getdata()",1000) ;//设定每一秒钟刷新一次
	}
	function deletePoint() { //删除所有图钉函数
		var allOverlay = map.getOverlays();
		for (var i = 0; i < allOverlay.length - 1; i++) {
			map.clearOverlays(); //清除图钉函数
			return false;
		}
	}
	function getdata() { //使用ajax函数读取数据中的经纬度坐标等信息
		$
				.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/BikeAction_findBikeMap.action",//将调用的地址指向getzuobiao.php文件
					data : {},
					dataType : "json",
					cache : false,
					success : function(data) {
						deletePoint();
						var marker = new Array();
						var info = new Array();
						for (var i = 0; i < data.length; i++) {
							//var info = new Array(); //存放提示信息窗口对象的数组
							//var marker = new Array();
							//alert(data[i].longitude);
							var point = new BMap.Point(data[i].longitude,data[i].latitude);
							if (data[i].bikeStatus == "0") {
								marker[i] = new BMap.Marker(
										point, {icon:
												new BMap.Symbol(BMap_Symbol_SHAPE_POINT, {
										    	scale: 1,//图标缩放大小
										    	fillColor: "green",//填充颜色
										    	fillOpacity: 0.8 //填充透明度
												})
											});
								
								map.addOverlay(marker[i]);
								marker[i].setAnimation(BMAP_ANIMATION_BOUNCE);
							}else{
								marker[i] = new BMap.Marker(point); // 创建标注点
								map.addOverlay(marker[i]); //添加标注
							}

							 var label = new window.BMap.Label(data[i].bluetoothId, { offset: new window.BMap.Size(20, -10) }); //创建标签
							marker[i].setLabel(label);  //添加标签
							//info[i]= new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>" + data[i].bluetoothId + "</br> 经度：" + data[i].longitude +" &nbsp;&nbsp;&nbsp;纬度：" + data[i].latitude +"</br></p>"); // 创建信息窗口对象 
						
							//alert(data1[i].Longitude);
							//info2= new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>" + data1[1].FlyID + "</br>经度：" + data1[1].Oil + "</br> 纬度：" + data1[1].FlyDirection +"</br> 速度：" + data1[1].Speed +"</br> 高度：" + data1[1].Height + "</br></p>"); // 创建信息窗口对象
							//					marker[i].addEventListener("mouseover", function () {
							//					   this.openInfoWindow(info[i] );}
							//                );
						} // end for

						/* marker[0].addEventListener("mouseover", function() {
							this.openInfoWindow(info[0]);
						});
						 marker[1].addEventListener("mouseover", function() {
							this.openInfoWindow(info[1]);
						});
						marker[2].addEventListener("mouseover", function() {
							this.openInfoWindow(info[2]);
						});
						marker[3].addEventListener("mouseover", function() {
							this.openInfoWindow(info[3]);
						});
						marker[4].addEventListener("mouseover", function() {
							this.openInfoWindow(info[4]);
						});
						marker[5].addEventListener("mouseover", function() {
							this.openInfoWindow(info[5]);
						});
						marker[6].addEventListener("mouseover", function() {
							this.openInfoWindow(info[6]);
						});
						marker[7].addEventListener("mouseover", function() {
							this.openInfoWindow(info[7]);
						});
						marker[8].addEventListener("mouseover", function() {
							this.openInfoWindow(info[8]);
						});
						marker[9].addEventListener("mouseover", function() {
							this.openInfoWindow(info[9]);
						});  */

						// for(var i=0;i<data1.length;i++) {
						//					 info= new window.BMap.InfoWindow("<p style=’font-size:12px;lineheight:1.8em;’>" + data1[i].FlyID + "</br>经度：" + data1[i].Oil + "</br> 纬度：" + data1[i].FlyDirection +"</br> 速度：" + data1[i].Speed +"</br> 高度：" + data1[i].Height + "</br></p>"); // 创建信息窗口对象 
						//                marker[i].addEventListener("mouseover", function () {
						//                    this.openInfoWindow(info);// 创建信息窗口对象
						//               
						//                });
						//				}
					}
				});

	}
</script>
