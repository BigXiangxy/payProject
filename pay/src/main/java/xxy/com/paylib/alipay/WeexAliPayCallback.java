package xxy.com.paylib.alipay;

import com.taobao.weex.bridge.JSCallback;

import xxy.com.paylib.BaseCallBackBean;

/**
 * Created by QYG_XXY on 0001 2018/3/1.
 */

public class WeexAliPayCallback implements AliPayAPI.AliPayCallback {

    private JSCallback jsCallback;

    public WeexAliPayCallback(JSCallback jsCallback) {
        this.jsCallback = jsCallback;
    }

    @Override
    public void callback(int code, PayResult payResult, String msg) {
        if (code == 0) {
            if (jsCallback != null)
                jsCallback.invokeAndKeepAlive(new BaseCallBackBean<PayResult>().setData(payResult));
        } else {
            if (jsCallback != null)
                jsCallback.invokeAndKeepAlive(new BaseCallBackBean<PayResult>().setCode(code).setMessage(msg));
        }
    }
}
