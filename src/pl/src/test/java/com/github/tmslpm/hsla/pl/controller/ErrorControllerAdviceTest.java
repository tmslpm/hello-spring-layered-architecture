package com.github.tmslpm.hsla.pl.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@TestPropertySource(locations = "classpath:application.test.properties")
@AutoConfigureMockMvc
class ErrorControllerAdviceTest {

  @Autowired
  MockMvc mockMvc;

  @Test
  void test_not_found_user_exception_handle() throws Exception {
    mockMvc.perform(get("/v1/users/get/nonexistent"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.status").value("NOT_FOUND"))
      .andExpect(jsonPath("$.timestamp").exists());
  }

}
