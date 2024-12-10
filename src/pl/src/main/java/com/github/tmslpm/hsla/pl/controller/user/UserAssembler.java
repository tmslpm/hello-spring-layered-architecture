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

import com.github.tmslpm.hsla.bll.dto.UserDTO;
import com.github.tmslpm.hsla.pl.controller.index.IndexControllerProxy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@AllArgsConstructor
public class UserAssembler implements RepresentationModelAssembler<
  UserDTO,
  EntityModel<UserDTO>
  > {

  @Getter
  private final UserControllerProxy userProxy;
  private final IndexControllerProxy indexProxy;

  @Override
  public @NotNull EntityModel<UserDTO> toModel(@NotNull UserDTO v) {
    return EntityModel.of(v,
      userProxy.linkToGetUserByUsername(v.name()),
      indexProxy.linkToIndex(),
      userProxy.linkToGetAllMembers()
    );
  }

  @Override
  public @NotNull CollectionModel<EntityModel<UserDTO>> toCollectionModel(@NotNull Iterable<? extends UserDTO> entity) {
    return StreamSupport
      .stream(entity.spliterator(), false)
      .map(v -> EntityModel.<UserDTO>of(v, userProxy.linkToGetUserByUsername(v.name())))
      .collect(Collectors.collectingAndThen(Collectors.toList(), CollectionModel::of))
      .add(
        indexProxy.linkToIndex(),
        userProxy.linkToGetAllMembers()
      );
  }

}
