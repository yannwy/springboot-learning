package springbootlearning.demohelloworld.service.serviceImpl;

import org.springframework.stereotype.Service;
import springbootlearning.demohelloworld.model.RequestBodyModel;
import springbootlearning.demohelloworld.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String show(RequestBodyModel requestBodyModel) {
        return requestBodyModel.getName();
    }
}
