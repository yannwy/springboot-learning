package com.yannavalon.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.yannavalon.helloworld.model.RequestBodyModel;
import com.yannavalon.helloworld.service.DemoService;

@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @GetMapping("/getHello")
    public String sayHello(@RequestParam(required = false,name = "who") String who){
        if(null == who){
            return "hello world !";
        }else
        return "hello,"+who+"!";
    }
    @PostMapping("/postHello")
    public String sayHello(@RequestBody RequestBodyModel requestBodyModel) {
        return "hello,"+demoService.show(requestBodyModel);
    }
}
