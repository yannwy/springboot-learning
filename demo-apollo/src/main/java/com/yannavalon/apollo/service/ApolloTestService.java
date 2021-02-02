package com.yannavalon.apollo.service;

import com.yannavalon.apollo.model.DataApiRequestBody;
import com.yannavalon.apollo.model.Description;

import java.util.List;

public interface ApolloTestService {
    List<Description> getApolloConfig(DataApiRequestBody dataApiRequestBody);
}
