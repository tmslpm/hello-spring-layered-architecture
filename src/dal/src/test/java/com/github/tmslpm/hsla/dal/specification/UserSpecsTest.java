package com.github.tmslpm.hsla.dal.specification;

import com.github.tmslpm.hsla.dal.entity.UserEntity;
import com.github.tmslpm.hsla.dal.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@TestPropertySource(locations = "classpath:application.test.properties")
class UserSpecsTest {
  @Autowired
  IUserRepository userRepository;

  @BeforeEach
  public void setUp() {
    userRepository.deleteAll();

    var arr = List.of(
      "UserSpecsTest-A", LocalDate.of(1000, 6, 15),
      "UserSpecsTest-B", LocalDate.of(2000, 6, 15),
      "UserSpecsTest-C", LocalDate.of(2050, 6, 15),
      "UserSpecsTest-D", LocalDate.of(9999, 6, 15)
    );

    for (int i = 0; i < arr.size(); i += 2) {
      UserEntity user = new UserEntity(arr.get(i).toString());
      user.setActive(true);
      user.setCreatedAt((LocalDate) arr.get(i + 1));
      userRepository.save(user);
    }
  }

  @Test
  public void testCreatedAfter() {
    List<UserEntity> result = userRepository.findAll(
      UserSpecs.createdAfter(LocalDate.of(500, 1, 1)));

    assertEquals(4, result.size());
  }

}