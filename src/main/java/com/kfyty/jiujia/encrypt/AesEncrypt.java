package com.kfyty.jiujia.encrypt;

import cn.hutool.core.io.IoUtil;
import com.kfyty.loveqq.framework.core.utils.CommonUtil;
import lombok.SneakyThrows;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * 描述:
 *
 * @author kfyty725
 * @date 2022/2/19 11:36
 * @email kfyty725@hotmail.com
 */
public abstract class AesEncrypt {
    private static final ScriptEngine SCRIPT_ENGINE = new ScriptEngineManager().getEngineByName("javascript");

    static {
        loadJsScript("/static/js/rollups/aes.js");
        loadJsScript("/static/js/aes.js");
        loadJsScript("/static/js/aes-min.js");
        loadJsScript("/static/js/aes-encrypt.js");
    }

    @SneakyThrows
    public static String encrypt(String data) {
        if (CommonUtil.empty(data)) {
            return null;
        }
        Invocable invocable = (Invocable) SCRIPT_ENGINE;
        return invocable.invokeFunction("encrypt", data).toString();
    }

    @SneakyThrows
    public static String decrypt(String data) {
        if (CommonUtil.empty(data)) {
            return null;
        }
        Invocable invocable = (Invocable) SCRIPT_ENGINE;
        return invocable.invokeFunction("decrypt", data).toString();
    }

    @SneakyThrows
    private static void loadJsScript(String path) {
        InputStream stream = AesEncrypt.class.getResourceAsStream(path);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IoUtil.copy(Objects.requireNonNull(stream, "获取 js 脚本失败: " + path), outputStream);
        SCRIPT_ENGINE.eval(outputStream.toString());
        IoUtil.close(stream);
        IoUtil.close(outputStream);
    }
}
