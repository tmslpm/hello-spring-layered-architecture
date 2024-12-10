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
import com.github.tmslpm.hsla.bll.dto.UserDTO;
import com.github.tmslpm.hsla.bll.exception.UserNotFoundException;
import com.github.tmslpm.hsla.bll.mapper.UserMapper;
import com.github.tmslpm.hsla.dal.entity.UserEntity;
import com.github.tmslpm.hsla.dal.repository.IUserRepository;
import com.github.tmslpm.hsla.dal.specification.UserSpecs;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class UserService {

  private final IUserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserDTO create(@Valid @NotNull UserCreateDTO userDto) {
    return userMapper.toDTO(userRepository
      .save(new UserEntity(userDto.name())));
  }

  public UserDTO findByName(String username) {
    return userRepository
      .findByName(username)
      .map(userMapper::toDTO)
      .orElseThrow(() -> UserNotFoundException
        .builder()
        .username(username)
        .build());
  }

  public List<UserDTO> findAll() {
    return userRepository
      .findAll()
      .stream()
      .map(userMapper::toDTO)
      .toList();
  }

  public List<UserDTO> findUserCreatedAfter(LocalDate date) {
    return userRepository
      .findAll(Specification.where(UserSpecs.createdAfter(date)))
      .stream()
      .map(userMapper::toDTO)
      .toList();
  }

}
