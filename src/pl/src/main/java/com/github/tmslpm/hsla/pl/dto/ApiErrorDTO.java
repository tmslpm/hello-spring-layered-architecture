package com.github.tmslpm.hsla.pl.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ApiErrorDTO(HttpStatus status, String message, LocalDateTime timestamp) {

}
