package org.acme;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class GreetingSlim {

  @GetMapping("/{name}")
  public String hello(@PathVariable("name") final String name) {
    return "Hi, your Name is what? Your name is who? Your name is: Slim" + name + "!";
  }
}
