//package xxy.com.paylib.wxpay;
//
//import android.content.Context;
//
//import com.taobao.weex.bridge.JSCallback;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//import com.tencent.mm.opensdk.modelpay.PayReq;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;
//import com.weex.app.weexmodule.PayModule;
//
//import xxy.com.paylib.BaseCallBackBean;
//
///**
// * Created by QYG_XXY on 0002 2018/3/2.
// */
//
//public final class WXPayAPI {
//
//    private static JSCallback wxPayCallback;
//
//    public static JSCallback getWxPayCallback() {
//        return wxPayCallback;
//    }
//
//    public static boolean Pay(Context context, String appId, PayModule.WXParams params, JSCallback jsCallback) {
//        try {
//            IWXAPI api;
//            // 通过WXAPIFactory工厂，获取IWXAPI的实例
//            api = WXAPIFactory.createWXAPI(context, appId, false);
//            // 将该app注册到微信
//            if (!api.registerApp(appId)) {
//                jsCallback.invokeAndKeepAlive(new BaseCallBackBean<BaseResp>().setCode(-109).setMessage("注册到微信出错..."));
//                return false;
//            }
//            PayReq request = new PayReq();
//            request.appId = params.getAppid();//qyq:wx3dff908e443681f0   test:wxd930ea5d5a258f4f
//            request.partnerId = params.getPartnerid();
//            request.prepayId = params.getPrepayid();
//            request.packageValue = params.getPackageX();
//            request.nonceStr = params.getNoncestr();
//            request.timeStamp = params.getTimestamp();
//            request.sign = params.getSign();
//            wxPayCallback = jsCallback;
//            return api.sendReq(request);
//        } catch (Exception e) {
//            e.printStackTrace();
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<BaseResp>().setCode(-108).setMessage("调起微信出错..."));
//            return false;
//        }
//    }
//
//}
