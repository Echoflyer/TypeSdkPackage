package com.type.sdk.application;

import android.app.Application;

import com.type.sdk.android.TypeSDKLogger;
import com.type.sdk.android.TypeSDKTool;
import com.type.sdk.notification.PushService;
import com.type.sdk.android.vivo.TypeSDKBonjour_vivo;
import com.type.sdk.android.TypeSDKDefine.AttName;
import com.vivo.unionsdk.open.VivoUnionSDK;

public class TypeApplication extends Application{

	public TypeApplication() {
		
	}
	
	@Override
	public void onCreate() { 
		super.onCreate();
		TypeSDKLogger.e("TypeApplication");
		String buffStr = TypeSDKTool.getFromAssets(this, "CPSettings.txt");
        TypeSDKLogger.i(buffStr);
    	if(buffStr.length()>0)
    	{
    		TypeSDKBonjour_vivo.Instance().platform.StringToData(buffStr);
    		TypeSDKLogger.i(TypeSDKBonjour_vivo.Instance().platform.DataToString());
    	}
    	PushService.channelName = TypeSDKBonjour_vivo.Instance().platform.GetData("channelName");
		VivoUnionSDK.initSdk(this, TypeSDKBonjour_vivo.Instance().platform.GetData(AttName.APP_ID), false);
	}
	
}
