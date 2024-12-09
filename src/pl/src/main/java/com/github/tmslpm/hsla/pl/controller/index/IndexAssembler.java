package com.github.tmslpm.hsla.pl.controller.index;

import com.github.tmslpm.hsla.pl.controller.user.UserControllerProxy;
import com.github.tmslpm.hsla.pl.dto.ApiSimpleMessageDTO;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class IndexAssembler implements RepresentationModelAssembler<
  ApiSimpleMessageDTO,
  EntityModel<ApiSimpleMessageDTO>
  > {

  private final UserControllerProxy userProxy;

  @Override
  public @NotNull EntityModel<ApiSimpleMessageDTO> toModel(@NotNull ApiSimpleMessageDTO entity) {
    return EntityModel.of(entity, userProxy.linkToGetAllMembers());
  }

  public @NotNull EntityModel<ApiSimpleMessageDTO> toModel(@NotNull String messages) {
    return this.toModel(ApiSimpleMessageDTO
      .builder()
      .content(messages)
      .build());
  }

}
