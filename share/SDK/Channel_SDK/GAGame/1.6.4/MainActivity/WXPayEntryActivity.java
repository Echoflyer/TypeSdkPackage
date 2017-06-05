package @package@.wxapi;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.goldautumn.sdk.pay.GAGameSDKWXEntryActivity;

public class WXPayEntryActivity extends GAGameSDKWXEntryActivity implements IWXAPIEventHandler {

	private static final String TAG = "WXPayEntryActivity";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.finish();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
	}

	@Override
	public void onReq(BaseReq req) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onReq, errCode = " + req);
		super.onReq(req);
	}

	@Override
	public void onResp(BaseResp resp) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onPayFinish, errCode = " + resp.errCode+",resp.getType() = " + resp.getType());
		super.onResp(resp);
	}
	
}