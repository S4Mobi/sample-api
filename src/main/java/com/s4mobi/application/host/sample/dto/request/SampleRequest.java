package com.s4mobi.application.host.sample.dto.request;

import com.s4mobi.application.entity.SampleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SampleRequest {

    private String code;
    private String name;

    public SampleEntity toEntity() {
        return SampleEntity.builder()
                .code(this.getCode())
                .name(this.getName())
                .build();
    }

    public static SampleRequest fromEntity(final SampleEntity entity) {
        return SampleRequest.builder()
                .code(entity.getCode())
                .name(entity.getName())
                .build();
    }
}
