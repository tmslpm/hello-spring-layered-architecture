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
  void whenGeneralException_thenReturnApiError() throws Exception {
    mockMvc.perform(get("/trigger-general-error"))
      .andExpect(status().isInternalServerError())
      .andExpect(jsonPath("$.status").value("INTERNAL_SERVER_ERROR"))
      .andExpect(jsonPath("$.message").value("Ouch! An unexpected error has occurred."))
      .andExpect(jsonPath("$.timestamp").exists());
  }

}
