package org.acme;

import io.quarkus.grpc.GrpcService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

import static java.lang.String.format;

@GrpcService
public class HelloSlimService implements SlimGrpc {

  @Override
  public Uni<SlimReply> helloSlim(final SlimRequest request) {
    Log.infof("Processing REST request 🤘for %s", request.getName());
    return Uni.createFrom().item(format("Hi, your Name is what? Your name is who? Your name is: Slim %s!", request.getName()))
        .map(msg -> SlimReply.newBuilder().setMessage(msg).build());
  }
}
