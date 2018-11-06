package com.cristiandrincu.control;

import com.cristiandrincu.entity.CarCreated;

import javax.enterprise.event.Observes;

public class CarCreationListener {

    public void onCarCreation(@Observes CarCreated carCreated) {
        System.out.println("new car created" + carCreated.getIdentifier());
    }
}
