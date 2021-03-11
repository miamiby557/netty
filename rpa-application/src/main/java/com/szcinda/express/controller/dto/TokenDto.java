package com.szcinda.express.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class TokenDto implements Serializable {
    @JsonProperty("openid")
    private String openId;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("expires_in")
    private long expiresIn;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("scope")
    private String scope;
}
