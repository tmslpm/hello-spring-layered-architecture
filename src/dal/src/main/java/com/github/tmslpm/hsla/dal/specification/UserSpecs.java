package com.github.tmslpm.hsla.dal.specification;

import com.github.tmslpm.hsla.dal.entity.UserEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public interface UserSpecs {

  static Specification<UserEntity> createdAfter(LocalDate date) {
    return (r, q, b) -> b.greaterThan(r.get("createdAt"), date);
  }

}
