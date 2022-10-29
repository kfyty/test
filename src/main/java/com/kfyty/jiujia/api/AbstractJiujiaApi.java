package com.kfyty.jiujia.api;

import cn.hutool.http.HttpUtil;
import com.kfyty.jiujia.api.model.RequestModel;
import com.kfyty.jiujia.encrypt.AesEncrypt;
import com.kfyty.sdk.api.core.AbstractApi;
import com.kfyty.sdk.api.core.ApiResponse;
import com.kfyty.sdk.api.core.constant.ApiConstants;
import com.kfyty.sdk.api.core.decorate.ApiRetryDecorate;
import com.kfyty.sdk.api.core.exception.ApiException;
import com.kfyty.sdk.api.core.http.executor.URLConnectionHttpRequestExecutor;
import com.kfyty.core.utils.JsonUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 14:01
 * @email kfyty725@hotmail.com
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AbstractJiujiaApi<T extends AbstractJiujiaApi<T, R>, R extends ApiResponse> extends AbstractApi<T, R> {
    private String header;
    private RequestModel requestInfo;

    public AbstractJiujiaApi() {
        this.setReadTimeout(6000).cloneConfig().setRequestExecutor(new AesEncryptURLConnectionHttpRequestExecutor());
    }

    @SuppressWarnings("unchecked")
    public T setHeader(String header) {
        this.header = header;
        return (T) this;
    }

    @Override
    public String contentType() {
        return ApiConstants.CONTENT_TYPE_JSON;
    }

    @Override
    public void preProcessor() {
        super.preProcessor();
        RequestModel requestModel = new RequestModel()
                .setData(AesEncrypt.encrypt(JsonUtil.toJson(this.formData())))
                .setHeader(new RequestModel.TellerInfo().setTellerInfo(this.getHeader()));
        this.formData().clear();
        this.addFormData(requestModel);
    }

    public ApiRetryDecorate<T, R> retried() {
        return ApiRetryDecorate.of(this);
    }

    public static class AesEncryptURLConnectionHttpRequestExecutor extends URLConnectionHttpRequestExecutor {

        @Override
        public URLConnectionHttpResponse wrapResponse(Object response) {
            return new AesEncryptURLConnectionHttpResponse((cn.hutool.http.HttpResponse) response);
        }

        public static class AesEncryptURLConnectionHttpResponse extends URLConnectionHttpResponse {

            public AesEncryptURLConnectionHttpResponse(cn.hutool.http.HttpResponse response) {
                super(response);
            }

            @Override
            public byte[] body() {
                String body = HttpUtil.getString(super.body(), UTF_8, false);
                if (Objects.equals(body, "500")) {
                    throw new ApiException("请求失败: body=500");
                }
                String data = JsonUtil.toMap(body).get("data").toString();
                return Optional.ofNullable(AesEncrypt.decrypt(data)).map(e -> e.getBytes(UTF_8)).orElseGet(() -> new byte[0]);
            }
        }
    }
}
