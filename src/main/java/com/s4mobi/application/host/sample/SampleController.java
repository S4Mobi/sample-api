package com.s4mobi.application.host.sample;

import com.s4mobi.application.entity.SampleEntity;
import com.s4mobi.application.host.BaseEndpoint;
import com.s4mobi.application.host.sample.dto.request.SampleRequest;
import com.s4mobi.application.host.sample.dto.response.ErrorResponse;
import com.s4mobi.application.host.sample.dto.response.SampleResponse;
import com.s4mobi.application.service.sample.SampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Sample")
@RequestMapping("v1/samples")
public class SampleController extends BaseEndpoint {

    @Autowired
    private SampleService sampleService;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get All Sample List.", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Page<SampleResponse>> getAllSample(final Pageable pageable) {
        final Page<SampleEntity> entities = sampleService.getSamples(pageable);
        return ResponseEntity.ok(entities.map(SampleResponse::fromEntity));
    }

    @CrossOrigin
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Get One Sample by Id.", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<SampleResponse> getSample(final @PathVariable String id) {
        final SampleEntity entity = sampleService.getSample(id);
        return ResponseEntity.ok(SampleResponse.fromEntity(entity));
    }

    @CrossOrigin
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Post One Sample.", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "422", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> postSample(final @RequestBody SampleRequest request) {
        final SampleEntity entity = sampleService.createSample(request.toEntity());
        return ResponseEntity.created(this.createUri(entity.getId())).build();
    }

    @CrossOrigin
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Change all Sample by Id.", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> puttSample(final @PathVariable String id, final @RequestBody SampleRequest request) {
        sampleService.updateSample(id, request.toEntity());
        return ResponseEntity.ok().build();
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}")
    @Operation(description = "Remove on Sample by Id.", responses = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "422", description = "Invalid request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Internal error", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Void> deleteSample(final @PathVariable String id) {
        sampleService.remveSample(id);
        return ResponseEntity.ok().build();
    }
}
