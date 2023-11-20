package com.s4mobi.infrastructure.network;

import com.s4mobi.infrastructure.configuration.FeignConfig;
import com.s4mobi.infrastructure.network.dto.request.ParseSampleRequest;
import com.s4mobi.infrastructure.network.dto.response.ParseResults;
import com.s4mobi.infrastructure.network.dto.response.ParseSampleResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Sample", url = "https://parseapi.back4app.com/classes", configuration = FeignConfig.class)
public interface  SampleFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/Sample?count=1")
    ParseResults<ParseSampleResponse> getSamples(@RequestParam final int limit, @RequestParam final int skip, @RequestParam final String order);

    @RequestMapping(method = RequestMethod.GET, value = "/Sample/{id}")
    ParseSampleResponse getSample(@PathVariable final String id);

    @RequestMapping(method = RequestMethod.POST, value = "/Sample")
    ParseSampleResponse postSample(@RequestBody final ParseSampleRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/Sample/{id}")
    ParseSampleResponse putSample(@PathVariable final String id, @RequestBody final ParseSampleRequest request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/Sample/{id}")
    void deleteSample(@PathVariable final String id);
}
