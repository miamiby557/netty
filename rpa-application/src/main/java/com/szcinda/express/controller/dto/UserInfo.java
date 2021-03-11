package com.szcinda.express.controller.dto;


import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private String country;
    private String openid;
    private String province;
    private String city;
    private String nickname;
    private String headimgurl;
    private String sex;
}