package com.github.tmslpm.hsla.pl.controller.index;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class IndexControllerProxy {

  private static class HolderSingletonLazy {
    private static final IndexController PROXY = WebMvcLinkBuilder.methodOn(IndexController.class);
  }

  public IndexController getProxy() {
    return HolderSingletonLazy.PROXY;
  }

  public Link linkToIndex() {
    return linkTo(this.getProxy().index())
      .withRel("index");
  }

}
