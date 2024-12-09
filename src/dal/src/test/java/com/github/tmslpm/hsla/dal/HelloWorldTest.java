package com.github.tmslpm.hsla.dal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest()
@TestPropertySource(locations = "classpath:application.test.properties")
public class HelloWorldTest {

  @Test
  void exampleTest() {
    final String expected = "foo";
    final String actual = "bar";
    Assertions.assertNotEquals(expected, actual);
  }

}
