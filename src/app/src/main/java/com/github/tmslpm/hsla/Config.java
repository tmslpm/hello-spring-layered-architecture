package com.github.tmslpm.hsla;

import com.github.tmslpm.hsla.dal.entity.UserEntity;
import com.github.tmslpm.hsla.dal.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public interface Config {

  @Configuration
  @AllArgsConstructor
  class DevPopulateDatabase {
    private final IUserRepository userRepository;

    @Bean
    CommandLineRunner populate() {
      return args -> {
        userRepository.save(new UserEntity("Linda"));
        userRepository.save(new UserEntity("Paul"));
        userRepository.save(new UserEntity("Etoile"));
        userRepository.save(new UserEntity("John"));
      };
    }

  }
}
