package com.yannavalon.helloworld.service.serviceImpl;

import org.springframework.stereotype.Service;
import com.yannavalon.helloworld.model.RequestBodyModel;
import com.yannavalon.helloworld.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String show(RequestBodyModel requestBodyModel) {
        return requestBodyModel.getName();
    }
}
