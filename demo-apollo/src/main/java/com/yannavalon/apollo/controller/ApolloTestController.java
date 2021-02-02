package com.yannavalon.apollo.controller;


import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.yannavalon.apollo.model.DataApiRequestBody;
import com.yannavalon.apollo.model.Description;
import com.yannavalon.apollo.service.ApolloTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@EnableApolloConfig
@RestController
public class ApolloTestController {

    @Autowired
    private ApolloTestService apolloTestService;

    @PostMapping ("hi")
    public List<Description> show(@Valid @RequestBody DataApiRequestBody dataApiRequestBody) {
        return apolloTestService.getApolloConfig( dataApiRequestBody);
    }
}
