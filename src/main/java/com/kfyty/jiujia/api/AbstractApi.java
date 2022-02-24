package com.kfyty.jiujia.api;

import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.kfyty.jiujia.api.model.RequestModel;
import com.kfyty.jiujia.encrypt.AesEncrypt;
import com.sumwhy.api.core.AbstractSumwhyApi;
import com.sumwhy.api.core.SumwhyApiResponse;
import com.sumwhy.api.core.constant.SumwhyApiConstants;
import com.sumwhy.api.core.decorate.SumwhyApiRetryDecorate;
import com.sumwhy.api.core.exception.SumwhyApiException;
import com.sumwhy.api.core.http.HttpRequest;
import com.sumwhy.api.core.http.HttpResponse;
import com.sumwhy.api.core.http.executor.URLConnectionHttpRequestExecutor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;
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
public class AbstractApi<T extends AbstractApi<T, R>, R extends SumwhyApiResponse> extends AbstractSumwhyApi<T, R> {
    private String header;
    private RequestModel requestInfo;

    public AbstractApi() {
        this.setReadTimeout(6000).cloneConfig().setRequestExecutor(new AesEncryptURLConnectionHttpRequestExecutor());
    }

    @SuppressWarnings("unchecked")
    public T setHeader(String header) {
        this.header = header;
        return (T) this;
    }

    @Override
    public String contentType() {
        return SumwhyApiConstants.CONTENT_TYPE_JSON;
    }

    @Override
    public void preProcessor() {
        super.preProcessor();
        RequestModel requestModel = new RequestModel()
                .setData(AesEncrypt.encrypt(JSONObject.toJSONString(this.formData())))
                .setHeader(new RequestModel.TellerInfo().setTellerInfo(this.getHeader()));
        this.formData().clear();
        this.addFormData(requestModel);
    }

    public SumwhyApiRetryDecorate<T, R> retried() {
        return SumwhyApiRetryDecorate.of(this);
    }

    public static class AesEncryptURLConnectionHttpRequestExecutor extends URLConnectionHttpRequestExecutor {

        @Override
        public HttpResponse getResponse(HttpRequest<?> api) {
            URLConnectionHttpResponse response = new AesEncryptURLConnectionHttpResponse(this.buildRequest(api).execute());
            if (response.isSuccess()) {
                return response;
            }
            IoUtil.close(response);
            throw new SumwhyApiException(format("request failed with api: %s, status: %s, body: %s", api.requestURL(), response.code(), HttpUtil.getString(response.body(), UTF_8, false)));
        }

        public static class AesEncryptURLConnectionHttpResponse extends URLConnectionHttpResponse {

            public AesEncryptURLConnectionHttpResponse(cn.hutool.http.HttpResponse response) {
                super(response);
            }

            @Override
            public byte[] body() {
                String body = HttpUtil.getString(super.body(), UTF_8, false);
                if (Objects.equals(body, "500")) {
                    throw new SumwhyApiException("请求失败: body=500");
                }
                String data = JSONObject.parseObject(body).getString("data");
                return Optional.ofNullable(AesEncrypt.decrypt(data)).map(e -> e.getBytes(UTF_8)).orElseGet(() -> new byte[0]);
            }
        }
    }
}
