package com.s4mobi.infrastructure.network.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ParseSampleResponse {

    @JsonProperty("objectId")
    private String id;
    private String code;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}
