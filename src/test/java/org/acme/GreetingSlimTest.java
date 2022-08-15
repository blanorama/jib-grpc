package org.acme;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingSlimTest {

  @LocalServerPort
  private int port;

  @Test
  public void testHelloEndpoint() {
    given()
        .port(port)
        .when().get("/hello/Shady")
        .then()
        .statusCode(200)
        .body(is("Hi, your Name is what? Your name is who? Your name is: SlimShady!"));
  }
}
