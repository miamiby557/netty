package com.szcinda.express.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.szcinda.express.controller.dto.Result;
import com.szcinda.express.controller.dto.TokenDto;
import com.szcinda.express.controller.dto.WechatAuthCodeResponse;
import com.szcinda.express.controller.dto.WechatDto;
import com.szcinda.express.controller.netty.NettyChannelInboundHandlerAdapter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/wechat")
public class WechatController {
    /*@Value("${wechat.sessionHost}")
    private String wechatHost;
    @Value("${wx.appId}")
    private String appId;
    @Value("${wx.appSecret}")
    private String appSecret;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public WechatController(ObjectMapper objectMapper) {
        this.restTemplate = new RestTemplate();
        this.objectMapper = objectMapper;
    }

    // 公众号登录
    @RequestMapping(value = "callBack", method = RequestMethod.GET)
    @ResponseBody
    public Result<TokenDto> callBack(@RequestParam("code") String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId +
                "&secret=" + appSecret +
                "&code=" + code +
                "&grant_type=authorization_code";
        TokenDto tokenDto = getObject(url, TokenDto.class);
        Assert.notNull(tokenDto, "获取OPENID失败");
        return Result.success(tokenDto);
    }

    // 小程序登录
    private WechatAuthCodeResponse getWxSession(String code) {
        String urlString = "?appid={appid}&secret={srcret}&js_code={code}&grant_type={grantType}";
        String response = restTemplate.getForObject(
                wechatHost + urlString, String.class,
                appId,
                appSecret,
                code,
                "authorization_code");
        ObjectMapper objectMapper = new ObjectMapper();
        WechatAuthCodeResponse wechatAuthCodeResponse;
        try {
            wechatAuthCodeResponse = objectMapper.readValue(response, WechatAuthCodeResponse.class);
        } catch (Exception e) {
            e.printStackTrace();
            wechatAuthCodeResponse = new WechatAuthCodeResponse();
            wechatAuthCodeResponse.setErrCode(-1);
            wechatAuthCodeResponse.setErrMsg(e.getMessage());
        }
        return wechatAuthCodeResponse;
    }

    @GetMapping("getOpenId/{code}")
    public Result getOpenId(@PathVariable("code") String code) {
        WechatAuthCodeResponse response = getWxSession(code);
        return returnResult(response);
    }

    private Result returnResult(WechatAuthCodeResponse response) {
        System.out.println(response);
        if (response.getErrCode() == 0) {
            return Result.success(response.getOpenId());
        } else {
            return Result.fail(response.getErrMsg());
        }
    }

    private <T> T getObject(String url, Class<T> clazz) {
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                return objectMapper.readValue(result, clazz);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/receiveData")
    public Result receiveData(@RequestBody WechatDto data) {
        System.out.println("接收到微信数据:" + data.getData());
        NettyChannelInboundHandlerAdapter.receiveWechatData(data.getData());
        return Result.success();
    }*/
}
