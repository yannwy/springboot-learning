package com.yannavalon.apollo.model;

import com.alibaba.fastjson.JSONObject;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 鹿韭
 * @date 2020/12/24
 */
@Data
@Builder
public class DataApiRequestBody {

    private String appId;
    private String appSecret;

    @NotBlank

    private String apiCode;

    /**
     * 请求是否来自ERP
     */
    private Boolean fromErp;


    private JSONObject apiParams;
}






