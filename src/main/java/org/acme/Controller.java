package org.acme;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Controller {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/{name}")
  public String hello(@PathParam("name") final String name) {
    return "Hi, your Name is what? Your name is who? Your name is: Slim" + name + "!";
  }
}
