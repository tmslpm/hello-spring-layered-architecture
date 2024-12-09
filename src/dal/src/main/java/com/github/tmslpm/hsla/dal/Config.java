package com.github.tmslpm.hsla.dal;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

public interface Config {

  @Configuration
  @EnableJpaAuditing
  @SuppressWarnings("unused")
  class ConfigJPA {
  }

}
