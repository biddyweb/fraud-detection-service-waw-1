package org.microhackaton.fraudDetection;

import groovy.lang.Closure ;

public class MyClosure extends Closure {
    public MyClosure(Object owner, Object thisObject) {
        super(owner, thisObject);
    }

    public MyClosure(Object owner) {
        super(owner);
    }

    public Object call() {
        return "defaultResponse";
    }
}