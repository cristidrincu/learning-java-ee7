package com.cristiandrincu.control;

import com.cristiandrincu.entity.Color;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class DefaultColorExposer {

    //by using @Produces annotation, we can now inject Color where we need it
    @Produces
//    @Named("diesel")
    @Diesel
    public Color exposeDefaultColor() {
        return Color.RED;
    }
}
