package com.github.tmslpm.hsla.pl.controller;

import com.github.tmslpm.hsla.pl.dto.ApiErrorDTO;
import lombok.AllArgsConstructor;
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

  @ExceptionHandler(NullPointerException.class)
  public ResponseEntity<ApiErrorDTO> onNullPointerError(NullPointerException nullPointerException) {
    return toResponseDTO(
      HttpStatus.BAD_REQUEST,
      "A required object was null.",
      nullPointerException);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ApiErrorDTO> onIllegalArgumentError(IllegalArgumentException illegalArgumentException) {
    return toResponseDTO(
      HttpStatus.BAD_REQUEST,
      "Invalid input provided. Please check the data and try again.",
      illegalArgumentException);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorDTO> onUnexpectedError(Exception exception) {
    return toResponseDTO(
      HttpStatus.INTERNAL_SERVER_ERROR,
      "Ouch! An unexpected error has occurred.",
      exception);
  }

  @Contract("_, _, _ -> new")
  private <T extends Exception> @NotNull ResponseEntity<ApiErrorDTO> toResponseDTO(HttpStatus status, String messages, T exception) {
    logger.debug(
      "Exception caught in controller advice: Exception <- {}, Message <- {}",
      exception.getClass().getName(),
      exception.getMessage(),
      exception);

    return new ResponseEntity<>(ApiErrorDTO
      .builder()
      .status(status)
      .message(messages)
      .timestamp(LocalDateTime.now())
      .build(), status);
  }

}
