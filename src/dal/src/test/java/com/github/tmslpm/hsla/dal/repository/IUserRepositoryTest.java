package com.github.tmslpm.hsla.dal.repository;

import com.github.tmslpm.hsla.dal.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest()
@TestPropertySource(locations = "classpath:application.test.properties")
class IUserRepositoryTest {

  @Autowired
  IUserRepository repository;

  @BeforeEach
  void onBeforeEach() {
    this.repository.deleteAll();
  }

  @Test
  void test_find_by_username() {
    repository.save(new UserEntity("test-john41268"));
    assertTrue(repository.findByName("test-john41268").isPresent());
  }

  @Test
  void test_user_entity_audit_fields_are_initialized() {
    // test setup
    repository.save(new UserEntity("test"));
    assertTrue(repository.findByName("test").isPresent());

    // test
    UserEntity savedUser = repository.findByName("test").get();
    assertNotNull(savedUser.getCreatedAt());
    assertNotNull(savedUser.getUpdatedAt());
    assertTrue(savedUser.isActive());
  }

}