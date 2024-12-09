package com.github.tmslpm.hsla.dal.repository;

import com.github.tmslpm.hsla.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends
  JpaRepository<UserEntity, Long>,
  JpaSpecificationExecutor<UserEntity> {

}
