package org.acme;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloSlimService implements SlimGrpc {

  @Override
  public Uni<SlimReply> helloSlim(final SlimRequest request) {
    return Uni.createFrom().item("Your Name is what? Your name is who? Your name is: Slim" + request.getName() + "!")
        .map(msg -> SlimReply.newBuilder().setMessage(msg).build());
  }
}
