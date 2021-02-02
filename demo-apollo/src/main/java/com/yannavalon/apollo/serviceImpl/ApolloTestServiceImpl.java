package com.yannavalon.apollo.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.yannavalon.apollo.model.DataApiRequestBody;
import com.yannavalon.apollo.model.Description;
import com.yannavalon.apollo.service.ApolloTestService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ApolloTestServiceImpl implements ApolloTestService {

    @Override
    public List<Description> getApolloConfig(DataApiRequestBody dataApiRequestBody) {

        Config config = ConfigService.getAppConfig();

        JSONObject jsonObject = dataApiRequestBody.getApiParams();
        Set<String> set = jsonObject.keySet();

        List<Description> descriptionList = new ArrayList<>();

        for(String key:set){
            descriptionList.add(new Description(key,config.getProperty(key,null)));
        }

        return descriptionList;

    }

}
