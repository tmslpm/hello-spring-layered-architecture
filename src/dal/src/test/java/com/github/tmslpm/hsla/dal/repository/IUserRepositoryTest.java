/*
 * MIT License
 *
 * Copyright (c) 2024 Thomas.L
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

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