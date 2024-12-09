package com.github.tmslpm.hsla.pl.controller;

import com.github.tmslpm.hsla.bll.exception.UserNotFoundException;
import com.github.tmslpm.hsla.pl.dto.ApiErrorDTO;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorControllerAdvice {
  private final Logger logger = LoggerFactory.getLogger("error-controller-advice");

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ApiErrorDTO> onNotFoundUser(@NotNull UserNotFoundException ex) {
    logger.debug("UserNotFoundException <- not found user {}", ex.getUsername());
    return toResponseDTO(HttpStatus.NOT_FOUND, "Not found requested user", ex);
  }

  @Contract("_, _, _ -> new")
  private <T extends Exception> @NotNull ResponseEntity<ApiErrorDTO> toResponseDTO(HttpStatus status, String messages, T ex) {
    logger.debug("Exception caught in controller advice: Exception <- {}, Message <- {}",
        ex.getClass().getName(), ex.getMessage(), ex);
    return new ResponseEntity<>(ApiErrorDTO
      .builder()
      .status(status)
      .message(messages)
      .timestamp(LocalDateTime.now())
      .build(), status);
  }

}
