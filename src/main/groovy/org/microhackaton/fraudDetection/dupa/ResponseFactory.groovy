package org.microhackaton.fraudDetection.dupa;

public class ResponseFactory {
    public static Closure create(Object response) {
        return { response }
    }
} 
