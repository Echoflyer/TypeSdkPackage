package com.type.sdk.android.qihoo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.type.sdk.android.BaseMainActivity;
import com.type.sdk.android.TypeSDKLogger;
import com.type.sdk.android.TypeSDKDefine.AttName;

import com.type.utils.*;
import com.type.sdk.android.TypeSDKData;
import com.type.sdk.android.TypeSDKTool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
public class MainActivity extends BaseMainActivity 
{

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
//        String buffStr = TypeSDKTool.getFromAssets(_in_context, "CPSettings.txt");
//        TypeSDKLogger.e("buffStr:" + buffStr);
//    	if(buffStr.length()>0)
//    	{
//    		TypeSDKBonjour_Qihoo.Instance().platform.StringToData(buffStr);
//    		TypeSDKLogger.i("platform:" + TypeSDKBonjour_Qihoo.Instance().platform.DataToString());
//    	}
    	
//    	String result="";
//    	result +="~"+ SharkSDK.Instance().isHasRequest(SharkSDKDefine.AttName.REQUEST_INIT_WITH_SEVER);
//    	result +="~"+ SharkSDK.Instance().isHasRequest(SharkSDKDefine.AttName.SUPPORT_PERSON_CENTER);
//    	result +="~"+ SharkSDK.Instance().isHasRequest(SharkSDKDefine.AttName.SUPPORT_SHARE);
        
//    	TypeSDKLogger.i("result "+result);
    	super.onCreate(savedInstanceState);
        TypeSDKLogger.i("android on create finish");

        
    }
    
//    SharkSDKEventListener initListener = new SharkSDKEventListener(){
//		
//		@Override
//		public Boolean NotifySDKEvent(SharkSDKEvent event){
//			RealOncreate();
//			return null;
//		}
//	};
	
//	private void RealOncreate(){
//		SharkSDK.Instance().ClientInit(_in_context, "");
//	}
	
    @Override
    protected void onDestroy()
    {
    	TypeSDKLogger.e("sdk do destory");
    	TypeSDKBonjour_Qihoo.Instance().onDestroy();
    	super.onDestroy();
    }

    @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		TypeSDKBonjour_Qihoo.Instance().onResume();
	}
    
	
    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		TypeSDKBonjour_Qihoo.Instance().onPause();
	}

	public  void CallInitSDK()
    {
    	String _in_data = "";
    	TypeSDKBonjour_Qihoo.Instance().initSDK(_in_context,_in_data);
    }
    
    public  void CallLogin(String _in_data)
    {
    	TypeSDKLogger.i("call login:" + _in_data);
    	TypeSDKBonjour_Qihoo.Instance().ShowLogin(_in_context,_in_data);
    }
    
    public  void CallLogout()
    {
    	TypeSDKBonjour_Qihoo.Instance().ShowLogout(_in_context);
    }
    
   public String CallPayItem(final String _in_data) {
	   TypeSDKLogger.i("CallPayItem:" + _in_data);
		new Thread() {
			@Override
			public void run() {
				String payMessage;
				try {
					payMessage = HttpUtil.http_get(TypeSDKBonjour_Qihoo
							.Instance().platform
							.GetData(AttName.SWITCHCONFIG_URL));
					
					if (((payMessage.equals("") || payMessage.isEmpty()) && openPay)
							|| TypeSDKTool.openPay(TypeSDKBonjour_Qihoo
									.Instance().platform
									.GetData(AttName.SDK_NAME), payMessage)) 
					{
						TypeSDKBonjour_Qihoo.Instance().PayItem(_in_context,
								_in_data);
					} 
					else 
					{
						TypeSDKNotify_Qihoo notify = new TypeSDKNotify_Qihoo();
						TypeSDKData.PayInfoData payResult = new TypeSDKData.PayInfoData();
						payResult.SetData(AttName.PAY_RESULT, "0");
						notify.Pay(payResult.DataToString());
						Handler dialogHandler = new Handler(Looper.getMainLooper());
						dialogHandler.post(new Runnable(){
							@Override
							public void run() {
								// TODO Auto-generated method stub
								TypeSDKTool.showDialog("暂未开放充值！！！", _in_context);
							}});							
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}.start();
		return "client pay function finished";
	}
    
    public  String CallExchangeItem(String _in_data)
    {
    	return TypeSDKBonjour_Qihoo.Instance().ExchangeItem(_in_context, _in_data);
    }
    
    public  void CallToolBar()
    {
    	TypeSDKBonjour_Qihoo.Instance().ShowToolBar(_in_context);
    }
    public void CallHideToolBar()
    {
    	TypeSDKBonjour_Qihoo.Instance().HideToolBar(_in_context);
    }
    
    public  void CallPersonCenter()
    {
    	TypeSDKBonjour_Qihoo.Instance().ShowPersonCenter(_in_context);
    }
    public void CallHidePersonCenter()
    {
    	TypeSDKBonjour_Qihoo.Instance().HidePersonCenter(_in_context);
    }
    public void CallShare(String _in_data)
    {
    	TypeSDKBonjour_Qihoo.Instance().ShowShare(_in_context, _in_data);
    }
    public void CallSetPlayerInfo(String _in_data)
    {
    	TypeSDKBonjour_Qihoo.Instance().SetPlayerInfo(_in_context, _in_data);
    }
    public void CallExitGame()
    {
    	TypeSDKBonjour_Qihoo.Instance().ExitGame(_in_context);
    }
    public void CallDestory()
    {
    	TypeSDKBonjour_Qihoo.Instance().onDestroy();
    }
    public int CallLoginState()
    {
    	return TypeSDKBonjour_Qihoo.Instance().LoginState(_in_context);
    }
    public String CallUserData()
    {
    	return TypeSDKBonjour_Qihoo.Instance().GetUserData();
    }
    public String CallPlatformData()
    {
    	return TypeSDKBonjour_Qihoo.Instance().GetPlatformData();
    }
    public boolean CallIsHasRequest(String _in_data)
    {
    	return TypeSDKBonjour_Qihoo.Instance().isHasRequest(_in_data);
    }
	
    public String CallAnyFunction(String FuncName,String _in_data)
    {
    	Method[] me = TypeSDKBonjour_Qihoo.Instance().getClass().getMethods();
    	for(int i = 0;i<me.length;++i)
    	{
    		if(me[i].getName().equals(FuncName))
    		{
    			try 
    			{
					return (String) me[i].invoke(TypeSDKBonjour_Qihoo.Instance(),_in_context ,_in_data);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	return "error";
    }
	
	public void AddLocalPush(String _in_data)
    {
    	TypeSDKLogger.i(_in_data);
    	TypeSDKBonjour_Qihoo.Instance().AddLocalPush(_in_context, _in_data);
    }
    
    public void RemoveLocalPush(String _in_data)
    {
    	TypeSDKLogger.i(_in_data);
    	TypeSDKBonjour_Qihoo.Instance().RemoveLocalPush(_in_context, _in_data);
    }
    
    public void RemoveAllLocalPush()
    {
    	
    	TypeSDKBonjour_Qihoo.Instance().RemoveAllLocalPush(_in_context);
    }
	
}
