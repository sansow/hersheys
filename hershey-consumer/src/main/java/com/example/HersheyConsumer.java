package com.example;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.smallrye.reactive.messaging.annotations.Blocking;

@ApplicationScoped
public class HersheyConsumer {

    @Incoming("hershey-in")
    @Blocking
    public void consume(String message) {
        System.out.println("Received message: " + message);
        // Add your message processing logic here
    }
}