## 淘途途TTS、路书SDK简介 ##

...

##接入准备

####一、将相关aar包放入libs文件夹中并引入工程

		//淘途途TTS 路书aar
		implementation(name: 'taotutu_route-release', ext: 'aar')
		//淘途途核心库
   		implementation 'com.github.Jiayuonline:TaotutuCore:1.0.10'

		//如果使用淘途途页面 需要添加以下包(语音合成及地图) 否则不添加
		 
    	implementation(name: 'taotutu_tts-release', ext: 'aar')
    	implementation(name: 'AMap3DMap_6.9.2_AMapNavi_6.9.0_AMapSearch_6.9.2_AMapLocation_4.7.0_20190710', ext: 'aar')

###二、使用您申请的key 初始化淘途途sdk

		TaotutuManager.getInstance().init(this);
        TaotutuManager.getInstance().setAccessSecret("您的淘途途secret");
        TaotutuManager.getInstance().setAccessKey("您的淘途途key");


## 功能使用 ##

####一、获取路书列表

 		TaoRouteManager routeManager = new TaoRouteManager();

		routeManager.getRouteList(pageNo, 10, new RouteListPresenter.OnRouteListCallback() {
            @Override
            public void onSuccess(List<RouteBookListBean> list) {
                 
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });

####二、获取路书详情

		routeManager.getRouteDetail(id, new RouteDetailPresenter.OnDetailCallback() {
            @Override
            public void onSuccess(RouteBookDetailBean routeBookDetailBean) {
                 
            }

            @Override
            public void onFailed(Throwable throwable) {

            }
        });




 



####三、获取TTS点

		routeManager.getTTSList("121.388467,31.176875", 1, 10, new RouteDetailPresenter.OnTTSCallback() {
                    @Override
                    public void onSuccess(List<TTSPointBean> list) {

                    }

                    @Override
                    public void onFailed(Throwable throwable) {

                    }
                });

####四、相关页面

		//跳转至路书列表页
	 	TaoRouteManager.goRouteList(MainActivity.this);


		//跳转至路书详情页
	    TaoRouteManager.goRouteDetail(MainActivity.this,"4635b9cfc91f4965af01aa643faab4ad");





##下载完整示例代码
		https://github.com/Jiayuonline/RouteDemo


##数据字典

	路书表
	字段	类型	空	注释
	routeId	string	否	路书id
	coverPath	string	否	封面图
	title	string	否	标题
	subtitle	string	是	副标题
	labels	string[]	否	标签数组
	province	string	否	省
	city	string	否	市
	district	string)	否	区
	dayNum	int	否	路书总天数
	description	string	否	简介
	placesList	list<list>	否	路书详情集合
	createTime	date	否	创建时间
	updateTime	date	否	更新时间
