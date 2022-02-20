package com.kfyty.jiujia.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.kfyty.jiujia.api.response.WxPayResponse;
import com.sumwhy.api.core.annotation.Parameter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 11:08
 * @email kfyty725@hotmail.com
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WxPayApi extends AbstractApi<WxPayApi, WxPayResponse> {
    @Parameter(value = "payType", defaultValue = "3")
    private String payType;

    @Parameter(value = "pay_way", defaultValue = "0")
    private String payWay;

    @Parameter(value = "hospital_id", defaultValue = "lysfybjy0002")
    private String hospitalId;

    @Parameter(value = "body", defaultValue = "微信支付挂号")
    private String body;

    @Parameter(value = "chnlcode", defaultValue = "002")
    private String chnlcode;

    @Parameter(value = "trantype", defaultValue = "002")
    private String trantype;

    @Parameter("user_id")
    private String userId;

    @Parameter("user_phone")
    private String userPhone;

    @Parameter("PatientID")
    private String patientID;

    @Parameter("pid")
    private String pid;

    @Parameter("total_amount")
    private String totalAmount;

    @Parameter("registerData")
    private RegisterData registerData;

    @Override
    public String requestURL() {
        return "http://llyl.mbcloud.com/wx/services/wxpay/wxpay/getPaymentOrder.do";
    }

    @Override
    public String method() {
        return "POST";
    }

    @Data
    @Accessors(chain = true)
    public static class RegisterData {
        @JSONField(name = "PatName")
        private String PatName;

        @JSONField(name = "PatientID")
        private String PatientID;

        @JSONField(name = "Day")
        private String Day;

        @JSONField(name = "CardNo")
        private String CardNo;

        @JSONField(name = "VisitID")
        private String VisitID;

        @JSONField(name = "AsRowid")
        private String AsRowid;

        @JSONField(name = "MarkDesc")
        private String MarkDesc;

        @JSONField(name = "SessionType")
        private String SessionType;

        @JSONField(name = "HBTime")
        private String HBTime;

        @JSONField(name = "DepName")
        private String DepName;

        @JSONField(name = "Price")
        private String Price;

        @JSONField(name = "IDCardNo")
        private String IDCardNo;

        @JSONField(name = "PatientName")
        private String PatientName;

        @JSONField(name = "TimeValue")
        private String TimeValue;

        @JSONField(name = "SerNum")
        private String SerNum;

        @JSONField(name = "LockPass")
        private String LockPass;

        @JSONField(name = "phone")
        private String phone;

        @JSONField(name = "registerInfo")
        private String registerInfo;

        @JSONField(name = "payType")
        private String payType = "3";

        @JSONField(name = "RegType")
        private String RegType = "3";

        @JSONField(name = "CardTypeID")
        private String CardTypeID = "2";

        @JSONField(name = "smsCodeType")
        private String smsCodeType = "0";

        @JSONField(name = "smsType")
        private String smsType = "003";

        @JSONField(name = "xinYeMian")
        private String xinYeMian = "true";
    }
}
