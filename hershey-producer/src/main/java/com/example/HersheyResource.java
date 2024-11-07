package com.example;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Path("/hershey")
public class HersheyResource {

    @Inject
    @Channel("hershey-out")
    Emitter<String> emitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sendMessage() {
        String message = "Hello from Hershey at " + System.currentTimeMillis();
        emitter.send(message);
        return "Message sent: " + message;
    }
}