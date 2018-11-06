package com.cristiandrincu.boundary;

import com.cristiandrincu.control.CarFactory;
import com.cristiandrincu.control.CarRepository;
import com.cristiandrincu.entity.Car;
import com.cristiandrincu.entity.CarCreated;
import com.cristiandrincu.entity.Specification;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class CarManufacturer {

    @Inject
    CarFactory carFactory;

    @Inject
    CarRepository carRepository; //used to persist cars to db

    @Inject
    Event<CarCreated> carCreatedEvent;

    public Car manufactureCar(Specification specification) {
        Car car = carFactory.createCar(specification);
        carRepository.store(car);
        carCreatedEvent.fire(new CarCreated(car.getIdentifier())); //blocks
        return car;
    }

    public List<Car> retrieveCars() {
        return carRepository.loadCars();
    }
}
