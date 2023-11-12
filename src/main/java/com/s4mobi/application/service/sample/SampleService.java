package com.s4mobi.application.service.sample;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s4mobi.application.entity.SampleEntity;
import com.s4mobi.application.service.BaseService;

import java.util.List;

public interface SampleService extends BaseService {
    List<SampleEntity> getSamples();
    SampleEntity getSample(final String id);
    SampleEntity createSample(final SampleEntity entity);
    void updateSample(final String id, final SampleEntity entity);
    void remveSample(final String id);
}
