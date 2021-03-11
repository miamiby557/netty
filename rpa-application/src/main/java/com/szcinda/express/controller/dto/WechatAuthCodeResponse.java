package com.szcinda.express.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WechatAuthCodeResponse implements Serializable {
    @JsonProperty(value = "openid")
    private String openId;

    @JsonProperty(value = "session_key")
    private String sessionKey;

    @JsonProperty(value = "unionid")
    private String unionId;

    @JsonProperty(value = "errcode")
    private int errCode;

    @JsonProperty(value = "errmsg")
    private String errMsg;
}
