//package com.qiyico.qyq.app.wxapi;
//
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.taobao.weex.bridge.JSCallback;
//import com.tencent.mm.opensdk.constants.ConstantsAPI;
//import com.tencent.mm.opensdk.modelbase.BaseReq;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//
//import xxy.com.paylib.BaseCallBackBean;
//import xxy.com.paylib.R;
//import xxy.com.paylib.wxpay.WXPayAPI;
//
//public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
//
//    private static final String TAG = "WXPayEntryActivity";
//
//    private IWXAPI api;
//    public static final String APP_ID = "wx3dff908e443681f0";
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pay_result);
//        api = WXAPIFactory.createWXAPI(this, APP_ID);
//        api.handleIntent(getIntent(), this);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
//        api.handleIntent(intent, this);
//    }
//
//    @Override
//    public void onReq(BaseReq req) {
//    }
//
//    @Override
//    public void onResp(final BaseResp resp) {
//        JSCallback jsCallback = WXPayAPI.getWxPayCallback();
//        if (jsCallback != null) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<BaseResp>().setData(resp));
//        }
//        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
//            int code = resp.errCode;
//            switch (code) {
//                case 0:
//                    showText("支付成功");
//                    showToast("支付成功");
//                    finish();
//                    break;
//                case -1:
//                    showText("支付失败");
//                    showToast("支付失败");
//                    finish();
//                    break;
//                case -2:
//                    showText("支付取消");
//                    showToast("支付取消");
//                    finish();
//                    break;
//                default:
//                    showText("支付失败");
//                    showToast("支付失败");
//                    setResult(RESULT_OK);
//                    finish();
//                    break;
//            }
//        }
//    }
//
//    private void showToast(String msg) {
//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
//    }
//
//    private void showText(String msg) {
//        try {
//            ((TextView) findViewById(R.id.text)).setText(msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}