package com.cristiandrincu.entity;

import javax.validation.constraints.NotNull;

//immutable class - only getters
public class Specification {

    @NotNull
    private Color color;

    @NotNull
    @EnvironmentalFriendly
    private EngineType engineType;

    public Specification() {}

    public Specification(Color color, EngineType engineType) {
        this.color = color;
        this.engineType = engineType;
    }

    public Color getColor() {
        return color;
    }

    public EngineType getEngineType() {
        return engineType;
    }
}
