package com.s4mobi.application.entity;

import com.s4mobi.infrastructure.network.dto.request.ParseSampleRequest;
import com.s4mobi.infrastructure.network.dto.response.ParseSampleResponse;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SampleEntity {

    private String id;
    private String code;
    private String name;

    public ParseSampleRequest toRequest() {
        return ParseSampleRequest.builder()
                .code(this.getCode())
                .name(this.getName())
                .build();
    }

    public static SampleEntity fromResponse(final ParseSampleResponse response) {
        return SampleEntity.builder()
                .id(response.getId())
                .code(response.getCode())
                .name(response.getName())
                .build();
    }
}
