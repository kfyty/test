package com.kfyty.jiujia.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kfyty.jiujia.api.response.WxPayResponse;
import com.kfyty.sdk.api.core.annotation.Parameter;
import com.kfyty.core.utils.JsonUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

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
public class WxPayApi extends AbstractJiujiaApi<WxPayApi, WxPayResponse> {
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

    @Override
    public WxPayResponse exchange() {
        String body = new String(this.execute(), UTF_8);
        Map<String, Object> map = JsonUtil.toMap(body);
        return new WxPayResponse(map.get("code").toString(), map.get("msg").toString(), JsonUtil.toJson(map.get("data")));
    }

    @Data
    @Accessors(chain = true)
    public static class RegisterData {
        @JsonProperty(value = "PatName")
        private String PatName;

        @JsonProperty(value = "PatientID")
        private String PatientID;

        @JsonProperty(value = "Day")
        private String Day;

        @JsonProperty(value = "CardNo")
        private String CardNo;

        @JsonProperty(value = "VisitID")
        private String VisitID;

        @JsonProperty(value = "AsRowid")
        private String AsRowid;

        @JsonProperty(value = "MarkDesc")
        private String MarkDesc;

        @JsonProperty(value = "SessionType")
        private String SessionType;

        @JsonProperty(value = "HBTime")
        private String HBTime;

        @JsonProperty(value = "DepName")
        private String DepName;

        @JsonProperty(value = "Price")
        private String Price;

        @JsonProperty(value = "IDCardNo")
        private String IDCardNo;

        @JsonProperty(value = "PatientName")
        private String PatientName;

        @JsonProperty(value = "TimeValue")
        private String TimeValue;

        @JsonProperty(value = "SerNum")
        private String SerNum;

        @JsonProperty(value = "LockPass")
        private String LockPass;

        @JsonProperty(value = "phone")
        private String phone;

        @JsonProperty(value = "registerInfo")
        private String registerInfo;

        @JsonProperty(value = "payType")
        private String payType = "3";

        @JsonProperty(value = "RegType")
        private String RegType = "3";

        @JsonProperty(value = "CardTypeID")
        private String CardTypeID = "2";

        @JsonProperty(value = "smsCodeType")
        private String smsCodeType = "0";

        @JsonProperty(value = "smsType")
        private String smsType = "003";

        @JsonProperty(value = "xinYeMian")
        private String xinYeMian = "true";
    }
}
