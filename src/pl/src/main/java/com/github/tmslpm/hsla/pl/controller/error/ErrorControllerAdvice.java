/*
 * MIT License
 *
 * Copyright (c) 2024 Thomas.L
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.tmslpm.hsla.pl.controller.error;

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
