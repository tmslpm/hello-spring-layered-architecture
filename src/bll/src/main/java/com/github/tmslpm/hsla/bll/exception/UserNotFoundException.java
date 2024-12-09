package com.github.tmslpm.hsla.bll.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
@Builder
public class UserNotFoundException extends NullPointerException {
  private String username;
}
