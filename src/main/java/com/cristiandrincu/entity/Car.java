package com.cristiandrincu.entity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

import static com.cristiandrincu.entity.Car.FIND_ALL;
import static com.cristiandrincu.entity.Car.FIND_CAR;

@Entity
@Table(name = "cars")
@NamedQueries({
        @NamedQuery(name = FIND_ALL, query = "select c from Car c"),
        @NamedQuery(name = FIND_CAR, query = "select c from Car c where c.identifier = :identifier")
})
public class Car {

    public static final String FIND_ALL = "Car.findAll"; //it does not matter what we name this as long as the string is unique - "Car.findAll" or "Car.findAllCars" will also work
    public static final String FIND_CAR = "Car.findCar";

    @JsonbTransient //do not include in the response the identifier, it will not be serialized
    @Id
    private String identifier;

    @Enumerated(EnumType.STRING)
    private Color color;

    @JsonbProperty("engine") //output property as engine instead of engineType
    @Enumerated(EnumType.STRING)
    private EngineType engineType;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "identifier='" + identifier + '\'' +
                ", color=" + color +
                ", engineType=" + engineType +
                '}';
    }
}
