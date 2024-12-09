package com.github.tmslpm.hsla.pl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@SuppressWarnings("unused")
public class IndexController {
  @GetMapping(value = {"", "index.html", "index", "home.html", "home"})
  String index() {
    return "index";
  }
}