//package com.weex.app.weexmodule;
//
//import android.app.Activity;
//import android.content.Context;
//import android.text.TextUtils;
//
//import com.alibaba.weex.plugin.annotation.WeexModule;
//import com.alipay.sdk.app.EnvUtils;
//import com.google.gson.Gson;
//import com.google.gson.annotations.SerializedName;
//import com.qiyico.qyq.app.wxapi.WXPayEntryActivity;
//import com.taobao.weex.annotation.JSMethod;
//import com.taobao.weex.bridge.JSCallback;
//import com.taobao.weex.common.WXModule;
//import com.tencent.mm.opensdk.modelbase.BaseResp;
//
//import xxy.com.paylib.BaseCallBackBean;
//import xxy.com.paylib.alipay.AliPayAPI;
//import xxy.com.paylib.alipay.PayResult;
//import xxy.com.paylib.alipay.WeexAliPayCallback;
//import xxy.com.paylib.wxpay.WXPayAPI;
//
///**
// * 支付
// * Created by QYG_XXY on 0002 2018/3/2.
// */
//
//@WeexModule(name = "qyqPay")
//public final class PayModule extends WXModule {
//
//    /**
//     * 获取alipay SDK版本号
//     *
//     * @param jsCallback
//     * @return
//     */
//    @JSMethod(uiThread = false)
//    public BaseCallBackBean<String> getAliPaySDKVersion(JSCallback jsCallback) {
//        BaseCallBackBean<String> callBackBean = new BaseCallBackBean<String>();
//        if (mWXSDKInstance == null) {
//            callBackBean.setCode(-200).setMessage("getAliPaySDKVersion ::: getInstall - mWXSDKInstance is null!!!");
//            jsCallback.invokeAndKeepAlive(callBackBean);
//            return callBackBean;
//        }
//        Context context = mWXSDKInstance.getContext();
//        if (mWXSDKInstance == null) {
//            callBackBean.setCode(-200).setMessage("getAliPaySDKVersion ::: getInstall - Context is null!!!");
//            jsCallback.invokeAndKeepAlive(callBackBean);
//            return callBackBean;
//        }
//        Activity activity = null;
//        try {
//            activity = (Activity) context;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (activity == null) {
//            callBackBean.setCode(-200).setMessage("getAliPaySDKVersion ::: getInstall - Context is null!!!");
//            jsCallback.invokeAndKeepAlive(callBackBean);
//            return callBackBean;
//        }
//        return callBackBean.setData(AliPayAPI.getSDKVersion(activity));
//    }
//
//    /**
//     * 支付宝支付业务
//     * 这里只是为了方便直接向商户展示支付宝的整个支付流程；
//     * 所以Demo中加签过程直接放在客户端完成；
//     * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
//     * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
//     * <p>
//     * orderInfo的获取必须来自服务端；
//     *
//     * @param orderInfo
//     * @param jsCallback
//     */
//    @JSMethod
//    public void AliPay(String orderInfo, JSCallback jsCallback) {
//        if (mWXSDKInstance == null) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<PayResult>().setCode(-200).setMessage("AliPay ::: getInstall - mWXSDKInstance is null!!!"));
//            return;
//        }
//        Context context = mWXSDKInstance.getContext();
//        if (mWXSDKInstance == null) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<PayResult>().setCode(-200).setMessage("AliPay ::: getInstall - Context is null!!!"));
//            return;
//        }
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
//        Activity activity = null;
//        try {
//            activity = (Activity) context;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (activity == null) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<PayResult>().setCode(-200).setMessage("AliPay ::: getInstall - Context is null!!!"));
//            return;
//        }
//        AliPayAPI.Pay(activity, orderInfo, new WeexAliPayCallback(jsCallback));
//    }
//
//    /**
//     * 调起微信支付
//     *
//     * @param params     服务器返回的调起微信的参数
//     *                   {
//     *                   "package": "Sign=WXPay",
//     *                   "appid": "wx3dff908e443681f0",
//     *                   "sign": "0E0DE34F65D0B2E5BE18E4D53B93BF70",
//     *                   "partnerid": "1282809701",
//     *                   "prepayid": "wx17132807214232860e3cde764210764961",
//     *                   "noncestr": "2450abfacc8a4e1bbfef5a8539105f64",
//     *                   "timestamp": "1523942818"
//     *                   }
//     * @param jsCallback
//     * @return
//     */
//    @JSMethod
//    public void WXPay(String params, JSCallback jsCallback) {
//        if (mWXSDKInstance == null) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<BaseResp>().setCode(-200).setMessage("UserModule ::: getInstall - mWXSDKInstance is null!!!"));
//            return;
//        }
//        Context context = mWXSDKInstance.getContext();
//        if (mWXSDKInstance == null) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<BaseResp>().setCode(-200).setMessage("UserModule ::: getInstall - Context is null!!!"));
//            return;
//        }
//        WXParams wxParams = null;
//        try {
//            wxParams = new Gson().fromJson(params, WXParams.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (wxParams == null
//                || TextUtils.isEmpty(wxParams.getAppid())
//                || TextUtils.isEmpty(wxParams.getNoncestr())
//                || TextUtils.isEmpty(wxParams.getPackageX())
//                || TextUtils.isEmpty(wxParams.getPartnerid())
//                || TextUtils.isEmpty(wxParams.getPrepayid())
//                || TextUtils.isEmpty(wxParams.getSign())
//                || TextUtils.isEmpty(wxParams.getTimestamp())
//                || !WXPayEntryActivity.APP_ID.equals(wxParams.getAppid())) {
//            jsCallback.invokeAndKeepAlive(new BaseCallBackBean<BaseResp>().setCode(-109).setMessage("参数错误..."));
//            return;
//        }
//        WXPayAPI.Pay(context, WXPayEntryActivity.APP_ID, wxParams, jsCallback);
//    }
//
//
//    public static class WXParams {
//
//        /**
//         * package : Sign=WXPay
//         * appid : wx3dff908e443681f0
//         * sign : 0E0DE34F65D0B2E5BE18E4D53B93BF70
//         * partnerid : 1282809701
//         * prepayid : wx17132807214232860e3cde764210764961
//         * noncestr : 2450abfacc8a4e1bbfef5a8539105f64
//         * timestamp : 1523942818
//         */
//
//        @SerializedName("package")
//        private String packageX;
//        private String appid;
//        private String sign;
//        private String partnerid;
//        private String prepayid;
//        private String noncestr;
//        private String timestamp;
//
//        public String getPackageX() {
//            return packageX;
//        }
//
//        public void setPackageX(String packageX) {
//            this.packageX = packageX;
//        }
//
//        public String getAppid() {
//            return appid;
//        }
//
//        public void setAppid(String appid) {
//            this.appid = appid;
//        }
//
//        public String getSign() {
//            return sign;
//        }
//
//        public void setSign(String sign) {
//            this.sign = sign;
//        }
//
//        public String getPartnerid() {
//            return partnerid;
//        }
//
//        public void setPartnerid(String partnerid) {
//            this.partnerid = partnerid;
//        }
//
//        public String getPrepayid() {
//            return prepayid;
//        }
//
//        public void setPrepayid(String prepayid) {
//            this.prepayid = prepayid;
//        }
//
//        public String getNoncestr() {
//            return noncestr;
//        }
//
//        public void setNoncestr(String noncestr) {
//            this.noncestr = noncestr;
//        }
//
//        public String getTimestamp() {
//            return timestamp;
//        }
//
//        public void setTimestamp(String timestamp) {
//            this.timestamp = timestamp;
//        }
//    }
//}
