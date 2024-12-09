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
