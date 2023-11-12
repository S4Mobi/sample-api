package com.s4mobi.infrastructure.network;

import com.s4mobi.infrastructure.configuration.FeignConfig;
import com.s4mobi.infrastructure.network.dto.request.ParseSampleRequest;
import com.s4mobi.infrastructure.network.dto.response.ParseResults;
import com.s4mobi.infrastructure.network.dto.response.ParseSampleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "Sample", url = "https://parseapi.back4app.com/classes", configuration = FeignConfig.class)
public interface  SampleFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/Sample")
    ParseResults<ParseSampleResponse> getSamples();

    @RequestMapping(method = RequestMethod.GET, value = "/Sample/{id}")
    ParseSampleResponse getSample(final @PathVariable String id);

    @RequestMapping(method = RequestMethod.POST, value = "/Sample")
    ParseSampleResponse postSample(final @RequestBody ParseSampleRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/Sample/{id}")
    ParseSampleResponse putSample(final @PathVariable String id, final @RequestBody ParseSampleRequest request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/Sample/{id}")
    ParseSampleResponse deleteSample(final @PathVariable String id);
}
