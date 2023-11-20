package com.s4mobi.application.service.sample;

import com.s4mobi.application.entity.SampleEntity;
import com.s4mobi.application.service.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SampleService extends BaseService {
    Page<SampleEntity> getSamples(final Pageable pageable);
    SampleEntity getSample(final String id);
    SampleEntity createSample(final SampleEntity entity);
    void updateSample(final String id, final SampleEntity entity);
    void remveSample(final String id);
}
