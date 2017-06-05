package com.type.sdk.application;

import android.app.Application;

import com.type.sdk.android.TypeSDKLogger;
import com.type.sdk.android.TypeSDKTool;
import com.type.sdk.android.qihoo.TypeSDKBonjour_Qihoo;
import com.type.sdk.notification.PushService;
import com.qihoo.gamecenter.sdk.matrix.Matrix;

public class TypeApplication extends Application {

	public TypeApplication() {

	}

	@Override
	public void onCreate() {
		super.onCreate();
		TypeSDKLogger.e("TypeApplication");
		Matrix.initInApplication(this);
		String buffStr = TypeSDKTool.getFromAssets(this, "CPSettings.txt");
		TypeSDKLogger.i(buffStr);
		if (buffStr.length() > 0) {
			TypeSDKBonjour_Qihoo.Instance().platform.StringToData(buffStr);
			TypeSDKLogger.i(TypeSDKBonjour_Qihoo.Instance().platform.DataToString());
		}
		PushService.channelName = TypeSDKBonjour_Qihoo.Instance().platform.GetData("channelName");
	}

}
