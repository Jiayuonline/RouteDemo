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

		routeManager.getRouteList(1, 10,"121.394979,31.182782", new RouteListPresenter.OnRouteListCallback() {
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



#车友组队sdk
###接入准备
1. 将相关文件放进libs文件夹

		libqndroid_rtc.so
		grouplib-release.aar

2. 在build.gradle中添加以下依赖
	
	
		implementation 'com.github.Jiayuonline:TaotutuCore:1.0.10'
		implementation 'com.github.0xZhangKe:WebSocketDemo:3.1'
	    implementation(name: 'grouplib-release', ext: 'aar')

 
3. 添加libs路径（如果已有则不需要）

		repositories {
   		 flatDir {
      	  dirs 'libs'
    	}
		}

		 sourceSets{
        main{
            jniLibs.srcDirs = ['libs']
        }
    	}

1. 相关权限

		 <uses-permission android:name="android.permission.INTERNET" />
	    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
	    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
	  	<uses-permission android:name="android.permission.RECORD_AUDIO" />
	    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />

4. 初始化

		 TaotutuManager.getInstance().init(this);
        TaotutuManager.getInstance().setAccessSecret("您的秘钥");
        TaotutuManager.getInstance().setAccessKey("您的key");
        TaotutuManager.getInstance().setChannel("您的渠道");
        TaotutuManager.getInstance().setPlatform("andorid");//平台
        TaotutuManager.getInstance().setUniqueCode("您的用户唯一码/设备码");		


###方法说明

1. 创建车队

		  GroupManager.createGroup( name, new GroupPresenter.OnCreateCallback() {
            @Override
            public void onCreateSuccess() {
                
            }
        });

2. 加入车队

		  GroupManager.joinGroup(  code, new GroupPresenter.OnJoinCallback() {
            @Override
            public void onJoinSuccess() {
                
            }
        });

3. 车队列表

		 GroupManager.getGroupList(  new GroupPresenter.OnGroupListCallback() {
            @Override
            public void onGroupList(List<GroupListModel> data) {
               
            }
        });

4. 车队详情

		  GroupManager.getGroupDetail(  id,  new GroupPresenter.OnGroupDetailCallback() {
            @Override
            public void onDetail(GroupDetailBean detailBean) {
                
            }
        });

5. 添加成员

		  GroupManager.addMember("","", new GroupPresenter.OnAddMemberCallback() {
            @Override
            public void onAddSuccess() {
                
            }
        });

1. 成员列表

		  GroupManager.getMemberList(id, new GroupPresenter.OnMemberListCallback() {
            @Override
            public void onMemberList(List<MemberListModel> memberListModels) {
                
            }
        });

1. 好友列表

		 GroupManager.getFriendList(  new GroupPresenter.OnFriendListCallback() {
	            @Override
	            public void onFriendList(List<FriendListModel> friendListModels) {
	              
	            }
	        });

1. 修改名称

		  GroupManager.changeName("573399", name, new GroupPresenter.OnChangeCallback() {
            @Override
            public void onChangeSuccess() {
              
            }
        });

1. 退出车队

		  GroupManager.quitGroup(et_group_id.getText().toString(), new GroupPresenter.OnQuitCallback() {
            @Override
            public void onQuitSuccess() {
                 
            }
        });

1. 连接车队socket

		GroupManager.getInstance().startConnect(mid
                , new GroupPresenter.OnScoketCallback() {
                    @Override
                    public void onConnected(String url) {
                         
                    }

                    @Override
                    public void onConnectFailed(Throwable e) {
                         
                    }

                    @Override
                    public void onDisconnect() {
                         
                    }

                    @Override
                    public void onSendDataError(Throwable cause) {
                         
                    }

                    @Override
                    public void onMessage(String message) {
                         
                    }

                    @Override
                    public void onMessage(SocketBean message) {
                       

                    }
                });

1. 通过socket发送位置

		        GroupManager.sendLocation("123,123","ddd",GroupManager.LOCATION_TYPE_SHARE);

1. 初始化小队语音

			/**
	         * 需要添加麦克风权限
	         */
	        if (Build.VERSION.SDK_INT >= 23) {
	            AndPermission.with(this)
	                    .runtime()
	                    .permission(Permission.RECORD_AUDIO
	                    )
	                    .onGranted(new Action<List<String>>() {
	                        @Override
	                        public void onAction(List<String> data) {
	
	                        }
	                    })
	                    .onDenied(new Action<List<String>>() {
	                        @Override
	                        public void onAction(List<String> data) {
	
	                        }
	                    })
	                    .start();
	        }

		//初始化语音引擎 
        GroupManager.getInstance().initQNY(this, new GroupPresenter.OnRoomCallback() {
            @Override
            public void onConnected() {
                tv_content.setText("加入房间");
            }

            @Override
            public void onConnecting() {
                tv_content.setText("正在加入房间...."); 
            }

            @Override
            public void onLeaveRoom() {
                tv_content.setText("离开房间");
            }

            @Override
            public void onRemoteUserJoined() {
                tv_content.setText("队友加入房间"); 
            }

            @Override
            public void onRemoteUserLeft() {
                tv_content.setText("队友离开房间");

            }

            @Override
            public void onError(int i, String s) {
                tv_content.setText("语音出错: "+i+" , "+s);

            }
        });

1. 加入房间并开启语音

		        GroupManager.getInstance().joinRoom(roomToken);

1. 离开房间

		        GroupManager.getInstance().leaveRoom();

1. 开启/关闭麦克风

		        GroupManager.getInstance().publishAudio(true);

1. 是否静音 

	       GroupManager.getInstance().muteRemoteAudio(true);
1. 断开连接

	        GroupManager.getInstance().disConnect();

