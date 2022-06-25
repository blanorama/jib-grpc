package org.acme;

import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class HelloSlimServiceTest {

  @GrpcClient
  SlimGrpc slimGrpc;

  @Test
  public void testHello() {
    final SlimReply reply = slimGrpc
        .helloSlim(SlimRequest.newBuilder().setName("Shady").build()).await().atMost(Duration.ofSeconds(5));
    assertEquals("Your Name is what? Your name is who? Your name is: SlimShady!", reply.getMessage());
  }

}
