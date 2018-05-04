package xxy.com.paylib.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * Created by QYG_XXY on 0001 2018/3/1.
 */

public final class AliPayAPI {
    /**
     * 支付宝支付业务：入参app_id
     */
//    public static final String APPID = "";
    private static final int SDK_PAY_FLAG = 0x11;//同步结果标志位

    /**
     * get the sdk version. 获取SDK版本号
     */
    public static String getSDKVersion(Activity activity) {
        return activity == null ? null : new PayTask(activity).getVersion();
    }


    /**
     * 支付宝支付业务
     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；
     * 所以Demo中加签过程直接放在客户端完成；
     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
     * <p>
     * orderInfo的获取必须来自服务端；
     *
     * @param orderInfo
     */
    public static void Pay(final Activity activity, final String orderInfo, final AliPayCallback callback) {
        if (TextUtils.isEmpty(orderInfo)) {
            callback.callback(-100, null, "orderInfo is empty!!!");
            return;
        }
        if (activity == null) {
            callback.callback(-101, null, "activity is null  !!!");
            return;
        }
        if (callback == null) {
            callback.callback(-102, null, "org.weex.plugin.callback is empty!!!");
            return;
        }
        @SuppressLint("HandlerLeak") final Handler mHandler = new Handler() {
            @SuppressWarnings("unused")
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SDK_PAY_FLAG: {//支付宝支付Demo
                        @SuppressWarnings("unchecked")
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                        callback.callback(0, payResult, "success");
                        break;
                    }
                    default:
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        }).start();
    }


    public interface AliPayCallback {
        /**
         * /*
         * 对于支付结果，请商户依赖服务端的异步通知结果。
         * 同步通知结果，仅作为支付结束的通知。
         * <p>
         * String resultInfo = payResult.getResult();// 同步返回需要验证的信息
         * String resultStatus = payResult.getResultStatus();
         * //判断resultStatus 为9000则代表支付成功
         * if (TextUtils.equals(resultStatus, "9000")) {
         * // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
         * Toast.makeText(PayDemoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
         * } else {
         * // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
         * Toast.makeText(PayDemoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
         * }
         *
         * @param code      //说明：表示调用过程是否成功，0成功，其他为不成功(支付是否成功参考result)
         * @param payResult //支付宝支付同步结果参考上面说明
         */
        void callback(int code, PayResult payResult, String msg);
    }
}
