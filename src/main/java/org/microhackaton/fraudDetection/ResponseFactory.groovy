package org.microhackaton.fraudDetection;

public class ResponseFactory {
    public static Closure create(Object response) {
        return { response }
    }
} 
