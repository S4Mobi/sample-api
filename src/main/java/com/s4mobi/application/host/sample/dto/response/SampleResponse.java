package com.s4mobi.application.host.sample.dto.response;

import com.s4mobi.application.entity.SampleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SampleResponse {

    private String id;
    private String code;
    private String name;

    public SampleEntity toEntity() {
        return SampleEntity.builder()
                .id(this.getId())
                .code(this.getCode())
                .name(this.getName())
                .build();
    }

    public static SampleResponse fromEntity(final SampleEntity entity) {
        return SampleResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .build();
    }
}
