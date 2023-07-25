package org.acme;

import io.quarkus.grpc.GrpcService;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloSlimService implements SlimGrpc {

  @Override
  public Uni<SlimReply> helloSlim(final SlimRequest request) {
    Log.infof("Processing REST request 🤘for %s", request.getName());
    return Uni.createFrom().item(
        "Hi, your Name is what? Your name is who? Your name is: Slim" + request.getName() + "!"
    ).map(msg -> SlimReply.newBuilder().setMessage(msg).build());
  }
}
