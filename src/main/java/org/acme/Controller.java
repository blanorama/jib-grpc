package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class Controller {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("/{name}")
  public String hello(@PathParam("name") final String name) {
    return "Hi, your Name is what? Your name is who? Your name is: Slim" + name + "!";
  }
}
