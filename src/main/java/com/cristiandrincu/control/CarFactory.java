package com.cristiandrincu.control;

import com.cristiandrincu.entity.Car;
import com.cristiandrincu.entity.Color;
import com.cristiandrincu.entity.Specification;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

public class CarFactory {

    @Inject
//    @Named("diesel") //we need @Named annotation in case we need to use more colors in different places through our application from different exposers/methods
    @Diesel
    Color defaultCarColor;

    public Car createCar(Specification specification) {
        Car car = new Car();
        car.setIdentifier(UUID.randomUUID().toString());
        car.setColor(specification.getColor() == null ? defaultCarColor : specification.getColor());
        car.setEngineType(specification.getEngineType());

        return car;
    }

}
