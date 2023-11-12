package com.s4mobi.application.host;

import com.s4mobi.application.exception.BusinessException;
import com.s4mobi.application.exception.UnexpectedException;
import com.s4mobi.application.host.sample.dto.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public abstract class BaseEndpoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseEndpoint.class);

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        final ErrorResponse erro = ErrorResponse.builder().message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
    }

    @ExceptionHandler(UnexpectedException.class)
    private ResponseEntity<ErrorResponse> handleUnexpectedException(UnexpectedException e) {
        final ErrorResponse erro = ErrorResponse.builder().message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(Throwable.class)
    private ResponseEntity<ErrorResponse> handleUnknownException(Throwable unknownError) {
        final UnexpectedException exception = new UnexpectedException();
        LOGGER.error(exception.getMessage(), unknownError);
        final ErrorResponse erro = ErrorResponse.builder().message(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    protected URI createUri(final String id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
