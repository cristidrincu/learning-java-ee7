package com.cristiandrincu.entity;

//domain event
public class CarCreated {
    private final String identifier;

    public CarCreated(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }
}
