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

package com.github.tmslpm.hsla.bll.service;

import com.github.tmslpm.hsla.bll.dto.UserCreateDTO;
import com.github.tmslpm.hsla.dal.entity.UserEntity;
import com.github.tmslpm.hsla.dal.repository.IUserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource(locations = "classpath:application.test.properties")
class UserServiceTest {
  @Autowired
  UserService service;

  @MockitoBean
  IUserRepository repository;

  @BeforeEach
  void onBeforeEach() {
  }

  @Test
  void test_create_user_with_valid_data() {
    // mock repository
    when(this.repository.save(any(UserEntity.class)))
      .thenReturn(new UserEntity("john123"));

    // create (save) entity
    final UserEntity user = this.service.create(UserCreateDTO.builder()
      .name("john123")
      .build());

    // test
    assertNotNull(user);
    assertEquals("john123", user.getName());
  }

  @Test
  void test_create_user_with_invalid_name() {
    UserCreateDTO invalidUser = UserCreateDTO.builder().build();
    assertThrows(ConstraintViolationException.class, () -> service.create(invalidUser));
  }

}