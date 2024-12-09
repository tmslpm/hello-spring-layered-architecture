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

package com.github.tmslpm.hsla.pl.controller.user;

import com.github.tmslpm.hsla.bll.dto.UserCreateDTO;
import com.github.tmslpm.hsla.bll.dto.UserDTO;
import com.github.tmslpm.hsla.bll.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
@SuppressWarnings("unused")
public class UserController {
  private final Logger logger = LoggerFactory.getLogger("user-controller");
  private final UserService userService;
  private final UserAssembler userAssembler;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<EntityModel<UserDTO>> createUser(@RequestBody UserCreateDTO userCreateDTO) {
    return new ResponseEntity<>(
      userAssembler.toModel(userService.create(userCreateDTO)),
      HttpStatus.CREATED
    );
  }

  @GetMapping("/all")
  public ResponseEntity<CollectionModel<EntityModel<UserDTO>>> getAll() {
    return ResponseEntity.ok(userAssembler.toCollectionModel(userService.findAll()));
  }

  @GetMapping("/get/{name}")
  public ResponseEntity<EntityModel<UserDTO>> get(@PathVariable String name) {
    return ResponseEntity.ok(userAssembler.toModel(userService.findByName(name)));
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    // enable and implement spring security is required for avoid bad practice
    // so currently just do nothing
    logger.warn("endpoint do nothing currently. so: NO USER DELETED");
    return ResponseEntity.ok().build();
  }

}
