package com.github.tmslpm.hsla.pl.controller.user;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class UserControllerProxy {

  private static class HolderSingletonLazy {
    private static final UserController PROXY = WebMvcLinkBuilder.methodOn(UserController.class);
  }

  public UserController getProxy() {
    return HolderSingletonLazy.PROXY;
  }

  public Link linkToGetAllMembers() {
    return linkTo(this.getProxy().getAll())
      .withRel("users");
  }

  public Link linkToGetUserByUsername(String name) {
    return linkTo(this.getProxy().get(name))
      .withSelfRel()
      .andAffordance(afford(this.getProxy().delete((null))));
  }

}
